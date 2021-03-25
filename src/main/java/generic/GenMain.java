package generic;

/**
 * @author : Alex
 * @created : 10.03.2021, среда
 **/

interface GenGetterSetter<T>{
    void set(T value);
    T get();
}

public class GenMain<T> implements GenGetterSetter<T>{
    T value;

    @Override
    public void set(T value) {this.value = value;}

    @Override
    public T get() {return value;}

    public static void main(String[] args) {
        GenMain<String> gs = new GenMain<>();
        gs.set("Test");
        GenMain<Double> gd = new GenMain<>();
        gd.set(145.6);
        //gd.set("Bad String"); //ошибка компиляции
    }
}
