/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/

interface Getter{
    int get();
}

interface GetterSetter extends Getter{
    void set(int val);
}


class ClassImpl implements GetterSetter {
    int x;
    @Override
    public void set(int val) {
        this.x = val;
    }
    @Override
    public int get() {
        return x;
    }

    public static void main(String[] args) {
        ClassImpl cls = new ClassImpl();
        Getter g = cls; //приведение типов не нужно
        GetterSetter gs = cls; //приведение типов не нужно



    }

}
