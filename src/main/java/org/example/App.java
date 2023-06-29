package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    static int portNumber = 1234;
    static ServerSocket serverSocket;

    static boolean startServer() {

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args){

        if (!startServer()){
            return;
        }
        System.out.println("Server avviato!");
        while (true) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
}