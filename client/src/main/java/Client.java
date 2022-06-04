import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException {
        String host = "netology.homework";
        int port = 8080;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String[] clientMessages = {clientSocket.getInetAddress().toString(), "yes"};
            int i = 0;
            String serverMessage;
            String clientMessage;

            do {
                serverMessage = in.readLine();
                System.out.println("Ready output in console " + i);
                System.out.println(serverMessage);

                if(serverMessage.contains("Welcome")){
                    System.out.println("Exit of chicle");
                    break;
                }
                clientMessage = getMessage(clientMessages, serverMessage);
                System.out.println(clientMessage);
                out.println(clientMessage);
                i++;
                System.out.println(i);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InetAddress inetAddress = InetAddress.getByName(host);
        System.out.println(host + ", ip address: " + inetAddress.getHostAddress());

    }

    private static String getMessage(String[] clientMessages, String serverMessage) {
        String clientMessage = "1111";
        if (serverMessage.contains("Write your name")) {
            clientMessage = clientMessages[0];
            return clientMessage;
        }
        if (serverMessage.contains("Are you child? (yes/no)")){
            clientMessage = clientMessages[1];
            return clientMessage;
        }
        return clientMessage;
    }
}
