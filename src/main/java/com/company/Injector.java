package com.company;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    public <T> T inject(T obj){
        Class<?> aClass = obj.getClass(); 
        Properties properties = new Properties(); 
        try {
            properties.load(getClass().getResourceAsStream("/injector.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for(Field field : aClass.getDeclaredFields()) { 
            field.setAccessible(true); 
            try {
                if (field.getAnnotation(AutoInjectable.class) != null && field.get(obj) == null) {
                    field.set(obj, Class.forName(properties.getProperty(field.getType().getName())).getDeclaredConstructor().newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
