import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args){

        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept(); // ждем подключения
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {// порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080)

//            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String userName = "";
            String clientMessage;
            String message;
            String[] serverMessages = {"Write your name", "Are you child? (yes/no)", "Welcome to the kids area, userName! Let's play!", "Welcome to the adult zone, userName! Have a good rest, or a good working day!"};

            System.out.println("Start message");
            for (int i = 0; i < serverMessages.length; i++) {
                message = serverMessages[i];
                if(in.ready()){
                    clientMessage = in.readLine();
                    System.out.println(clientMessage);
                } else {
                    clientMessage = "";
                }

                System.out.println("Message " + i + " output");
                if (i == 1) {
                    userName = clientMessage;
                    System.out.println("Client name " + userName);
                }
                if (clientMessage.contains("yes")) {
                    message = (serverMessages[i].replace("userName", userName));
                    out.println(message + " " + clientMessage);
                    break;
                }
                if (clientMessage.contains("no")) {
                    message = (serverMessages[3].replace("userName", userName));
                    out.println(message + " " + clientMessage);
                    break;
                }

                out.println(message + " " + clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
