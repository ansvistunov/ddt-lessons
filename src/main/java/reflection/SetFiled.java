package reflection;

import java.lang.reflect.Field;

/**
 * @author : Alex
 * @created : 18.03.2021, четверг
 **/
public class SetFiled {
    static class Test{
        private static String field = "YES";
        @Override
        public String toString(){return field;}
    }

    public static void main(String[] args) {
        Test test = new Test();
        setFieldValue(test,"field","NO");
        System.out.println(test);
    }

    public static void setFieldValue(Object o, String fieldName, Object value){
        Class clasz = o.getClass();
        try {
            Field field = clasz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(o,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
