package reflection;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/


class InspectClass {
    public static void main(String[] argv) throws ClassNotFoundException {
        String name = "reflection.InspectClass";
        Class c = Class.forName(name);
        Constructor[] cons = c.getConstructors( );
        System.out.println("Constructors:" + Arrays.asList(cons));
        Method[] meths = c.getMethods( );
        System.out.println("Methods:"+ Arrays.asList(meths));
        Field[] fields = c.getDeclaredFields();
        System.out.println("Fields:"+ Arrays.asList(fields));
    }
    private Integer i;

}
