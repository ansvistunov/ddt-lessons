package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Alex
 * @created : 12.03.2021, пятница
 **/
public class TextWrite {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")))) {
            for (int i = 0; i < values.length; i++) {
                out.println(values[i]);
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
