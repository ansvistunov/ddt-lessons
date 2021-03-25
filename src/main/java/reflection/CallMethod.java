package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class CallMethod {
    public static void main(String[] args) {
        System.out.println(callmethod("","isEmpty", new Class[]{}, new Object[]{}));
        System.out.println(callmethod("test","toUpperCase",new Class[]{}, new Object[]{}));
        System.out.println(callmethod("test","repeat", new Class[]{int.class}, new Object[]{3}));
    }

    static Object callmethod(Object o, String methodName, Class[] classArgs, Object[] args){
        Class clasz = o.getClass();
        try {
            Method method = clasz.getMethod(methodName, classArgs);
            return method.invoke(o, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        } catch (IllegalAccessException e) {
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        } catch (InvocationTargetException e) {
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        }
    }
}
