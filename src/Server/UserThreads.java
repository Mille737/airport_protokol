package Server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class UserThreads extends Thread {

    private Socket socket;
    private ServerTower serverTower;
    private UserThreads userThreads;
    public PrintWriter printWriter;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader bufferedReader;
    private String userName;
    private static LocalDateTime time;
    Boolean responseFromTower = true;

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
        } else if(userName.equals("Bagage")) {
            baggageProtocol();
        } else if(userName.equals("Brændstof")) {
            fuelProtocol();
        } else if (userName.equals("Pilot")) {
            pilotProtocol();
        }
    }

    public void gateProtocol() {

        String choice;

        printWriter.println(
                "Tryk A: Gate crew klar til passagere.\n" +
                        "Tryk B: Boarding påbegyndt.\n" +
                        "Tryk C: Unboarding påbegyndt.\n" +
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
                        Thread.sleep(15000);
                        printWriter.println("Boarding afsluttet: Accepted. " + time.now());
                        System.out.println("Boarding afsluttet. " + time.now());
                        writeToOne("Pilot", "Boarding afsluttet.");
                        break;
                    case "C":
                        printWriter.println("Unboarding påbegyndt: Accepted. " + time.now());
                        System.out.println("Unboarding påbegyndte. " + time.now());
                        Thread.sleep(5000);
                        printWriter.println("Unboarding afsluttet: Accepted. " + time.now());
                        System.out.println("Unboarding afsluttet. " + time.now());
                        writeToOne("Brændstof", "Unbording afsluttet.");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void baggageProtocol() {

        String choice;

        printWriter.println(
                "Tryk A: Bagage crew klar.\n" +
                        "Tryk B: Bagage fyldning påbegyndt.\n" +
                        "Tryk C: Bagage aftagning påbegyndt.\n" +
                        "Tryk F: Forsinkelse på 10 min.\n" +
                        "Tryk Q: Afslutte kommunikation.");
        try {
            while (true) {
                choice = bufferedReader.readLine();
                switch (choice) {
                    case "A":
                        printWriter.println("Bagage crew klar: Accepted. " + time.now());
                        System.out.println("Bagage crew klar. " + time.now());
                        break;
                    case "B":
                        printWriter.println("Bagage fyldning påbegyndt: Accepted. " + time.now());
                        System.out.println("Bagage fyldning påbegyndt. " + time.now());
                        Thread.sleep(15000);
                        printWriter.println("Bagage fyldning afsluttet: Accepted. " + time.now());
                        System.out.println("Bagage fyldning afsluttet. " + time.now());
                        writeToOne("Pilot", "Bagage fyldning afsluttet.");
                        break;
                    case "C":
                        printWriter.println("Bagage aftagning påbegyndt: Accepted. " + time.now());
                        System.out.println("Bagage aftagning påbegyndt. " + time.now());
                        Thread.sleep(10000);
                        printWriter.println("Bagage aftagning afsluttet: Accepted. " + time.now());
                        System.out.println("Bagage aftagning afsluttet. " + time.now());
                        writeToOne("Brændstof", "Bagage aftagning afsluttet.");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fuelProtocol() {

        String choice;

        printWriter.println(
                "Tryk A: Brændstof crew klar.\n" +
                        "Tryk B: Brændstof fyldning påbegyndt.\n" +
                        "Tryk F: Forsinkelse på 10 min.\n" +
                        "Tryk Q: Afslutte kommunikation.");
        try {
            while (true) {
                choice = bufferedReader.readLine();
                switch (choice) {
                    case "A":
                        printWriter.println("Brændstof crew klar: Accepted. " + time.now());
                        System.out.println("Brændstof crew klar. " + time.now());
                        break;
                    case "B":
                        printWriter.println("Brændstof fyldning påbegyndt: Accepted. " + time.now());
                        System.out.println("Brændstof fyldning påbegyndt. " + time.now());
                        Thread.sleep(10000);
                        printWriter.println("Brændstof fyldning afsluttet: Accepted. " + time.now());
                        System.out.println("Brændstof fyldning afsluttet. " + time.now());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pilotProtocol() {

        String choice;

        printWriter.println(
                "Tryk A: Tilladelse til at lande.\n" +
                        "Tryk B: Tilladelse til taxi til gate.\n" +
                        "Tryk C: Tilladelse til taxi fra gate.\n" +
                        "Tryk D: Tilladelse til at lette.\n" +
                        "Tryk F: Forsinkelse på 10 min.\n" +
                        "Tryk Q: Afslutte kommunikation.");
        try {
            while (true) {
                choice = bufferedReader.readLine();
                switch (choice) {
                    case "A":
                        if(responseFromTower) {
                            printWriter.println("Tilladelse til at landing: Accepted. " + time.now());
                            System.out.println("Landing påbegyndt. " + time.now());
                            Thread.sleep(20000);
                            System.out.println("Er landet." + time.now());
                        }
                        break;
                    case "B":
                        if(responseFromTower) {
                            printWriter.println("Taxi til gate: Accepted. " + time.now());
                            System.out.println("Taxi til gate påbegyndt. " + time.now());
                            Thread.sleep(12000);
                            printWriter.println("Ankommet ved gate: Accepted" + time.now());
                            System.out.println("Ankommet ved gate." + time.now());
                            writeToOne("Gate","Ankommet ved gate.");
                        } else {
                            printWriter.println("Taxi til venteplads." + time.now());
                            System.out.println("Taxi til venteplads påbegyndt." + time.now());
                            Thread.sleep(8000);
                            printWriter.println("Ankommet ved venteplads: Accepted" + time.now());
                            System.out.println("Ankommet ved venteplads." + time.now());
                        }
                        break;
                    case "C":
                        if(responseFromTower) {
                            printWriter.println("Taxi fra gate: Accepted. " + time.now());
                            System.out.println("Taxi fra gate påbegyndt. " + time.now());
                            Thread.sleep(10000);
                            printWriter.println("Ankommet ved start: Accepted" + time.now());
                            System.out.println("Ankommet ved start." + time.now());
                        } else {
                            printWriter.println("Taxi til venteplads." + time.now());
                            System.out.println("Taxi til venteplads påbegyndt." + time.now());
                            Thread.sleep(8000);
                            printWriter.println("Ankommet ved venteplads: Accepted" + time.now());
                            System.out.println("Ankommet ved venteplads." + time.now());
                        }
                        break;
                    case "D":
                        printWriter.println("Tilladelse til at lette: Accepted. " + time.now());
                        System.out.println("Flyet letter. " + time.now());
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
        } catch (Exception e) {
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
