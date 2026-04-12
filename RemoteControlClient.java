package com.induscore;
import java.io.*;
import java.net.*;
public class RemoteControlClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
        PrintWriter output = new PrintWriter(socket.getOutputStream(),
        true);
        BufferedReader input = new BufferedReader(new
        InputStreamReader(System.in))) {
        String command;
        System.out.println("Enter commands (start/stop) or 'exit' to quit:");
        while (true) {
        command = input.readLine();
        if ("exit".equalsIgnoreCase(command)) {
        break;
        }
        output.println(command);
    System.out.println("Sent: " + command);
    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}