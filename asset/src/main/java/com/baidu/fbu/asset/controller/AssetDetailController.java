package com.baidu.fbu.asset.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.service.AssetDetailService;
import com.baidu.fbu.asset.util.BaseController;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;
import com.baidu.fbu.common.service.FormatService;

@Controller
@RequestMapping("/assetDetail")
public class AssetDetailController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(AssetDetailController.class);

    /** 字符编码 */
    private static final String DEFAULT_CHARSET = "UTF-8";

	@Resource
	private AssetDetailService assetDetailService;

    /** 查询 AssetDetail 结果集 */
    @RequestMapping("/findAssetDetail")
    public void findAssetDetail( AssetDetail assetDetail, Integer page, Integer pageSize,
                                                            HttpServletResponse rep ) {
        // Util.print( page + "===="+ pageSize );

        // 查询第几页
        page = PageUtil.handlePage( page );

        // 每页的行数
        pageSize = PageUtil.handlePageSize( pageSize, "AssetDetail" );

        // 查询起始行的行数
        int startRow = PageUtil.calculateStartRow(page, pageSize);

        Map<String, Object> resultMap = null;
        try {
            resultMap = assetDetailService.findByParam( assetDetail, startRow, pageSize );
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetDetail error"), e);
        }

        resultMap.put( "message", "success" );
        resultMap.put( "page", page );
        resultMap.put( "pageSize", pageSize );
        resultMap.put( "assetDetail", assetDetail );    // assetDetail 是存放查询条件的实体类

        // 计算 资产计划 的 4 种本息的总金额
        String apId = assetDetail.getApId();

        if (!Util.isNullOrEmptyOrZero(apId)) {
            Map<String, Object> sumMap = null;
            try {
                sumMap = assetDetailService.sumInterestInquiry(apId);
                resultMap.put( "info", sumMap );
            } catch (SQLException e) {
                LOG.error(FormatService.logFormat("findAssetDetail error"), e);
            }
        }

        IOUtil.writeToPage( rep, resultMap );
    }

    /** 添加 AssetDetail */
    @RequestMapping("/addAssetDetail")
    public void addAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.add( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("addAssetDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

	/** 查询修改页面所需的 AssetDetail 实体类 */
	@RequestMapping("/toUpdateAssetDetail")
	public void toUpdateAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
		AssetDetail result = null;

		try {
			result = assetDetailService.findById( assetDetail.getLoanId() );
		} catch (SQLException e) {
			LOG.error(FormatService.logFormat("toUpdateAssetDetail error"), e);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "message", "success" );
		resultMap.put( "assetDetail", result );
	    IOUtil.writeToPage( rep, resultMap );
	}

	/** 修改 AssetDetail */
	@RequestMapping("/updateAssetDetail")
	public void updateAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
		try {
			assetDetailService.update( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );  // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("updateAssetDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
		}
	}

	/** 删除 AssetDetail */
    @RequestMapping("/deleteAssetDetail")
    public void deleteAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.deleteById( assetDetail.getLoanId() );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("deleteAssetDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 批量添加 AssetDetail 到 AssetPlan */
    @RequestMapping("/batchAddAssetDetailToAssetPlan")
    public void batchAddAssetDetailToAssetPlan(String ids, AssetDetail assetDetail, HttpServletResponse rep) {
        try {
            assetDetailService.batchAddAssetDetailToAssetPlan(ids, assetDetail.getApId());
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("batchAddAssetDetailToAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 删除 AssetPlan 中的  AssetDetail */
    @RequestMapping("/deleteAssetDetailInAssetPlan")
    public void deleteAssetDetailInAssetPlan( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.deleteAssetDetailInAssetPlan( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(FormatService.logFormat("deleteAssetDetailInAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 批量删除 AssetPlan 中的  AssetDetail */
    @RequestMapping("/batchDeleteAssetDetailInAssetPlan")
    public void batchDeleteAssetDetailInAssetPlan(String ids, HttpServletResponse rep) {
        try {
            assetDetailService.batchDeleteAssetDetailInAssetPlan(ids);
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("batchDeleteAssetDetailInAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 导出 资产计划 相关的 资产明细 到 excel
     * @throws IOException */
    @RequestMapping("/exportAssetDetailOfAnAssetPlan")
    public void exportAssetDetailOfAnAssetPlan(AssetDetail assetDetail,
            HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = null;
        try {
            workbook = assetDetailService.generateAssetToExcel(assetDetail);
            response.reset();
            response.setCharacterEncoding(DEFAULT_CHARSET);
            response.setContentType("application/vnd.ms-excel; charset=" + DEFAULT_CHARSET);
            response.setHeader("Content-Disposition", "attachment; filename=assetDetail"
                    + Util.getDateStringOfToday() + ".xls");
            workbook.write(response.getOutputStream());
        } catch (RuntimeException e) {
            LOG.error(FormatService.logFormat("downloadOrderToExcelByCon error."), e);
        } catch (IOException e) {
            LOG.error(FormatService.logFormat("downloadOrderToExcelByCon error."), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    /** 导入 excel 确定资产管理人要购买的资产 */
    @RequestMapping("/importExcelToSellAsset")
    public void importExcelToSellAsset( @RequestParam MultipartFile file,
                                    HttpServletResponse rep, HttpServletRequest req) {
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring( 0, fileName.length() - 4 ) + "_" + Util.getRandomString( 10 ) + ".xls";
        String uploadPath = req.getSession().getServletContext().getRealPath("/");
        uploadPath += File.separator ; // +"upload"+ File.separator;

        // Util.print( uploadPath+" == "+ fileName );

        // 上传文件到服务器
        File uploadFile = new File(uploadPath + "/" + fileName);
        try {
            file.transferTo(uploadFile);
        } catch (IllegalStateException e) {
            LOG.error(FormatService.logFormat("上传excel失败"), e);
        } catch (IOException e) {
            LOG.error(FormatService.logFormat("上传excel失败"), e);
        }
        try {
            assetDetailService.excelInAsset(uploadFile);
        } catch (RuntimeException e) {
            LOG.error(FormatService.logFormat("importExcelToSellAsset error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }

        // 删除文件
        IOUtil.deleteFile(uploadPath, fileName);

        IOUtil.writeToPage( rep, "message", "success" );
    }

}