import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
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
            while ((input = in_socket.readLine()) != null) {
                System.out.println(input);

                if (input.equals("adios")){
                    break;
                }
            }

            this.socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
