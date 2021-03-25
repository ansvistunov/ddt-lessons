package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public class CollectionHash {
    public static void main(String[] args) {
        HashMap<Integer, String>  spaceAchievements = new HashMap<>();
        spaceAchievements.put(1957,"первый искуственный спутник земли");
        spaceAchievements.put(1961,"первый человек в космосе");
        String ach = spaceAchievements.get(1961);
        System.out.println(ach);
        String none = spaceAchievements.get(1918);
        System.out.println(none);
        none = spaceAchievements.getOrDefault(1918,"None");
        System.out.println(none);
        for(Map.Entry<Integer,String> entry : spaceAchievements.entrySet()){
            System.out.print(entry.getKey()+":");
            System.out.println(entry.getValue());
        }
    }
}
