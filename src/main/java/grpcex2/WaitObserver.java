package grpcex2;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
public class WaitObserver<T> implements StreamObserver<T> {
    private final CountDownLatch latch;
    private final Consumer<T> consumer;
    public WaitObserver(CountDownLatch latch, Consumer<T> consumer){
        this.latch = latch;
        this.consumer = consumer;
    }
    @Override
    public void onNext(T t) {
        consumer.accept(t);
    }
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
    @Override
    public void onCompleted() {
        latch.countDown();
    }
}
