package Server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class UserThreads extends Thread {

    private Socket socket;
    private ServerTower serverTower;
    private UserThreads userThreads;
    private PrintWriter printWriter;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader bufferedReader;
    private String userName;
    private static LocalDateTime time;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserThreads(Socket socket, ServerTower serverTower, String userName) {
        this.socket = socket;
        this.serverTower = serverTower;
        this.userName = userName;
    }

    public void run() {
        try {
            //Input
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //Output
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userName.equals("Gate")) {
            gateProtocol();
        } else {
            printWriter.println(userName + " Accepted.");
        }
    }

    public void gateProtocol() {

        String choice;

        printWriter.println(
                "Tryk A: Gate crew klar til passagere.\n" +
                        "Tryk B: Boarding påbegyndt.\n" +
                        "Tryk C: Boarding afsluttet.\n" +
                        "Tryk D: Unboarding påbegyndt.\n" +
                        "Tryk E: Unboarding afsluttet. \n" +
                        "Tryk F: Forsinkelse på 10 min.\n" +
                        "Tryk Q: Afslutte kommunikation.");
        try {
            while (true) {
                choice = bufferedReader.readLine();
                switch (choice) {
                    case "A":
                        printWriter.println("Gate crew klar til passagere: Accepted. " + time.now());
                        System.out.println("Gate crew klar til passagere. " + time.now());
                        break;
                    case "B":
                        printWriter.println("Boarding påbegyndt: Accepted. " + time.now());
                        System.out.println("Boarding påbegyndt. " + time.now());
                        break;
                    case "C":
                        printWriter.println("Boarding afsluttet: Accepted. " + time.now());
                        System.out.println("Boarding afsluttet. " + time.now());
                        break;
                    case "D":
                        printWriter.println("Unboarding påbegyndt: Accepted. " + time.now());
                        System.out.println("Unboarding påbegyndte. " + time.now());
                        break;
                    case "E":
                        printWriter.println("Unboarding afsluttet: Accepted. " + time.now());
                        System.out.println("Unboarding afsluttet. " + time.now());
                        writeToOne("Brændstof", "Påbegynd brændstof påfyldning.");
                        break;
                    case "F":
                        printWriter.println("Forsinkelse på 10 min: Accepted. " + time.now());
                        System.out.println("Forsinkelse på 10 min. " + time.now());
                        break;
                    case "Q":
                        printWriter.println("Afslutte kommunikation: Accepted. " + time.now());
                        System.out.println("Afslutte kommunikation. " + time.now());
                        System.exit(1);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void writeToOne(String userName, String message) {
            for (int i = 0; i < serverTower.getUserThreads().size(); i++) {
                userThreads = serverTower.getUserThreads().get(i);

                //if the written username is in list continue
                if (userName.equalsIgnoreCase(serverTower.getUserThreads().get(i).getUserName())) {
                    try {
                        userThreads.printWriter.println(message);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


}
