package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public class CollectionList {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList();
        for(int i = 0; i<10; i++) integerList.add(i);
        List<Integer> l1 = new ArrayList<>(integerList);
        for(int element: integerList) System.out.print(String.format(" %d",element));
        for(int i = 0; i < l1.size(); i+=2) l1.remove(i);
        System.out.println();
        for(int element: l1) System.out.print(String.format(" %d",element));
        System.out.println();
        for(int element: integerList) System.out.print(String.format(" %d",element));
        for(int i = 0; i < integerList.size(); i+=2) integerList.remove(new Integer(i));
        System.out.println();
        for(int element: integerList) System.out.print(String.format(" %d",element));

    }

}
