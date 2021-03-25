package example.command;

import example.Car;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public abstract class Command<T> {
    protected final T parameter;
    protected final Car car;

    public Command(T parameter, Car car){
        this.parameter = parameter;
        this.car = car;
    }
    public abstract void execute();
}
