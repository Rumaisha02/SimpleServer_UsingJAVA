import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class HttpResponseReturn {
    public static void main(String args[]) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(80);
            System.out.println("Listening for connection on port 80");

            while (true) {
                try (Socket socket = server.accept()) {
                    Date today = new Date();
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                    socket.getOutputStream()
                          .write(httpResponse.getBytes("UTF-8"));
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        } finally {
            if (server != null && !server.isClosed()) {
                try {
                    server.close();
                    System.out.println("Server socket closed.");
                } catch (IOException e) {
                    System.out.println("Error closing server: " + e.getMessage());
                }
            }
        }
    }
}
