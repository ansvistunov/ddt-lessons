package reflection;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class ObjectFactory {
    public static void main(String[] args) {
        System.out.println(createObject("java.util.HashMap"));
        System.out.println(createObject(ObjectFactory.class.getName()));
        System.out.println(createObject("java.lang.Integer"));
    }
    static Object createObject(String className){
        try {
            Class clasz = Class.forName(className);
            Object o = clasz.newInstance();
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();throw new RuntimeException(e.getCause());
        } catch (IllegalAccessException e) {
            e.printStackTrace();throw new RuntimeException(e.getCause());
        } catch (InstantiationException e) {
            e.printStackTrace();throw new RuntimeException(e.getCause());
        }
    }
    @Override
    public String toString(){
        return "reflection.ObjectFactory";
    }
}
