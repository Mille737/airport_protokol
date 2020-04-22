package Server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class UserThreads extends Thread {

    private Socket socket;
    private ServerTower serverTower;
    private PrintWriter printWriter;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader bufferedReader;
    private String userName;
    private static LocalDateTime time;

    public UserThreads(Socket socket, ServerTower serverTower) {
        this.socket = socket;
        this.serverTower = serverTower;
    }

    public void run() {
        try {
            //Input
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            userName = bufferedReader.readLine();
            //Output
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userName.equals("Gate")) {
            gateProtocol();
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
            while(true) {
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
}
