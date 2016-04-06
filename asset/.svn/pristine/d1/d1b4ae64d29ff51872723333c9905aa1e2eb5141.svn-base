/** Copyright (C) 2015 Baidu, Inc. All Rights Reserved  */
package com.baidu.fbu.asset.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.baidu.fbu.common.model.ResultBody;
import com.baidu.fbu.common.model.ResultError;
import com.baidu.fbu.common.service.CacheService;
import com.baidu.fbu.common.service.FormatService;
import com.baidu.fbu.common.service.FormatService.LOGSTEP;
import com.baidu.fbu.asset.constants.Constants;

/** 请求返回对象工具类 */
public final class ResultService {
    private ResultService() {

    }

    /** create success result   */
    public static Object success(Object data) {
        Object resultObject = new ResultBody(Constants.ResultCode.SUCCESS, data);
        CacheService.putResult(data);
        FormatService.accMark(LOGSTEP.SUCCESS);
        return new ResponseEntity<Object>(resultObject, HttpStatus.OK);
    }

    /**
     * create error result
     */
    public static Object unknownError() {
        Object resultObject =
                new ResultError(Constants.ResultCode.ERR_UNKOWN,
                        Constants.ResultCode.ERR_MSG[Constants.ResultCode.ERR_UNKOWN]);
        CacheService.putResult(resultObject);
        FormatService.accMark(LOGSTEP.FAILED);
        return resultObject;
    }

}