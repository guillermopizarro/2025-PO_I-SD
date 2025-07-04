import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MessageReceiver implements Runnable {
    
    DatagramSocket socket;
	byte buffer[];
	ClientWindow window;

	public MessageReceiver(DatagramSocket sock, ClientWindow win) {
		this.socket = sock;
		this.buffer = new byte[1024];
		this.window = win;
	}

	public void run() {
		while (true) {
			try {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String received = new String(packet.getData(), 1, packet.getLength() - 1).trim();
				System.out.println(received);
				window.displayMessage(received);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
