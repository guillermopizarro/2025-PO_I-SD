import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Receiver {
    
    public Receiver() {
        
    }

    public void conectar() {
        try {
            DatagramSocket socket = new DatagramSocket(2020);

            byte[] buffer = new byte[1500]; // MTU = 1500 (Unidad máxima de transmisión)
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            socket.receive(packet);
            String mensaje = new String(buffer).trim();
            System.out.println("Mensaje recibido: " + mensaje);

            InetAddress senders_address = packet.getAddress();
            int senders_port = packet.getPort();

            System.out.println("Ingresar mensaje: ");
            Scanner consola = new Scanner(System.in);
            mensaje = consola.nextLine();
            buffer = mensaje.getBytes();
            packet = new DatagramPacket(buffer, buffer.length, senders_address, senders_port);
            socket.send(packet);

            System.out.println("Mensaje enviado: " + mensaje);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Receiver objeto = new Receiver();
        objeto.conectar();
    }
}