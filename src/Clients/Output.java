/* package Clients;

import Server.ServerTower;
import Server.UserThreads;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Output extends Thread {

    private ServerTower serverTower;
    private Client client;
    private OutputStream outputStream;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String userName;
    private static LocalDateTime time;
    private Output output;


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
                Input input = new Input();
                //String messageFromTower = bufferedReader.readLine();
                System.out.println("test" + messageToTower + input.getMessageFromTower());
                //UserThreads userThreads = new UserThreads(socket, serverTower, userName);
                //userThreads.writeToOne("Pilot", "test");


                /*if(messageFromTower.startsWith("Taxi til gate: Accepted.")) {
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
                } if(true) {
                    //System.out.println(input.getMessageFromTower() + time.now());
                }
            } while (!messageToTower.equals("Quit"));
        } catch (Exception e) {
            System.out.println("jeg er her");
            e.printStackTrace();
        }
    }
    public void landingResponse() throws InterruptedException {

        //userThreads.writeToOne("Pilot", "test" + time.now());
        System.out.println("Landing påbegyndt. " + time.now());
        output.printWriter.println("Landing påbegyndt. " + time.now());
        Thread.sleep(20000);
        System.out.println("Er landet." + time.now());
        output.printWriter.println("Er landet." + time.now());
    }
}
*/
