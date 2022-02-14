package grpcex2;
import com.google.protobuf.Empty;
import grpc.ex2.*;
import grpc.ex2.BillingServiceStreamGrpc;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.*;
public class BillingClient {
    private static BillingServiceStreamGrpc.BillingServiceStreamStub createClient(String host, int port){
        Channel channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        return BillingServiceStreamGrpc.newStub(channel);
    }
    public static void main(String[] args) throws Exception{
        final int cardCount = 5, operationCount = 100000;
        BillingServiceStreamGrpc.BillingServiceStreamStub asyncClient = createClient("localhost",7080);
        System.out.println("Connected to server");

        final CountDownLatch addCardsLatch = new CountDownLatch(cardCount);
        WaitObserver<Empty> observer = new WaitObserver<>(addCardsLatch, t->{});
        for (int i = 1; i <= cardCount; i++) {
            AddNewCardRequest cardRequest = AddNewCardRequest.newBuilder().setCard(String.valueOf(i)).setPersonname("Client "+i).build();
            asyncClient.addNewCard(cardRequest,observer);
        }
        addCardsLatch.await(1, TimeUnit.MINUTES);

        final CountDownLatch operationLatch = new CountDownLatch(1);
        observer = new WaitObserver<>(operationLatch, t->{});
        StreamObserver<MoneyRequest> requestObserver = asyncClient.processOperation(observer);
        for(int i = 1;i <= operationCount; i++){
            int card = i % cardCount + 1;
            MoneyRequest moneyRequest = MoneyRequest.newBuilder()
                    .setCard( String.valueOf(card))
                    .setMoney(100 / card).build();
            requestObserver.onNext(moneyRequest);
        }
        requestObserver.onCompleted();
        operationLatch.await(1, TimeUnit.MINUTES);

        final CountDownLatch balanceLatch = new CountDownLatch(cardCount);
        WaitObserver<GetCardBalanceResponse> balanceObserver = new WaitObserver<>(balanceLatch, t->System.out.println("balance="+t.getBalance()));
        for (int i = 1; i <= cardCount; i++) {
            GetCardBalanceRequest cardBalanceRequest = GetCardBalanceRequest.newBuilder().setCard(String.valueOf(i)).build();
            asyncClient.getCardBalance(cardBalanceRequest, balanceObserver);
        }
        balanceLatch.await(1, TimeUnit.MINUTES);
    }
}
