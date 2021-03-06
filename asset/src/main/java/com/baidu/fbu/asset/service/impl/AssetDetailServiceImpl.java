package com.baidu.fbu.asset.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.fbu.asset.dao.AssetDetailDao;
import com.baidu.fbu.asset.dao.AssetPlanDao;
import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import com.baidu.fbu.asset.service.AssetDetailService;
import com.baidu.fbu.asset.util.ExcelUtil;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;
import com.baidu.fbu.common.service.FormatService;

@Service("assetDetailService")
@Transactional
public class AssetDetailServiceImpl implements AssetDetailService {
    /** log日志  */
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AssetDetailServiceImpl.class);

    @Resource
    private AssetDetailDao assetDetailDao;

    @Resource
    private AssetPlanDao assetPlanDao;

    public void add( AssetDetail assetDetail ) throws SQLException {
        assetDetail.setCreatetime(new Date());
        assetDetail.setUpdatetime(new Date());
        assetDetailDao.addByParam(assetDetail);
    }

    public void update(AssetDetail assetDetail) throws SQLException {
        assetDetail.setUpdatetime(new Date());
        assetDetailDao.updateByParam(assetDetail);
    }

    public void deleteById( String id ) throws SQLException {
        assetDetailDao.deleteById(id);
    }

    public AssetDetail findById( String id) throws SQLException {
        return (AssetDetail) assetDetailDao.findById(id);
    }

    /** 查询符合条件的记录的条数  */
    public Long countByParam(AssetDetail assetDetail) throws SQLException {
        Map<String, Object> map = IOUtil.beanToMap(assetDetail);
        return assetDetailDao.countByParam( map );
    }

    /** 查询所有符合条件的记录  */
    public Map<String, Object> findByParam( AssetDetail assetDetail ) throws SQLException {
        // -1, -1 表示 不分页查询，而是查询全部。分页查询的话，startRow 默认参数是 0，pageSize 是一个正整数
        return findByParam( assetDetail, -1, -1 );
    }

    /** 查询符合条件的记录，分页  */
    public Map<String, Object> findByParam( AssetDetail assetDetail, int startRow, int pageSize ) throws SQLException {
        Map<String, Object> map = IOUtil.beanToMap(assetDetail);

        String ids = assetDetail.getRepayStatusStr();
        if (ids != null) {
            List<Object> idList = Util.idsStringToList(ids);
            map.put( "idList", idList );
        }

        long count = assetDetailDao.countByParam(map);

        map.put( "startRow", startRow );
        map.put( "pageSize", pageSize );
        List<Object> list = assetDetailDao.findByParam(map);

        // 转换结果集中的 Date 类型的属性 为 字符串，否则 sf.json jar 转换成 JSONObject时 会报错
        List keyList = Util.getArrayList();
        keyList.add( "loanTime" );
        keyList.add( "dueDate" );
        keyList.add( "amortisementDate" );
        IOUtil.formatDateToStr(list, keyList);

        Map<String, Object> result = Util.getHashMap();
        result.put( "list", list );
        result.put( "count", count );

        if ( startRow > -1 && pageSize > -1 ) {     // startRow 默认是 0 ， pageSize 默认是一个正整数
            long howManyPages = PageUtil.calculateHowManyPages(count, pageSize);
            result.put( "howManyPages", howManyPages );
        }

        // Util.print( startRow+" --- "+pageSize+" --- "+count +" --- ");
        return result;
    }

    // 计算 资产计划 的 4 种本息的总金额
    public Map<String, Object> sumInterestInquiry(String apId) {
        return assetDetailDao.sumInterestInquiry(apId);
    }

    /** 批量为 资产计划 添加 资产   */
    public void batchAddAssetDetailToAssetPlan(String ids, String apId) {
        List<Object> idList = Util.idsStringToList(ids);

        Map<String, Object> map = Util.getHashMap();
        map.put( "idList", idList );
        map.put( "apId", apId );
        map.put( "transferStatus", 1 );    // 更新 资产明细的状态  0 = 未转让  1 = 待转让   2 = 已转让
        map.put( "updatetime", new Date());
        assetDetailDao.batchUpdateAssetDetailByParam(map);
    }

    /** 删除 资产计划 中的 资产   */
    public void deleteAssetDetailInAssetPlan(AssetDetail assetDetail) throws SQLException {
        assetDetail.setApId("");
        assetDetail.setTransferStatus((short) 0);    // 更新 资产明细的状态  0 = 未转让   1 = 待转让   2 = 已转让
        assetDetail.setUpdatetime(new Date());
        assetDetailDao.updateByParam(assetDetail);
    }

    /** 批量删除 资产计划 中的 资产   */
    public void batchDeleteAssetDetailInAssetPlan(String ids) {
        List<Object> idList = Util.idsStringToList(ids);

        Map<String, Object> map = Util.getHashMap();
        map.put( "idList", idList );
        map.put( "apId", 0L );
        map.put( "transferStatus", 0 );    // 更新 资产明细的状态  1 = 待转让   2 = 已转让  0 = 未转让
        map.put( "updatetime", new Date());
        assetDetailDao.batchUpdateAssetDetailByParam(map);
    }

    /** 导入 excel，确定资产管理人要购买的资产
     * idList 是资产管理人要购买的资产的借据号的集合
     */
    public void sellAsset(Long apId, List<Object> decideToBuy) throws Exception {
        // 查询资产计划中的所有资产
        Map<String, Object> searchParam = Util.getHashMap();
        searchParam.put("apId", apId);
        searchParam.put("startRow", -1);
        searchParam.put("pageSize", -1);
        List<Object> assets = assetDetailDao.findByParam( searchParam );

        // Util.print(apId + "-----");
        // Util.print(decideToBuy);
        // Util.print(assets);

        if (Util.isNullOrEmptyOrZero( assets )) {
            throw new Exception("计划中没有资产明细");
        }

        // 判断 资产计划 中是否包含上传的 资产明细
        for (int j = 0; j < decideToBuy.size(); j++) {
            boolean existFlag = false;
            String loanIdOfAssetToBuy = (String) decideToBuy.get(j);

            for (int i = 0; i < assets.size(); i++) {
                AssetDetail asset = (AssetDetail) assets.get(i);
                Util.print(asset.getLoanId() + "  ---- " + loanIdOfAssetToBuy);
                if (loanIdOfAssetToBuy.equals(asset.getLoanId())) {
                    existFlag = true;
                }
            }

            // Util.print(existFlag);
            if (existFlag == false) {
                throw new Exception("资产计划中不包含上传的资产明细, 它的借据号是 "
                            + loanIdOfAssetToBuy);
            }
        }

        // 首先 将 excel 导入的，并且已经校验完的 资产，更新为 已转让 的计划中的资产
        Map<String, Object> map = Util.getHashMap();
        map.put( "idList", decideToBuy );  // idList 是资产管理人要购买的资产的借据号的集合
        map.put( "apId", apId );
        map.put( "transferStatus", 2 );    // 更新 资产明细的状态   2 = 已转让    0 = 未转让   1 = 待转让
        map.put( "updatetime", new Date());

        assetDetailDao.batchUpdateAssetDetailByLoanIds(map);

        // 然后 删除 资产计划 中 资产管理人不购买的 资产明细
        assetDetailDao.removeAssetDetailsNotBuyInAnAssetPlan(map);
    }

    public HSSFWorkbook generateAssetToExcel(AssetDetail assetDetail) {
        List<AssetDetailVo> list = new ArrayList<AssetDetailVo>();
        HSSFWorkbook listToExcel = null;
        try {
            list = assetDetailDao.exportAssets(assetDetail);
            listToExcel = ExcelUtil.exportAsset(list);
        } catch (DataAccessException e) {
            LOG.error(FormatService.logFormat("generateOrderToExcelByCon error."), e);
        }
        return listToExcel;
    }

    /** 导入借据到数据库 */
    @SuppressWarnings("resource")
    public void excelInAsset(File f) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(f));
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                HSSFSheet sheet = workbook.getSheetAt(i); // 获取每个Sheet表
                int num = sheet.getPhysicalNumberOfRows(); // 获取每个Sheet表行数
                if (num > 1) {
                    HSSFRow titleRow = sheet.getRow(0); // 获取第一行，判断需要的值在哪一列
                    Integer buyOrNotNum = null;
                    Integer loanIdNum = null;
                abc:
                    for (int j = 0; j < titleRow.getPhysicalNumberOfCells(); j++) {
                        HSSFCell cell = titleRow.getCell(j);
                        if (cell != null) {
                            String cellStr = cell.toString();
                            if ("是否购买".equals(cellStr)) {
                                buyOrNotNum = Integer.valueOf(j);
                            } else if ("借据号".equals(cellStr)) {
                                loanIdNum = Integer.valueOf(j);
                            }
                            if (buyOrNotNum != null && loanIdNum != null) {
                                break abc;
                            }
                        }
                    }
                    buyOrNotNum = buyOrNotNum != null ? buyOrNotNum : 1;
                    loanIdNum = loanIdNum != null ? loanIdNum : 2;
                    for (int k = 1; k < sheet.getPhysicalNumberOfRows() - 6; k++) { // 去除合计的行数
                        HSSFRow contentRow = sheet.getRow(k); // 获取每一行
                        HSSFCell buyOrNotCell = contentRow.getCell(buyOrNotNum);
                        if (buyOrNotCell != null && buyOrNotCell.toString().trim().length() != 0) {
                            HSSFCell loanIdCell = contentRow.getCell(loanIdNum);
                            if (loanIdCell != null && loanIdCell.toString().trim().length() != 0) {
                                String loanId = loanIdCell.toString().trim();
                                Map<String, Object> checkResult = new HashMap<String, Object>();
                                try {
                                    checkResult = assetDetailDao
                                            .checkLoanId(loanId); // 检查当前loan_id数据库中是否存在
                                } catch (DataAccessException e) {
                                    LOG.error("查询数据库表tbl_asset_detail失败", e);
                                }
                                if (checkResult != null) {
                                    Integer transferStatus = (Integer) checkResult.get("transfer_status"); // 获取转让状态
                                    BigDecimal surplusPrincipalAmount =
                                            (BigDecimal) checkResult.get("surplus_principal_amount"); // 获取剩余本息
                                    BigDecimal zeroBd = new BigDecimal(0);
                                    surplusPrincipalAmount =
                                            surplusPrincipalAmount != null ? surplusPrincipalAmount : zeroBd;
                                    if (transferStatus == 1 && surplusPrincipalAmount.compareTo(zeroBd) > 0) {
                                        checkResult.put("transfer_principal_interest", surplusPrincipalAmount);
                                        checkResult.put("transfer_status", 2);
                                        checkResult.remove("surplus_principal_amount");
                                        try {
                                            checkResult.put("updatetime", new Date());
                                            assetDetailDao.updateTransferPrincipalInterest(checkResult);
                                        } catch (DataAccessException e) {
                                            LOG.error("更新数据库表 tbl_asset_detail 失败", e);
                                        }
                                    } else {
                                        LOG.error("该借据不属于待转让借据或该借据剩余本息等于零，借据号" + loanId);
                                    }
                                } else {
                                    LOG.error("导入功能：数据库中找不到该借据信息，借据号:" + loanId);
                                }
                            } else {
                                LOG.error("导入功能：该行借据单元格没有任何值，行号：" + (k + 1));
                            }
                        } else {
                            LOG.error("该借据未被购买，行号" + (k + 1));
                        }
                    }
                } else {
                    LOG.error("该页为空");
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("找不到要导入的xls文件", e);
        } catch (IOException e) {
            LOG.error("IO输入异常", e);
        }
    }

}