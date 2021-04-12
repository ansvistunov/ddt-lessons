package grpc.hello;
import io.grpc.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        EchoServiceGrpc.EchoServiceBlockingStub client = createClient("localhost",8080);
        System.out.println("Connected to server");
        Scanner console = new Scanner(System.in);
        String message;
        while((message = console.nextLine())!=null){
            EchoRequest request = EchoRequest.newBuilder().setMessage(message).build();
            EchoResponse response = client.echo(request);
            System.out.println("response="+response.getMessage());
        }
    }

    private static EchoServiceGrpc.EchoServiceBlockingStub createClient(String host, int port){
        Channel channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        return EchoServiceGrpc.newBlockingStub(channel);
    }
}
