package com.bnuz.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T> T  copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
            System.out.println(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }
}
