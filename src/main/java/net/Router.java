package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Alex
 * @created : 21.03.2021, воскресенье
 **/
public class Router{
    private final int localport;
    private final int remoteport;
    private final String remotehost;


    public static void main(String[] args) {
        String remotehost = args[0];
        Integer localport = Integer.parseInt(args[1]);
        Integer remoteport = Integer.parseInt(args[2]);
        Router router = new Router(remotehost,localport,remoteport);
        router.listen();
    }


    private Router(String remotehost, int localport, int remoteport){
        this.remotehost  = remotehost;
        this.remoteport = remoteport;
        this.localport = localport;
    }


    class Runner implements Runnable{

        private final Socket socket;

        public Runner(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                final Socket destination = new Socket(remotehost, remoteport);
                System.out.println("Connected to "+remotehost);
                CompletableFuture f1 = CompletableFuture.runAsync(()-> {
                    try {
                        socket.getInputStream().transferTo(destination.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                CompletableFuture f2 = CompletableFuture.runAsync(()-> {
                    try {
                        destination.getInputStream().transferTo(socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Stream redirected. waiting.....");

            synchronized (this) {
                while (true) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void listen() {
        try {
            ServerSocket serverSocket = new ServerSocket(localport);
            while(true){
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("new connection...");
                    new Thread(new Runner(socket)).start();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
