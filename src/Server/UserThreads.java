package Server;

import Server.ServerTower;

import java.io.*;
import java.net.Socket;

public class UserThreads extends Thread {

    private Socket socket;
    private ServerTower serverTower;
    private PrintWriter printWriter;
    private InputStream inputStream;
    private OutputStream outputStream;

    public UserThreads(Socket socket, ServerTower serverTower) {
        this.socket = socket;
        this.serverTower = serverTower;
    }

    public void run() {
        try {
            //Input
            inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //Output
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println("Hej med dig");

    }
}
