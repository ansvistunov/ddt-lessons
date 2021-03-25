/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/
abstract class Figure{
    abstract void draw();
}
class Rectangle extends Figure{
    @Override
    void draw() {System.out.println("Rectangle");}
}
class Circle extends Figure{
    @Override
    void draw() {System.out.println("Circle");}
}
class AbstractPainter {
    public static void draw(Figure[] figures){
        for(Figure figure: figures) figure.draw();
    }
    public static void main(String[] args) {
        Figure[] figures = {new Rectangle(),new Circle()};
        draw(figures);
    }
}
