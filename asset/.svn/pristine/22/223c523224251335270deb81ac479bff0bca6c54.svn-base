package com.baidu.fbu.asset.controller;

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

import com.baidu.fbu.asset.entity.AssetStageDetail;
import com.baidu.fbu.asset.service.AssetStageDetailService;
import com.baidu.fbu.asset.util.BaseController;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;
import com.baidu.fbu.common.service.FormatService;

@Controller
@RequestMapping("/assetStageDetail")
public class AssetStageDetailController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AssetStageDetailController.class);

    /** 字符编码 */
    private static final String DEFAULT_CHARSET = "UTF-8";

	@Resource
	private AssetStageDetailService assetStageDetailService;

    /** 查询 AssetStageDetail 结果集 */
    @RequestMapping("/findAssetStageDetail")
    public void findAssetStageDetail( AssetStageDetail assetStageDetail, Integer page, Integer pageSize,
                                      HttpServletResponse rep, HttpServletRequest request) {
        // Util.print( page + "===="+ pageSize );
        String loanId = null;
        try {
            loanId = request.getParameter("loanId").toString();
            assetStageDetail.setLoanId(loanId);
        } catch (Exception e) {
            LOG.error("", e);
        }
        // 查询第几页
        page = PageUtil.handlePage( page );

        // 每页的行数
        pageSize = PageUtil.handlePageSize( pageSize, "AssetStageDetail" );

        // 查询起始行的行数
        int startRow = PageUtil.calculateStartRow(page, pageSize);

        Map<String, Object> resultMap = null;

        try {
            resultMap = assetStageDetailService.findByParam( assetStageDetail, startRow, pageSize );
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetStageDetail error"), e);
        }

        resultMap.put( "message", "success" );
        resultMap.put( "page", page );
        resultMap.put( "pageSize", pageSize );
        resultMap.put( "assetStageDetail", assetStageDetail );    // assetStageDetail 是存放查询条件的实体类
        IOUtil.writeToPage( rep, resultMap );
    }

    /** 添加 AssetStageDetail */
    @RequestMapping("/addAssetStageDetail")
    public void addAssetStageDetail( AssetStageDetail assetStageDetail, HttpServletResponse rep ) {
        try {
            assetStageDetailService.add( assetStageDetail );
            IOUtil.writeToPage( rep, "message", "success" );
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("addAssetStageDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

	/** 查询修改页面所需的 AssetStageDetail 实体类 */
	@RequestMapping("/toUpdateAssetStageDetail")
	public void toUpdateAssetStageDetail( AssetStageDetail assetStageDetail, HttpServletResponse rep ) {
		AssetStageDetail result = null;

		try {
            result = assetStageDetailService.findById(assetStageDetail);
        } catch (SQLException e) {
			LOG.error(FormatService.logFormat("toUpdateAssetStageDetail error"), e);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "message", "success" );
		resultMap.put( "assetStageDetail", result );
	    IOUtil.writeToPage( rep, resultMap );
	}

	/** 修改 AssetStageDetail */
	@RequestMapping("/updateAssetStageDetail")
	public void updateAssetStageDetail( AssetStageDetail assetStageDetail, HttpServletResponse rep ) {
		try {
			assetStageDetailService.update( assetStageDetail );
            IOUtil.writeToPage( rep, "message", "success" );  // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("updateAssetStageDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
		}
	}

	/** 删除 AssetStageDetail */
    @RequestMapping("/deleteAssetStageDetail")
    public void deleteAssetStageDetail(AssetStageDetail assetStageDetail, HttpServletResponse rep) {
        try {
            assetStageDetailService.deleteById( assetStageDetail );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("deleteAssetStageDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 导出 资产计划 相关的 资产分期明细 到 excel
     * @throws IOException */
    @RequestMapping("/exportAssetDetailOfAnAssetPlan")
    public void exportAssetStageDetailOfAnAssetPlan(String ids, HttpServletResponse response)
            throws IOException {
        HSSFWorkbook workbook = null;
        try {
            workbook = assetStageDetailService.generateAssetStageToExcel(ids);
            response.reset();
            response.setCharacterEncoding(DEFAULT_CHARSET);
            response.setContentType("application/vnd.ms-excel; charset=" + DEFAULT_CHARSET);
            response.setHeader("Content-Disposition", "attachment; filename=assetStageDetail"
                    + Util.getDateStringOfToday() + ".xls");
            workbook.write(response.getOutputStream());
        } catch (RuntimeException e) {
            LOG.error(FormatService.logFormat("download assetStageDetail To excel error."), e);
        } catch (IOException e) {
            LOG.error(FormatService.logFormat("download assetStageDetail To excel error."), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

}