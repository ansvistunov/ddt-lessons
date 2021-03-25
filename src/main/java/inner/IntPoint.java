package inner;

/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/
class IntPoint {
    int x,y;
    int color;
    void move(int dx, int dy){x+=dx; y+=dy;}
}

class DoublePoint extends IntPoint{
    double x, y;
    void move(int dx, int dy){
        move((double)dx, (double)dy);
    }

    void move(double dx, double dy) {x+=dx; y+=dy;}
}

