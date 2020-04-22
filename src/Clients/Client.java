package Clients;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static String hostName;
    private static int port = 2911;
    private Socket socket;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void connectToServerTower() {
        try {
            socket = new Socket(hostName, port);
            new Input(socket,this).start();
            new Output(socket, this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(hostName,port);
        client.connectToServerTower();
    }
}
