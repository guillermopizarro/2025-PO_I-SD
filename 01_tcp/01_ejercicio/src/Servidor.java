import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private int puerto;

    public Servidor(){
        this.puerto = 2020;
    }

    public void conectar() {
        try {
            ServerSocket server_socket = new ServerSocket(this.puerto);
            System.out.println("Port: " + this.puerto + " is open.");
            
            Socket socket = server_socket.accept();

            // In buffer
            InputStreamReader in_stream = new InputStreamReader(socket.getInputStream()); 
            BufferedReader in_socket = new BufferedReader( in_stream );
            // Out buffer
            OutputStreamWriter out_stream = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out_socket = new PrintWriter(out_stream, true);
            
            out_socket.println("Welcome!");

            String mensaje = in_socket.readLine();
            System.out.println("Client says: " + mensaje);

            socket.close();
            System.out.println("Server is close.");
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
