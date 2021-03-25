package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class CallStaticMethod {
    public static void main(String[] args) {
        System.out.println(callStaticMethod(CallStaticMethod.class, "testCall",
                new Class[]{String.class}, new Object[]{"Java"}));
    }
    public static String testCall(String name) {return "Hello, "+name;}

    static Object callStaticMethod(Class clasz, String methodName, Class[] classArgs, Object[] args){
        try {
            Method method = clasz.getMethod(methodName, classArgs);
            return method.invoke(null, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        } catch (IllegalAccessException e) {
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        } catch (InvocationTargetException e) {
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        }
    }
}
