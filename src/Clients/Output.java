package Clients;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Output extends Thread {
    private PrintWriter printWriter;
    private Socket socket;
    private Client client;
    private OutputStream outputStream;
    private String userName;
    private BufferedReader bufferedReader;
    private static LocalDateTime time;


    public Output(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
            InputStream inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String messageToTower;
            System.out.println("Enter your user name: ");
            userName = scanner.nextLine();
            printWriter.println(userName);
            do {
                messageToTower = scanner.nextLine();
                printWriter.println(messageToTower);
                String messageFromTower = bufferedReader.readLine();

                if(messageFromTower.startsWith("Tilladelse til at lande: Accepted.")) {
                    System.out.println(messageFromTower + time.now() + "j");
                    System.out.println("Landing påbegyndt. " + time.now());
                    printWriter.println("Landing påbegyndt. " + time.now());
                    Thread.sleep(20000);
                    System.out.println("Er landet." + time.now());
                    printWriter.println("Er landet." + time.now());
                }
                if(messageFromTower.startsWith("Taxi til gate: Accepted.")) {
                    System.out.println(messageFromTower);
                    System.out.println("Taxi til gate påbegyndt. " + time.now());
                    printWriter.println("Taxi til gate påbegyndt. " + time.now());
                    Thread.sleep(12000);
                    System.out.println("Ankommet ved gate." + time.now());
                    printWriter.println("Ankommet ved gate." + time.now());
                }
                if(messageFromTower.startsWith("Taxi til venteplads.")) {
                    System.out.println("Taxi til venteplads påbegyndt." + time.now());
                    printWriter.println("Taxi til venteplads påbegyndt." + time.now());
                    Thread.sleep(8000);
                    System.out.println("Ankommet ved venteplads." + time.now());
                    printWriter.println("Ankommet ved venteplads." + time.now());
                }
                if(messageFromTower.startsWith("Taxi fra gate: Accepted.")) {
                    System.out.println("Taxi fra gate påbegyndt. " + time.now());
                    printWriter.println("Taxi fra gate påbegyndt. " + time.now());
                    Thread.sleep(10000);
                    System.out.println("Ankommet ved start." + time.now());
                    printWriter.println("Ankommet ved start." + time.now());
                }
                if(messageFromTower.startsWith("Tilladelse til at lette: Accepted.")) {
                    System.out.println("Flyet letter. " + time.now());
                    printWriter.println("Flyet letter. " + time.now());
                } else {
                    System.out.println(messageFromTower + time.now());
                }
            } while (!messageToTower.equals("Quit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
