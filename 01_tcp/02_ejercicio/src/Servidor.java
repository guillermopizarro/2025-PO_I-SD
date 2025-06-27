import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public final int PORT = 2020;

    public Servidor(){

    }

    public void conectar() {
        try {
            ServerSocket server_socket = new ServerSocket(PORT);

            Socket socket;
            
            while (true) { 
                socket = server_socket.accept();
                System.out.println("Cliente conectado.");

                HiloSocket hilo = new HiloSocket(socket);
                hilo.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.conectar();
    }
}
