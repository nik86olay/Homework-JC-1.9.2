import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException {
        String host = "netology.homework";
        int port = 8080;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String[] clientMessages = {clientSocket.getInetAddress().toString(), "yes"};

            do {
                System.out.println(in.readLine());
                if(in.readLine().contains("Welcome")) break;
                if (in.readLine().equalsIgnoreCase("Write your name")) out.println(clientMessages[0]);
                if (in.readLine().equalsIgnoreCase("Are you child? (yes/no)")) out.println(clientMessages[1]);
            } while (!clientSocket.isOutputShutdown());
        } catch (IOException e) {
            e.printStackTrace();
        }

        InetAddress inetAddress = InetAddress.getByName(host);
        System.out.println(host + ", ip address: " + inetAddress.getHostAddress());

    }
}
