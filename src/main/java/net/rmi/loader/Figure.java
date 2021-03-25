package net.rmi.loader;

import java.io.Serializable;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public abstract class Figure implements Serializable {
    public abstract void move(int dx, int dy);
}
