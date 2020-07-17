import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MeinClient {

	public static void main(String[] args) {
		// hostname/IP-Adresse und port auf den der Server auf unsere nachricht horcht
		// hostname ist normalerweise immer verweis auf die IP-Adresse des Server-PCs
		String IP = "Hier IP-Adresse des Servers angeben.";
		MeinClient clientObj = new MeinClient(IP, 8000);
		clientObj.sendMessage("hallo was geht?");
	}
	
	private InetSocketAddress adresse;
	
	public MeinClient(String hostname, int port) {
		adresse = new InetSocketAddress(hostname,port);
		
	}
	
	public void sendMessage(String nachricht) {
		try{
			Socket socket = new Socket();
			socket.connect(adresse, 5000);
		// printwriter erlaubt statt nur byte weise über netzwerk zu kommunizieren, strings zu verschicken:
			PrintWriter pwObj = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pwObj.println(nachricht);
		// flush verschickt die nachricht schließlich:  
			pwObj.flush();
			pwObj.close();
			socket.close(); 
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}