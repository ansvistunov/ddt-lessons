package threads;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class Task {
    private String data;
    public void set(String data){
        synchronized (this){
            while( this.data != null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.data = data;
            notify();
        }

    }
    public String get(){
        synchronized (this){
            while( this.data == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String ret = data;
            data = null;
            notify();
            return ret;
        }
    }
}
