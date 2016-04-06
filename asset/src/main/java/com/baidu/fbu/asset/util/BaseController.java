package com.baidu.fbu.asset.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
    /** 预先处理页面输入的整数类参数，将空字符串转换为 0；将时间类参数格式化  */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String timeFormat = "yyyy-MM-dd HH:mm:ss";
        String timeFormat = "yyyy-MM-dd";
        binder.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat( timeFormat ), true ) );

        binder.registerCustomEditor(Integer.class, null, new IntegerEditorOfBaseController() );
        binder.registerCustomEditor(int.class, null, new IntegerEditorOfBaseController() );
    }

}