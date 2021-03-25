package util;

import java.io.FileReader;
import java.util.Properties;

/**
 * @author : Alex
 * @created : 12.03.2021, пятница
 **/
public class PropertiesExample {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileReader("main.properties"));
        System.out.println(properties.get("name"));
        System.out.println(properties.get("city"));
        System.out.println(properties.getOrDefault("city","Moscow"));
        System.out.println(properties.getOrDefault("name","Nataly"));
    }
}
