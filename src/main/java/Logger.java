/**
 * @author : Alex
 * @created : 04.03.2021, четверг
 **/

public interface Logger {
    default void info(String message){
        if (!isNull(message)) log(message,"INFO");
    }
    default void warning(String message){
        if (!isNull(message)) log(message,"WARN");
    }
    default void error(String message){
        if (!isNull(message)) log(message,"ERROR");
    }
    static boolean isNull(String str) {
        return str == null ? true : "".equals(str) ? true : false;
    }
    private void log(String message, String level){
        //log message to db || file|| ...
    }
}
