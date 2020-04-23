package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTower {

    public static int port = 2911;
    private ArrayList<UserThreads> userThreads = new ArrayList<UserThreads>();
    public List<UserThreads> getUserThreads() {
        return userThreads;
    }
    private String userName;


    public void server() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                userName = bufferedReader.readLine();

                UserThreads newConnection = new UserThreads(socket,this, userName);
                userThreads.add(newConnection);
                newConnection.start();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
