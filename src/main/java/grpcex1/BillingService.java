package grpcex1;
import com.google.protobuf.Empty;
import grpc.ex1.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BillingService extends BillingServiceGrpc.BillingServiceImplBase {
    Map<String, Double> cards = new ConcurrentHashMap<>();
    public void addNewCard(grpc.ex1.AddNewCardRequest request,
                           io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        cards.putIfAbsent(request.getCard(),0.0);
        System.out.println("card added:"+cards);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
    public void addMoney(grpc.ex1.MoneyRequest request,
                         io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        cards.computeIfPresent(request.getCard(),(key,value)->value + request.getMoney());
        System.out.println("card money added:"+cards);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
    public void subMoney(grpc.ex1.MoneyRequest request,
                         io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        cards.computeIfPresent(request.getCard(),(key,value)->value - request.getMoney());
        System.out.println("card money sub:"+cards);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
    public void getCardBalance(grpc.ex1.GetCardBalanceRequest request,
                               io.grpc.stub.StreamObserver<grpc.ex1.GetCardBalanceResponse> responseObserver) {
        GetCardBalanceResponse response = GetCardBalanceResponse.newBuilder()
                .setBalance(cards.get(request.getCard()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    public static void main(String[] args) throws Exception{
        Server server = ServerBuilder
                .forPort(7080)
                .addService(new BillingService()).build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }

}
