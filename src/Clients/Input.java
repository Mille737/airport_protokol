/*package Clients;

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
    private String messageFromTower;

    public String getMessageFromTower() {
        return messageFromTower;
    }
    public void setMessageFromTower(String messageFromTower) {
        this.messageFromTower = messageFromTower;
    }

    public Input(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }
    public Input(){}

    Output output = new Output(socket, client);

    public void run() {
        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                setMessageFromTower(bufferedReader.readLine());
                if(messageFromTower == null) {
                    System.out.println("System closing.");
                    System.exit(1);
                }
                if(messageFromTower.startsWith("Tilladelse til at lande: Accepted.")) {
                    output.landingResponse();
                }
                System.out.println(messageFromTower);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
*/
