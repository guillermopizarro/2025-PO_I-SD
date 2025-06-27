import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class HiloSocket extends Thread {
    
    private Socket socket;

    public HiloSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // In buffer
            InputStreamReader in_stream = new InputStreamReader(socket.getInputStream()); 
            BufferedReader in_socket = new BufferedReader( in_stream );
            // Out buffer
            OutputStreamWriter out_stream = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out_socket = new PrintWriter(out_stream, true);

            Scanner teclado = new Scanner( System.in );

            String input = "";
            String output = "";
            while ((input = in_socket.readLine()) != null) {
                System.out.println(input);

                if (input.equals("hola")) {
                    output = teclado.nextLine();
                } else if (input.equals("hora")){
                    output = (new Date()).toString();
                } else if (input.equals("adios")){
                    output = "¡Que te vaya bien!";
                    out_socket.println(output);
                    out_socket.flush();
                    break;
                }

                out_socket.println(output);
                out_socket.flush();
            }

            this.socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
