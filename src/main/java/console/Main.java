package console;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Dot;
import com.indvd00m.ascii.render.elements.Rectangle;
import ex.Ex;

/**
 * @author : Alex
 * @created : 10.03.2021, среда
 **/
public class Main {
    private final char escCode = 0x1B;
    public void gotoXY(short row, short column){
        System.out.print(String.format("%c[%d;%df",escCode,row,column));
    }

    public static void main(String[] args) throws Exception{

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        /*
        The DefaultTerminalFactory can be further tweaked, but we'll leave it with default settings in this tutorial.
         */

        Terminal terminal = null;
        try {
            /*
            Let the factory do its magic and figure out which implementation to use by calling createTerminal()
             */
            terminal = defaultTerminalFactory.createTerminal();

            /*
            If we got a terminal emulator (probably Swing) then we are currently looking at an empty terminal emulator
            window at this point. If the code ran in another terminal emulator (putty, gnome-terminal, konsole, etc) by
            invoking java manually, there is yet no changes to the content.
             */

            /*
            Let's print some text, this has the same as calling System.out.println("Hello");
             */

            Thread.sleep(2000);

            /*
            At this point the cursor should be on start of the next line, immediately after the Hello that was just
            printed. Let's move the cursor to a new position, relative to the current position. Notice we still need to
            call flush() to ensure the change is immediately visible (i.e. the user can see the text cursor moved to the
            new position).
            One thing to notice here is that if you are running this in a 'proper' terminal and the cursor position is
            at the bottom line, it won't actually move the text up. Attempts at setting the cursor position outside the
            terminal bounds are usually rounded to the first/last column/row. If you run into this, please clear the
            terminal content so the cursor is at the top again before running this code.
             */

            for(int i=1;i<10;i++)
                for (int j=1;j<10;j++) {

                    TerminalPosition startPosition = terminal.getCursorPosition();

                    //terminal.setCursorPosition(startPosition.withRelativeColumn(3).withRelativeRow(2));
                    terminal.clearScreen();
                    //Thread.sleep(100);
                    terminal.setCursorPosition(i,j);
                    terminal.putCharacter('*');
                    terminal.flush();
                    terminal.setCursorPosition(1,1);
                    Thread.sleep(100);
                }


        }catch (Exception e){

        }
    }
}
