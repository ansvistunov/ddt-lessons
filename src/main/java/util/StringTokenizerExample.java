package util;

import java.io.StreamTokenizer;
import java.util.StringTokenizer;

/**
 * @author : Alex
 * @created : 12.03.2021, пятница
 **/
public class StringTokenizerExample {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("HelloServer, world",",");
        while (st.hasMoreTokens()) {
            System.out.print(st.nextToken() + "_");
        }

    }


}
