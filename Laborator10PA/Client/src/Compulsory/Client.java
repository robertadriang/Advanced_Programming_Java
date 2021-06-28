package Compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8101; // The server's port
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            // Send a request to the server
            Scanner keyboard = new Scanner(System.in);
            String request = "";
            while (request.compareTo("exit") != 0) {
                System.out.print("\nCommand: ");
                request = keyboard.nextLine();
                out.println(request);
                // Wait the response from the server ("Hello World!")
                String response = in.readLine();
                if(!request.equals("exit"))
                    System.out.println(response.replaceAll("\\|","\n")+"\n");
            }
            System.out.println("Exiting the client...");

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        } catch(SocketException e){
            System.out.printf("Socket exception. Probably timeout LOL");
        }
    }
}