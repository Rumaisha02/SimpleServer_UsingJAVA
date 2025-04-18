
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);
            System.out.println("Listening for connection on port 8080....");
            
            while (true) {
                Socket clientSocket = server.accept();
                InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();
                while (line != null && !line.isEmpty()) {
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
        } finally {
            if (server != null && !server.isClosed()) {
                server.close();
                System.out.println("Server socket closed.");
            }
        }
    }
}




