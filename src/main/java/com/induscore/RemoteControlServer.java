package com.induscore;
import java.io.*;
import java.net.*;
public class RemoteControlServer {
    private static Machine machine = new Machine();
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
        System.out.println("Server is listening on port 8080");
        while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("New client connected");
        handleClient(socket);
        }
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    private static void handleClient(Socket socket) {
        try (BufferedReader input = new BufferedReader(new
        InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(),
        true)) {
        String command;
        while ((command = input.readLine()) != null) {
        System.out.println("Received command: " + command);
        processCommand(command);
        output.println("Command executed: " + command);
    }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    private static void processCommand(String command) {
        switch (command.toLowerCase()) {
        case "start":
        machine.start();
        break;
        case "stop":
        machine.stop();
        break;
        default:
        System.out.println("Unknown command: " + command);
        break;
        }
    }
}
