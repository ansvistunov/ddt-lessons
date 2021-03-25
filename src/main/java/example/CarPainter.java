package example;

import example.command.Command;
import example.command.DownCommand;
import example.command.RightCommand;
import example.command.Script;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : Alex
 * @created : 10.03.2021, среда
 **/
public class CarPainter extends Panel implements CarEventsListener {

    private final FieldMatrix fieldMatrix;
    private final static int prefferedCellSize = 50;
    private final static int prefferedGap = 20;

    private final Map<Car, Color> cars;

    public CarPainter(FieldMatrix fieldMatrix) {
        super();
        cars = new HashMap<>();
        Frame f = new Frame("Cars");
        f.setSize(fieldMatrix.cols * prefferedCellSize + 2 * prefferedGap, fieldMatrix.rows * prefferedCellSize + 2 * prefferedGap);
        f.add(this);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        f.setVisible(true);
        this.fieldMatrix = fieldMatrix;

    }

    @Override
    public void update(Graphics g) {

        System.out.println("update called");

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        Image screenBuffer = createImage(screenWidth,screenHeight);
        Graphics gbuffer = screenBuffer.getGraphics();

        gbuffer.setColor(Color.BLACK);

        int width = Math.min(screenHeight - 2 * prefferedGap, screenWidth - 2 * prefferedGap);
        int step = width / Math.max(fieldMatrix.cols, fieldMatrix.rows);
        for (int i = 0; i <= fieldMatrix.rows; i++)
            for (int j = 0; j <= fieldMatrix.cols; j++) {
                gbuffer.drawLine(i * step + prefferedGap, j * step + prefferedGap, fieldMatrix.rows * step, j * step + prefferedGap);
                gbuffer.drawLine(i * step + prefferedGap, j * step + prefferedGap, i * step + prefferedGap, fieldMatrix.cols * step);
                if ( i<fieldMatrix.rows && j<fieldMatrix.cols && fieldMatrix.getCellState(i,j) == FieldMatrix.CellState.WALL) {
                    gbuffer.setColor(Color.RED);
                    gbuffer.fill3DRect(j * step + prefferedGap, i * step + prefferedGap, step, step, false);
                    gbuffer.setColor(Color.BLACK);
                }
            }
        for (Map.Entry<Car,Color> entry : cars.entrySet()) {
            Position p = entry.getKey().getPosition();
            gbuffer.setColor(entry.getValue());
            gbuffer.fill3DRect(p.col * step + prefferedGap, p.row * step + prefferedGap, step, step, false);
        }
        paint(gbuffer);
        g.drawImage(screenBuffer,0,0,this);

    }

    @Override
    public void carCreated(Car car) {
        cars.put(car, new Color((int)(Math.random() * 0x1000000)));
    }

    @Override
    public void carDestroyed(Car car) {
        cars.remove(car);
    }

    @Override
    public void carMoved(Car car, Position from, Position to, boolean success) {
        repaint();
    }

    public static void main(String[] args) throws Exception{
        //FieldMatrix fm = new FieldMatrix(10, 10);

        class CarMover implements Runnable{

            private final Car car;

            CarMover(Car car){
                this.car = car;
            }
            @Override
            public void run() {
                CarServer.Direction direction = CarServer.Direction.RIGHT;
                Random random = new Random();
                while (true) {
                    try {
                        if (!car.moveTo(direction)){
                            direction = CarServer.Direction.values()[random.nextInt(4)];
                        }
                        //System.out.println(car.getPosition());
                        //Thread.sleep(500);
                    } catch (Exception e) {
                        direction = CarServer.Direction.values()[random.nextInt(4)];
                    }
                }
            }
        }


        InputStream is = CarPainter.class.getClassLoader().getResourceAsStream("Field10x10.txt");
        FieldMatrix fm = FieldMatrix.load(new InputStreamReader(is));


        CarPainter p = new CarPainter(fm);
        BasicCarServer carServer = new BasicCarServer(fm, p);
        Car car = carServer.createCar();

        new Thread(new CarMover(car)).start();
        car = carServer.createCar();
        new Thread(new CarMover(car)).start();
        car = carServer.createCar();
        new Thread(new CarMover(car)).start();
        car = carServer.createCar();
        new Thread(new CarMover(car)).start();

        /*Script script = new Script();
        Command command = new DownCommand(4,car);
        script.addCommand(command);
        command = new RightCommand(4,car);
        script.addCommand(command);
        script.execute();*/


    }
}
