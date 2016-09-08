package com.liang.pro.notefortravel.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Reflection {

    public Object newInstance(String className,Object[] object,Class[] classeType) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class newOneClass = Class.forName(className);
        if (object == null){
            return newOneClass.newInstance();
        }else{
            Constructor constructor = newOneClass.getConstructor(classeType);
            return constructor.newInstance(object);
        }

    }
}
