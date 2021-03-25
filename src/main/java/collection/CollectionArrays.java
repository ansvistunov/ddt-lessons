package collection;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public class CollectionArrays {
    public static void main(String[] args) {
        Integer[] intArray = {10,5,6,4,7,2,3,1,8,0,9};
        List<Integer> iList = Arrays.asList(intArray);
        System.out.println(iList);
        Arrays.sort(intArray);
        System.out.println(Arrays.asList(intArray));
        int index = Arrays.binarySearch(intArray,4);
        System.out.println(index);
    }
}
