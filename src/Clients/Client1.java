package Clients;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client1 {

    private static LocalDateTime time;
    static Boolean landingAccepted = false;
    static Boolean taxiToGate = false;
    static Boolean taxiToPark = false;
    static Boolean taxiFromGate = false;
    static Boolean takeOffAccepted = false;

    public static void main(String[] args) throws IOException {

        //Serverport same as the server
        final int serverPort = 2911;
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, serverPort);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream, true);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        Scanner scanner = new Scanner(System.in);

        //The syntax of an anonymous class expression is like the invocation of a constructor,
        // except that there is a class definition contained in a block of code.
        //The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
        // The class must define a method of no arguments called run.
        Thread messageToTower = new Thread(new Runnable() {

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

                        if(landingAccepted == true) {
                            printWriter.println("Landing påbegyndt. " + time.now());
                            Thread.sleep(20000);
                            printWriter.println("Er landet." + time.now());
                            landingAccepted = false;
                        }
                        else if(taxiToGate == true) {
                            printWriter.println("Taxi til gate påbegyndt. " + time.now());
                            Thread.sleep(12000);
                            printWriter.println("Ankommet ved gate." + time.now());
                            taxiToGate = false;
                        }
                        else if(taxiToPark == true) {
                            printWriter.println("Taxi til venteplads påbegyndt." + time.now());
                            Thread.sleep(8000);
                            printWriter.println("Ankommet ved venteplads." + time.now());
                            taxiToPark = false;
                        }
                        else if(taxiFromGate == true) {
                            printWriter.println("Taxi fra gate påbegyndt. " + time.now());
                            Thread.sleep(10000);
                            printWriter.println("Ankommet ved start." + time.now());
                            taxiFromGate = false;
                        }
                        else if(takeOffAccepted == true) {
                            printWriter.println("Flyet letter. " + time.now());
                            takeOffAccepted = false;
                        } else {
                            messageToTower = scanner.nextLine();
                            printWriter.println(messageToTower);
                        }

                        } while (true);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        //creates new thread for message to be read
        Thread messageFromTower = new Thread(new Runnable() {

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
                        else if(messageFromTower.startsWith("Tilladelse til at lande: Accepted.")) {
                            landingAccepted = true;
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
        });
        //Starts threads
        messageToTower.start();
        messageFromTower.start();
    }
}
