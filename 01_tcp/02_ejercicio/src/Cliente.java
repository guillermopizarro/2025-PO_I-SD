import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private int puerto;
    private String ip;

    public Cliente() {
        this.puerto = 2020;
        this.ip = "localhost";
    }

    public void conectar() {
        try {
            Socket socket = new Socket(this.ip, this.puerto);
            System.out.println("Conexión exitosa al servidor.");

            // In buffer
            InputStreamReader in_stream = new InputStreamReader(socket.getInputStream()); 
            BufferedReader in_socket = new BufferedReader( in_stream );
            // Out buffer
            OutputStreamWriter out_stream = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out_socket = new PrintWriter(out_stream, true);

            Scanner teclado = new Scanner( System.in );

            while (true) {
                String input = teclado.nextLine(); 
                System.out.println("Cliente dice: ");
                out_socket.println(input);
                out_socket.flush();

                if (input.equals("adios")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Client is close.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.conectar();
    }
}
