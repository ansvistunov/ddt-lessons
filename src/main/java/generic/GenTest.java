package generic;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/

class Animal{}

class Dog extends Animal{}

class Cat extends Animal{}

class AnimalFeeder<T extends Animal>{
    private final T animal;
    public AnimalFeeder(T animal){
        this.animal = animal;
    }
}

public class GenTest {
    public static void main(String[] args) {
        AnimalFeeder<Dog> dogAnimalFeeder = new AnimalFeeder<>(new Dog());
        AnimalFeeder<Cat> catAnimalFeeder = new AnimalFeeder<>(new Cat());
        //AnimalFeeder<Integer> integerAnimalFeeder = new AnimalFeeder<>(new Integer(10));
    }
}
