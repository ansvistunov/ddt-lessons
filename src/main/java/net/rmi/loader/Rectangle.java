package net.rmi.loader;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public class Rectangle extends Figure {
    private int left;
    private int top;
    private int width;
    private int height;
    public Rectangle(int left, int top, int width, int height){
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }
    @Override
    public void move(int dx, int dy) {
        left+=dx; top+=dy;
    }
    @Override
    public String toString(){
        return String.format("Rectangle[%d,%d; width=%d, height=%d]", left, top, width, height);
    }
}
