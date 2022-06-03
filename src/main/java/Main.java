import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args){

        try (ServerSocket serverSocket = new ServerSocket(8080)) {// порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080)
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String userName = "";
            String clientMessage;
            String message;
            String[] serverMessages = {"Write your name", "Are you child? (yes/no)", "Welcome to the kids area, userName! Let's play!", "Welcome to the adult zone, userName! Have a good rest, or a good working day!"};

            for (int i = 0; i < serverMessages.length; i++) {
                message = serverMessages[i];
                if (i == 1) {
                    userName = in.readLine();
                }
                if (in.readLine().equalsIgnoreCase("yes")) {
                    message = (serverMessages[i].replace("userName", userName) + "\n");
                    i = serverMessages.length;
                }
                if (in.readLine().equalsIgnoreCase("no")) {
                    message = (serverMessages[serverMessages.length - 1].replace("userName", userName) + "\n");
                    i = serverMessages.length;
                }
                if(!in.readLine().isEmpty()) {
                    clientMessage = in.readLine();
                } else clientMessage = "";
                out.println(message + clientMessage);
                out.flush();
            }
            in.close();
            out.close();
            clientSocket.isClosed();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
