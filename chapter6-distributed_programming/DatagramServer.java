import java.net.*;
import java.io.*;
public class DatagramServer {
	public static void main(String[] args) {
		DatagramPacket datapacket, returnpacket;
		int port = 2018;
		int len = 1024;
		try {
			DatagramSocket datasocket = new DatagramSocket(port);
			byte[] buf = new byte[len];
			while (true) {
				datapacket = new DatagramPacket(buf, buf.length);
				datasocket.receive(datapacket);
				returnpacket = new DatagramPacket(
						datapacket.getData(),
						datapacket.getLength(),
						datapacket.getAddress(),
						datapacket.getPort());
				datasocket.send(returnpacket);
			}
		} catch (SocketException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

