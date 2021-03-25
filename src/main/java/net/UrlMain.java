package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : Alex
 * @created : 19.03.2021, пятница
 **/
public class UrlMain {
    public static void main(String[] args) throws Exception{
        try {
            URL url = new URL("https://www.yandex.ru:443/search/?text=Java");
            System.out.println(url.getQuery());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getProtocol());
            try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
                System.out.println(br.readLine());
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static void test() throws Exception{
        URL yandex = new URL("https","www.Yandex.ru", 443, "search/?text=java");
        URL baseURL = new URL("https://www.Yandex.ru/search/");
        URL search1URL = new URL(baseURL, "?text=Java");
        URL search2URL = new URL(baseURL, " ?text=Oracle");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(search1URL.openStream()))){
            System.out.println(br.readLine());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
