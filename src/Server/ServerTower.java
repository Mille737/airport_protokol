package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerTower {

    public static int port = 2911;
    private Set<UserThreads> userThreads = new HashSet<>();


    public void server() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();

                UserThreads newConnection = new UserThreads(socket,this);
                userThreads.add(newConnection);
                newConnection.start();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
