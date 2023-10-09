package com.imwyf.util;/*
package com.imwyf.util;

import java.lang.reflect.Method;

*/
/**
 * @BelongsProject: Abe_Se
 * @BelongsPackage: com.imwyf.util
 * @Author: wyf
 * @Date: 2023/9/13
 * @Description: 转发后端的请求到对应的处理函数
 *//*

public class HandlerUtils {
    ///////////////////////////////////////////////////////////////////////////////////////////////
    /// 属性
    //////////////////////////////////////////////////////////////////////////////////////////////
    private String _className = "com.imwyf.util.Handle";
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// 方法
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // constructor
    public void SetClassName(String className){
        _className = className;
    }

    */
/**
     * 主函数：根据提供的字符串作为函数名，调用事先定义的Handler函数
     * @param funName 需要调用的函数名
     * @param args 该Handler函数的参数
     * @return 该Handler函数的返回值
     *//*

    public Object FuncForward(String funName,  Object... args){
        Object res = null;
        try{
            Class clz = Class.forName(_className);
            Object obj = clz.newInstance();
            Method method = obj.getClass().getDeclaredMethod(funName, String.class);
            res = method.invoke(obj, args);
        }catch (Exception e){
            System.out.println("找不到对应的Handler函数");
            e.printStackTrace();
        }
        return res;
    }
}

*/
/**
 * @BelongsProject: Abe_Se
 * @BelongsPackage: com.imwyf.util
 * @Author: wyf
 * @Date: 2023/9/13
 * @Description: 提供一些处理函数，供后端请求我（架构部署端的服务）
 *//*

class Handler {

    public void TAInit(String[] attributes){

    }
}

*/
