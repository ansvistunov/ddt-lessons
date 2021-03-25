package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;
import java.util.Scanner;

/**
 * @author : Alex
 * @created : 25.03.2021, четверг
 **/
public class HTTPServer {
    public static final int port = 8000;
    public static final String targetDirectory = "/target/classes";

    public static void main(String[] args) throws Exception{
        File currentDirectory = new File("");
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {
            Socket socket = serverSocket.accept();
            try (Scanner scanner = new Scanner(socket.getInputStream())) {
                String query = scanner.nextLine();
                String[] tokens = query.split("\\s");
                String filename = tokens[1];
                System.out.println("request for "+filename);

                Path file = Paths.get(currentDirectory.getAbsolutePath()+targetDirectory+tokens[1]);
                InputStream is = Files.newInputStream(file, StandardOpenOption.READ);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                is.transferTo(baos);

                byte[] data = baos.toByteArray();
                OutputStream out = socket.getOutputStream();
                out.write("HTTP/1.0 200 OK\r\n".getBytes());
                out.write("Date: Fri, 31 Dec 2020 23:59:59 GMT\r\n".getBytes());
                out.write("Server: Apache/0.8.4\r\n".getBytes());
                out.write("Content-Type: application/java\r\n".getBytes());
                out.write(String.format("Content-Length: %d\r\n",data.length).getBytes());
                out.write("Connection: close\r\n".getBytes());
                out.write("\r\n".getBytes());
                new ByteArrayInputStream(data).transferTo(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
