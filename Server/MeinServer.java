import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ServerSocket;
import java.net.Socket;


public class MeinServer {

	public static void main(String[] args) {
	//Port 8000 zum testen verw. , port 80 ist bspw. webserver port, port 21 ist für das FTP-Protokoll->files austauschen
		MeinServer philippsServer = new MeinServer(8000);
		philippsServer.startListening();
	}
	
	private int port;
	
	public MeinServer(int port) {
		this.port = port;
	}
	
	public void startListening() {
		// methode nutz thread vlt auch andere sachen noch gleichzeitig zu machen in diesem Programm:
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					System.out.println("[Server] Server starten..");
					
					//ServerSocket objekt repräsentiert den Server:
					ServerSocket serverSocket = new ServerSocket(port);
					System.out.println("[Server] Warten auf Verbindung...");
					
					//Socket objekt repräsentiert quasi den Client:
					Socket remoteClientSocket = serverSocket.accept();
					// wir nutzen den Scanner um aus dem inputstream zu lesen:
					Scanner sObj = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
					if(sObj.hasNextLine()) {
						//wenn neue zeile da gibt diesen aus: (wir schreiben zeilenweise)
						System.out.println("[Server] Nachricht des clients: " + sObj.nextLine());
						
					}
					sObj.close();
					remoteClientSocket.close();
					serverSocket.close();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}