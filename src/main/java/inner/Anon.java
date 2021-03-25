package inner;

/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/

interface Function{
    public double calculate(double a, double b);
}

public class Anon {
    public static void main(String[] args) {
        Function summ = new Function() {
            @Override
            public double calculate(double a, double b) {return a+b;}
        };

        Function mult = new Function() {
            @Override
            public double calculate(double a, double b) {return a*b;}
        };
        System.out.println(summ.calculate(10.0,20.0));
        System.out.println(mult.calculate(10.0,20.0));
    }
}
