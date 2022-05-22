package utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;

import vistas.general.ComboboxItem;

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

    public static Date asDate(LocalDate localDate) {
      return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static boolean isNumber(String number){
        try{
            Double.parseDouble(number);
            return true;
        }
        catch(Exception err){
            return false;
        }
    }

    public static String genRandomSalt(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static int findCombobox(JComboBox<ComboboxItem> c, String s) {
       int size = c.getItemCount();
       int itemFind = -1;
       for (int i = 0; i < size; i++) {
           ComboboxItem item = c.getItemAt(i);
           if (s.equals(item.getId())){
               itemFind = i;
           }
           System.out.println("Item at " + item.getId());
       }
       return itemFind;
    }
}
