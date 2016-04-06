/**  Copyright (C) 2015 Baidu, Inc. All Rights Reserved. */
package com.baidu.fbu.asset.constants;

import org.springframework.beans.factory.annotation.Value;

import com.baidu.fbu.asset.util.DesUtils;

/** 资产管理 的 常量类 */
public class Constants {    
    @Value( "${mis.asset}" )
    private String misAsset;
    
    @Value("${mis.checkurl}")
    private String misCheckurl;
    
    @Value("${mis.getinfourl}")
    private String misGetinfourl;
    
    @Value("${mis.sign}")
    private String misSign;
    
    public static class Base {
        public static String MIS_ASSET;
        public static String MIS_CHECKURL;
        public static String MIS_GETINFOURL;
        public static String MIS_SIGN;
        
        private static void init( String misAsset, String misCheckurl, String misGetinfourl, String misSign) {
            MIS_ASSET = misAsset;
            MIS_CHECKURL = misCheckurl;
            MIS_GETINFOURL = misGetinfourl;
            MIS_SIGN = DesUtils.decryptKey( misSign );            
        }
    }
        
    private void init() {
        Base.init( misAsset, misCheckurl, misGetinfourl, misSign );
    }

    /** 返回码     */
    public static class ResultCode {
        public static final String[] ERR_MSG = {
                "success", // 00
                "未知原因", // 01
                "请求参数格式错误" // 02
        };
        public static final int SUCCESS = 0;
        public static final int ERR_UNKOWN = 1;
        public static final int ERR_REQUEST_PARAM_ERROR = 2;
    }
    
}