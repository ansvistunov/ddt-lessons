package example.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Alex
 * @created : 11.03.2021, четверг
 **/
public class Script {
    private List<Command> commands;
    public Script(){
        commands = new ArrayList<>();
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public void execute(){
        for (Command command : commands)
            command.execute();
    }
}
