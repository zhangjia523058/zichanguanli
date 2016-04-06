package com.baidu.fbu.asset.util;

import com.baidu.fbu.asset.constants.PageConstants;

public class PageUtil {
    // 初始化 翻页查询的参数
    public static Integer handlePage( Integer page ) {
        if ( page == null || page <= 0 ) {
            page = 1;
        }
        return page;
    }

    // 初始化 翻页查询的参数
    public static Integer handlePageSize( Integer pageSize, String className ) {
        if ( pageSize == null || pageSize <= 0 ) {
            // 根据 类名 获得对应的每页查询的行数
            pageSize = PageConstants.getPageSize(className);
        }
        return pageSize;
    }

    // 计算 分页查询的分页行数
    public static Integer calculateStartRow(  Integer page, Integer pageSize ) {
        return ( page - 1 ) * pageSize;
    }

    // 计算 分页查询的结果，一共有几页
    public static long calculateHowManyPages( long count, Integer pageSize ) {
        long howManyPages = 0;

        if ( count % pageSize == 0 ) {
            howManyPages = count / pageSize;
        } else {
            howManyPages = count / pageSize + 1;
        }
        return howManyPages;
    }

}