package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public class GenTest2 {

    public static void readOnly(List<? extends Number> list){
        Number n = list.get(0); //брать можно
        System.out.println(n);
        //list.add(new Integer(134)); //класть - нельзя (ошибка компилятора)
    }

    public static void writeOnly(List<? super Number> list){
        //Number n = list.get(0); //брать нельзя (ошибка компилятора)
        list.add(new Integer(134)); //класть - можно
        list.add(new Double(134.0)); //класть - можно
        //Object o = list.get(0);
        //System.out.println(o);
        System.out.println(list);
    }

    public static void main(String[] args) {
        List list = new ArrayList(Arrays.asList(new Integer[]{1,2,3,4,5}));
        readOnly(list);
        writeOnly(list);
    }


}
