package net.rmi.loader;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public class Circle extends Figure {
    private  int x;
    private  int y;
    private  int r;

    public Circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
    @Override
    public void move(int dx, int dy) {
        x+=dx; y+=dy;
    }
    @Override
    public String toString(){
        return String.format("Circle[%d,%d; radius=%d]", x, y, r);
    }
}
