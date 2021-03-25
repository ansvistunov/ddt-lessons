package generic;

import java.util.Arrays;
import java.util.List;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/



public class WildCardTest {
    public static void feedAllAnimal(List<Animal> animals){
        for(Animal animal : animals){
            System.out.println(animal);
            //кормим
        }
    }

    public static void wildcardFeedAllAnimal(List<? extends Animal> animals){
        for(Animal animal : animals){
            System.out.println(animal);
            //кормим
        }
    }

    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(new Animal[]{new Animal(), new Animal()});
        feedAllAnimal(animals);

        List<Dog> dogs = Arrays.asList(new Dog[]{new Dog(), new Dog()});
        //feedAllAnimal(dogs); //Ошибка компиляции
        wildcardFeedAllAnimal(dogs); //Ошибки нет
    }
}
