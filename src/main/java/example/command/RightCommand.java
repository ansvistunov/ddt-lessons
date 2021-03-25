package example.command;

import example.Car;
import example.CarServer;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public class RightCommand extends Command<Integer> {
    public RightCommand(Integer parameter, Car car){
        super(parameter, car);
    }

    @Override
    public void execute() {
        for (int i = 0; i < parameter; i++) car.moveTo(CarServer.Direction.RIGHT);
        System.out.println("Down Command exit");
    }
}
