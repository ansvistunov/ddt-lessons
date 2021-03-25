package generic;

/**
 * @author : Alex
 * @created : 10.03.2021, среда
 **/
public class NullWrapper {
    private Object obj;
    public <T> NullWrapper(T element){
        if (element == null) throw new RuntimeException("Element is null!");
        this.obj = element;
    }
    public <T> T get(){
        return (T)obj;
    }

    public static void main(String[] args) {
        NullWrapper wint = new NullWrapper(134);
        NullWrapper wdouble = new NullWrapper(134.5);
        int i = wint.get();
        double d = wdouble.get();
        int err = wdouble.get(); //Exception in thread "main" java.lang.ClassCastException: class java.lang.Double cannot be cast to class java.lang.Integer
    }
}
