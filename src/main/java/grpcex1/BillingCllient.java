package grpcex1;
import grpc.ex1.*;
import io.grpc.*;
public class BillingCllient {
    public static void main(String[] args) {
        BillingServiceGrpc.BillingServiceBlockingStub client = createClient("localhost",7080);
        System.out.println("Connected to server");
        AddNewCardRequest cardRequest = AddNewCardRequest.newBuilder().setCard("1").setPersonname("Ivan").build();
        client.addNewCard(cardRequest);
        MoneyRequest moneyRequest = MoneyRequest.newBuilder().setCard("1").setMoney(100.0).build();
        client.addMoney(moneyRequest);
        client.subMoney(moneyRequest);
        GetCardBalanceRequest balanceRequest = GetCardBalanceRequest.newBuilder().setCard("1").build();
        GetCardBalanceResponse balanceResponse = client.getCardBalance(balanceRequest);
        System.out.println("balance:"+balanceResponse.getBalance());
    }
    private static BillingServiceGrpc.BillingServiceBlockingStub createClient(String host, int port){
        Channel channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        return BillingServiceGrpc.newBlockingStub(channel);
    }
}
