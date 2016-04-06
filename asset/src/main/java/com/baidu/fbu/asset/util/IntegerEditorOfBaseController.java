package com.baidu.fbu.asset.util;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/** 用来处理页面输入的整数类参数，将空字符串转换为 0  */
public class IntegerEditorOfBaseController extends PropertiesEditor {
    public void setAsText(String text) throws IllegalArgumentException {
        if ( text == null || text.equals("") ) {
            text = "0";
        }

        setValue(Integer.parseInt(text));
    }

    public String getAsText() {
        return getValue().toString();
    }

}