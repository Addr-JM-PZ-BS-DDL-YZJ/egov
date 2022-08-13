package com.yzj.egov.util;

import java.util.ResourceBundle;

/**
 * 字符串工具类
 */
public class StringUtil {

    private StringUtil(){}
        //resources/message
    private static ResourceBundle bundle1 = ResourceBundle.getBundle("resources.message");
    /**
     * 根据字符获取机构名称
     * @param code
     * @return
     */
    public static String getOrgTypeString(String code){
        return bundle1.getString(code);
    }

    public static String getCtyString(String ctycode) {
        return bundle1.getString(ctycode);
    }

    public static String getString(){
        return null;
    }

}
