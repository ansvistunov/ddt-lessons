package stat;

/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/

abstract class Figure{
    private static long count = 0;
    protected Figure(){count++;}
    public abstract void draw();
    public static long count(){return count;}
}
class Rectangle extends Figure{
    public Rectangle(){super();}
    @Override
    public void draw(){System.out.println("Rectangle");}
}

public class StaticTest {
    public static void main(String[] args) {
        new Rectangle(); System.out.println(Figure.count());
        Figure r = new Rectangle(); System.out.println(r.count());
    }
}
