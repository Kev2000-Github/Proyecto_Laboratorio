package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<String> getFieldsName(Object o) throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> result = new ArrayList<String>();
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            result.add(field.getName());
        }
        return result;
    }
}
