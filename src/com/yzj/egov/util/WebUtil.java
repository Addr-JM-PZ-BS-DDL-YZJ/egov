package com.yzj.egov.util;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * bean与web工具类
 */
public class WebUtil {
    /**
     * 通过反射机制给obj的字段赋值
     *
     * @param request
     * @param obj
     */
    public static void fieldForValue(HttpServletRequest request, Object obj) {
        //1.获取class
        Class c = obj.getClass();
        //获取网页传递的参数名称集合
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            //获取字段名
            String fieldString = parameterNames.nextElement();
            //拼接方法名
            String methodString = "set" + fieldString.toUpperCase().charAt(0) + fieldString.substring(1);
            try {
                //获取方法
                Method method = c.getDeclaredMethod(methodString, String.class);
                //调用方法
                method.invoke(obj, request.getParameter(fieldString));
            }catch (Exception e){
               // e.printStackTrace();
            }
        }

    }
}
