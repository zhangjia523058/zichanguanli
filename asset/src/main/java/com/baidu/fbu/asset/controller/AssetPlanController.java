package com.baidu.fbu.asset.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.fbu.asset.entity.AssetPlan;
import com.baidu.fbu.asset.service.AssetPlanService;
import com.baidu.fbu.asset.util.BaseController;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.common.service.FormatService;

@Controller
@RequestMapping("/assetPlan")
public class AssetPlanController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AssetPlanController.class);

	@Resource
	private AssetPlanService assetPlanService;

    /** 查询 AssetPlan 列表 */
    @RequestMapping("/findAssetPlan")
    public void findAssetPlan( AssetPlan assetPlan, Integer page, Integer pageSize, HttpServletResponse rep ) {
        // Util.print( page + "===="+ pageSize );

        // 查询第几页
        page = PageUtil.handlePage( page );

        // 每页的行数
        pageSize = PageUtil.handlePageSize( pageSize, "AssetPlan" );

        // 查询起始行的行数
        int startRow = PageUtil.calculateStartRow(page, pageSize);

        Map<String, Object> resultMap = null;

        try {
            resultMap = assetPlanService.findByParam( assetPlan, startRow, pageSize );
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", "查询资产计划列表时，出错" );
        }

        resultMap.put( "message", "success" );
        resultMap.put( "page", page );
        resultMap.put( "pageSize", pageSize );
        resultMap.put( "assetPlan", assetPlan );    // assetPlan 是存放查询条件的实体类
        IOUtil.writeToPage( rep, resultMap );
    }

    /** 添加 AssetPlan */
    @RequestMapping("/addAssetPlan")
    public void addAssetPlan( AssetPlan assetPlan, HttpServletResponse rep ) {
        try {
            assetPlanService.add( assetPlan );
            IOUtil.writeToPage( rep, "message", "success" );
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("addAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", "添加资产计划时，出错" );
        }
    }

	/** 查询 AssetPlan 实体类 */
	@RequestMapping("/toUpdateAssetPlan")
	public void toUpdateAssetPlan( AssetPlan assetPlan, HttpServletResponse rep ) {
		AssetPlan result = null;

		try {
			result = assetPlanService.findById( assetPlan.getId() );
		} catch (SQLException e) {
			LOG.error(FormatService.logFormat("toUpdateAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", "查询资产计划时，出错" );
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "message", "success" );
		resultMap.put( "assetPlan", result );
	    IOUtil.writeToPage( rep, resultMap );
	}

	/** 修改 AssetPlan */
	@RequestMapping("/updateAssetPlan")
	public void updateAssetPlan( AssetPlan assetPlan, HttpServletResponse rep ) {
		try {
			assetPlanService.update( assetPlan );
            IOUtil.writeToPage( rep, "message", "success" );  // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("updateAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", "修改资产计划时，出错" );
		}
	}

	/** 撤销 未发行的 AssetPlan */
    @RequestMapping("/cancelAssetPlan")
    public void cancelAssetPlan( AssetPlan assetPlan, HttpServletResponse rep ) {
        try {
            assetPlanService.cancel( assetPlan.getId() );
            IOUtil.writeToPage( rep, "message", "success" );  // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("cancelAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", "撤销资产计划时，出错" );
        }
    }

	/** 删除 AssetPlan */
    @RequestMapping("/deleteAssetPlan")
    public void deleteAssetPlan( AssetPlan assetPlan, HttpServletResponse rep ) {
        try {
            assetPlanService.deleteById( assetPlan.getId() );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("deleteAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", "删除资产计划时，出错" );
        }
    }

}