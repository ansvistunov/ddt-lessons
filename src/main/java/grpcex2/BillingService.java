package grpcex2;
import com.google.protobuf.Empty;
import grpc.ex2.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class BillingService extends BillingServiceStreamGrpc.BillingServiceStreamImplBase{
    public static void main(String[] args) throws Exception{
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new BillingService())
                //.addService(ProtoReflectionService.newInstance())
                .build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
    Map<String, Double> cards = new ConcurrentHashMap<>();
    public void addNewCard(grpc.ex2.AddNewCardRequest request,
                           io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        cards.putIfAbsent(request.getCard(),0.0);
        System.out.println("card added:"+cards);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
    public void getCardBalance(grpc.ex2.GetCardBalanceRequest request,
                               io.grpc.stub.StreamObserver<grpc.ex2.GetCardBalanceResponse> responseObserver) {
        GetCardBalanceResponse response = GetCardBalanceResponse.newBuilder()
                .setBalance(cards.get(request.getCard()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    public io.grpc.stub.StreamObserver<grpc.ex2.MoneyRequest> processOperation(
            io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        return new StreamObserver<MoneyRequest>() {
            long count = 0;
            long startTime = System.nanoTime();
            @Override
            public void onNext(MoneyRequest moneyRequest) {
                cards.computeIfPresent(moneyRequest.getCard(),(key,value)->value + moneyRequest.getMoney());
                count++;
            }
            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
            @Override
            public void onCompleted() {
                System.out.println(String.format("Stream complete. elements count=%s, seconds=%s",count, NANOSECONDS.toSeconds(System.nanoTime() - startTime)));
                System.out.println("cards="+cards);
                responseObserver.onNext(Empty.newBuilder().build());
                responseObserver.onCompleted();
            }
        };
    }
}
