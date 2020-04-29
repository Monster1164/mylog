package com.cb.myblog.util;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;

public class MyBeanUtils {

    /**
     * 获得属性为空的key的数组
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source){

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        ArrayList<String> nullPropertyNames = new ArrayList<>();
        for(PropertyDescriptor pd :pds){
            String propertyName =pd.getName();
            if(beanWrapper.getPropertyValue(propertyName) == null){
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
