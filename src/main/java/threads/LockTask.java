package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Alex
 * @created : 14.03.2021, воскресенье
 **/
public class LockTask {
    private String data;
    private ReentrantLock lock;
    private Condition condition;

    public LockTask(){
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public void set(String data){
        lock.lock();
        try {
            while (this.data != null) condition.await();
            this.data = data;
            condition.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public String get(){
        lock.lock();
        try{
            while(this.data == null) condition.await();
            String ret = data;
            data = null;
            condition.signalAll();
            return ret;
        }catch (InterruptedException e){
            e.printStackTrace(); throw new RuntimeException(e.getCause());
        }finally {
            lock.unlock();
        }
    }
}
