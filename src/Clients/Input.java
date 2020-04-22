package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input extends Thread {

    private BufferedReader bufferedReader;
    private Socket socket;
    private Client client;
    private InputStream inputStream;

    public Input(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String messageFromTower = bufferedReader.readLine();
                System.out.println(messageFromTower);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
