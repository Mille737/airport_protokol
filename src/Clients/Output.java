package Clients;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Output extends Thread {
    private PrintWriter printWriter;
    private Socket socket;
    private Client client;
    private OutputStream outputStream;

    public Output(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String messageToTower;
        try {
            do {
                messageToTower = scanner.nextLine();
                printWriter.println(messageToTower);
            } while (!messageToTower.equals("Quit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
