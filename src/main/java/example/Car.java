package example;

import java.util.Random;

/**
 * @author : Alex
 * @created : 10.03.2021, среда
 **/
public class Car {
    private final CarServer carServer;

    private Position position;
    private static int count = 1;
    private int index;
    private int speed = 500;

    public Car(CarServer carServer, Position position){
        this.carServer = carServer;
        this.position = position;
        speed = new Random().nextInt(300) + 300;
        System.out.println(speed);
        index = count++;
    }
    public void destroy(){
        carServer.destroyCar(this);
    }
    public boolean moveTo(CarServer.Direction direction){
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (carServer.moveCarTo(this,direction)){
            position = position.move(direction);
            return true;
        }else return false;

    }
    public Position getPosition(){return position;}

    public int getIndex(){return index;}
}
