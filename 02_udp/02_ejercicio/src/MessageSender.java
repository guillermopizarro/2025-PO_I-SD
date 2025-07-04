import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MessageSender implements Runnable {
    
	public final static int PORT = 2020;
	private DatagramSocket socket;
	private String hostName;
	private ClientWindow window;

    public MessageSender(DatagramSocket sock, String host, ClientWindow win) {
		this.socket = sock;
		this.hostName = host;
		this.window = win;
    }

	private void sendMessage(String s) {
        try {
            byte buffer[] = s.getBytes();
            InetAddress address = InetAddress.getByName(hostName);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

    @Override
    public void run() {
		boolean connected = false;
		do {
			try {
				sendMessage("New client connected - welcome!");
				connected = true;
			} catch (Exception e) {
				window.displayMessage(e.getMessage());
			}
		} while (!connected);
		while (true) {
			try {
				while (!window.message_is_ready) {
					Thread.sleep(100);
				}
				sendMessage(window.getMessage());
				window.setMessageReady(false);
			} catch (Exception e) {
				window.displayMessage(e.getMessage());
			}
		}
    }
}
