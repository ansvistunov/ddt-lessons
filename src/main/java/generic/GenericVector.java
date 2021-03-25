package generic;

/**
 * @author : Alex
 * @created : 10.03.2021, среда
 **/




public class GenericVector<T> {
    Object[] data;
    public <T> GenericVector(int size){
        data = new Object[size];
    }

    public <T> void setElement(T element, int index){
        data[index] = element;
    }

    public <T> T getElement(int index){
        return (T)data[index];
    }

    public static void main(String[] args) {
        GenericVector<Double> vector = new GenericVector<>(100);
        vector.setElement(123,1);
        vector.setElement(145.89,10);
        double e = vector.getElement(15);
    }

}
