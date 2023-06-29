package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private InetAddress inetAddress;
    private PlateList restaurant;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.restaurant = new PlateList();

        inetAddress = this.clientSocket.getInetAddress();
        System.out.println("Connesso da: " + inetAddress);
    }

    boolean manage() {
        restaurant.buildPlateList();
        BufferedReader in = null;

        try {
            in = new BufferedReader(
                    new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            return false;
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            return false;
        }
        out.println("Connesso, client: " + inetAddress);

        String s = "";
        while (true) {
            try {
                if  (!((s = in.readLine()) != null)) break;
            } catch (IOException e) {
                return false;
            }
            System.out.println("Input: " + s);
            out.println("Comando inserito: " + s);
            String result = execute(s, out);
            out.println(result);
        }
        return true;
    }

    private String execute(String s, PrintWriter out) {
        Gson g = new Gson();
        Command cmd = null;

        if (s.equals("")) {
            return "Comando non riconosciuto, riprovare";
        }

        while(true) {
            try {
                cmd = g.fromJson(s, Command.class);
                break;
            } catch (Exception e) {
                return "Comando non riconosciuto, riprovare";
            }
        }

        Plate moreCaloricPlate = null;
        String plateJSON = "";
        switch (cmd.getCmd()) {
            case "more_caloric":
                double max = Double.MIN_VALUE;
                for (Plate piatto : restaurant.getPiatti()) {
                    if (piatto.getCalories() > max) {
                        System.out.println("Calorie:" + piatto.getCalories());
                        moreCaloricPlate = piatto;
                        max = piatto.getCalories();
                    }
                }
                plateJSON = g.toJson(moreCaloricPlate);
                return plateJSON;
            case "all":
                plateJSON = g.toJson(restaurant.getPiatti());
                return plateJSON;
            case "all_vegans":
                ArrayList<Plate> vegans = new ArrayList<>();
                for (Plate piatto : restaurant.getPiatti()) {
                    if (piatto.isVegan()) {
                        vegans.add(piatto);
                    }
                }
                plateJSON = g.toJson(vegans);
                return plateJSON;
        }
        return "Comando non riconosciuto";
     }

    @Override
    public void run() {
        manage();
    }
}