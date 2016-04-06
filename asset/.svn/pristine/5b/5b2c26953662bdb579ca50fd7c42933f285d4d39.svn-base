/**  Copyright (C) 2015 Baidu, Inc. All Rights Reserved. */
package com.baidu.fbu.asset.constants;

import java.util.HashMap;

/** 资产管理 的 分页常量类 */
public class PageConstants {
    /** 资产管理计划 每页查询的 条数 */
    private static final int pageSizeOfAssetPlan = 10;

    /** 资产管理员 每页查询的 条数 */
    private static final int pageSizeOfAssetManager = 10;

    /** 资产明细 每页查询的 条数 */
    private static final int pageSizeOfAssetDetail = 20;

    /** 资产分期明细 每页查询的 条数 */
    private static final int pageSizeOfAssetStageDetail = 20;

    private static HashMap<String, Object> pageSizeMaps = new HashMap<String, Object>();

    static {
        pageSizeMaps.put("AssetPlan", pageSizeOfAssetPlan);
        pageSizeMaps.put("AssetManager", pageSizeOfAssetManager);
        pageSizeMaps.put("AssetDetail", pageSizeOfAssetDetail);
        pageSizeMaps.put("AssetStageDetail", pageSizeOfAssetStageDetail);
    }

    public static int getPageSize(String className){
        return (Integer)pageSizeMaps.get(className);
    }

}