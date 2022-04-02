import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(8089);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("New connection accepted");
                final String greetings = in.readLine();
                out.println(String.format("Hi %s, your port is %d, Write your name ", greetings, clientSocket.getPort()));

                final String question = in.readLine();
                out.println(String.format("Hi %s, Are you child? (yes/no) ", question));

                final String questionChild = in.readLine();
                System.out.println(questionChild);

                if(questionChild.equals("yes")){
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", question));
                } else {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", question));
                }

                //serverSocket.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }


}
