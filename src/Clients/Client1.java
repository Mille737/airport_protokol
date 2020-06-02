package Clients;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client1 {

    private static LocalDateTime time;
    static Boolean landingAccepted = false;
    static Boolean taxiToGate = false;
    static Boolean taxiToPark = false;
    static Boolean taxiFromGate = false;
    static Boolean takeOffAccepted = false;

    public final static Object obj = new Object();
    //Serverport same as the server
    static final int serverPort = 2911;
    static InetAddress ip;

    static {
        try {
            ip = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    static Socket socket;

    static {
        try {
            socket = new Socket(ip, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static OutputStream outputStream;

    static {
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static PrintWriter printWriter = new PrintWriter(outputStream, true);
    static InputStream inputStream;

    static {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    static Scanner scanner = new Scanner(System.in);

    public Client1() throws IOException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        //Starts threads
        MessageToTower messageToTower = new MessageToTower();
        messageToTower.start();
        MessageFromTower messageFromTower = new MessageFromTower();
        messageFromTower.start();
    }
    public static class MessageToTower extends Thread {

        @Override
        public void run() {
                while (true){

                    //message = next input
                    String messageToTower;

                    try {
                        System.out.println("Enter your user name: ");
                        String userName = scanner.nextLine();
                        printWriter.println(userName);
                        //sends message to server
                        // messageToTower = scanner.nextLine();
                        // printWriter.println(messageToTower);
                        do {
                            messageToTower = scanner.nextLine();
                            printWriter.println(messageToTower);
                            messageToTower = scanner.nextLine();
                            printWriter.println(messageToTower);

                            obj.wait();
                            printWriter.println("Landing påbegyndt. " + time.now());
                            Thread.sleep(20000);
                            printWriter.println("Er landet." + time.now());
                            landingAccepted = false;

                        if(taxiToGate = true) {
                            printWriter.println("Taxi til gate påbegyndt. " + time.now());
                            Thread.sleep(12000);
                            printWriter.println("Ankommet ved gate." + time.now());
                            taxiToGate = false;
                        }
                        else if(taxiToPark = true) {
                            printWriter.println("Taxi til venteplads påbegyndt." + time.now());
                            Thread.sleep(8000);
                            printWriter.println("Ankommet ved venteplads." + time.now());
                            taxiToPark = false;
                        }
                        else if(taxiFromGate = true) {
                            printWriter.println("Taxi fra gate påbegyndt. " + time.now());
                            Thread.sleep(10000);
                            printWriter.println("Ankommet ved start." + time.now());
                            taxiFromGate = false;
                        }
                        else if(takeOffAccepted = true) {
                            printWriter.println("Flyet letter. " + time.now());
                            takeOffAccepted = false;
                        }

                        } while (true);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
             }
    }
    public static class MessageFromTower extends Thread {
        @Override
        public void run() {
            while (true){

                try {
                    String messageFromTower = bufferedReader.readLine();
                    System.out.println(messageFromTower);
                    if(messageFromTower == null) {
                        System.out.println("System closing.");
                        System.exit(1);
                    }

                    if(messageFromTower.startsWith("Tilladelse til at lande: Accepted.") ) {
                        landingAccepted = true;
                        obj.notifyAll();
                        System.out.println("Landing påbegyndt. " + time.now());
                        Thread.sleep(20000);
                        System.out.println("Er landet." + time.now());
                    }
                    else if(messageFromTower.startsWith("Taxi til gate: Accepted.")) {
                        taxiToGate = true;
                        System.out.println("Taxi til gate påbegyndt. " + time.now());
                        Thread.sleep(12000);
                        System.out.println("Ankommet ved gate." + time.now());

                    }
                    else if(messageFromTower.startsWith("Taxi til venteplads.")) {
                        taxiToPark = true;
                        System.out.println("Taxi til venteplads påbegyndt." + time.now());
                        Thread.sleep(8000);
                        System.out.println("Ankommet ved venteplads." + time.now());
                    }
                    else if(messageFromTower.startsWith("Taxi fra gate: Accepted.")) {
                        taxiFromGate = true;
                        System.out.println("Taxi fra gate påbegyndt. " + time.now());
                        Thread.sleep(10000);
                        System.out.println("Ankommet ved start." + time.now());
                    }
                    else if(messageFromTower.startsWith("Tilladelse til at lette: Accepted.")) {
                        takeOffAccepted = true;
                        System.out.println("Flyet letter. " + time.now());
                    }

                } catch (IOException | InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
