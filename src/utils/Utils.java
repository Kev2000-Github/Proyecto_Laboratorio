package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static Object getRandomFromList(List<?> list){
        int min = 0;
		int max = list.size() - 1;
		Random random = new Random();
		int randomIndex = random.nextInt(max + min) + min;
        return list.get(randomIndex);
    }

    public static int randomInt(int min, int max){
		int randomNumber = (int)(Math.random()*max+min);
        return randomNumber;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
