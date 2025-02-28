/* Example from "Computer Networking: A Top-Down Approach" by
 * James Kurose and Keith Ross, 5th Edition */


import java.io.*;
import java.net.*;

class TCPServer {
	public static void main(String args[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		System.out.println ("Waiting for connection.....");

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();

			// Experiment to block some IPs
			
			// String ClinetIP = connectionSocket.getInetAddress().getHostAddress();
			// String blockedIP = "127.0.0.1";

			// if (ClinetIP.equals(blockedIP)){
			// 	System.out.println("fuck off!");
			// 	continue;
			// }

			System.out.println ("Connection successful");
		        System.out.println ("Waiting for input.....");
						
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			

			clientSentence = inFromClient.readLine();

			System.out.println("From client at " + connectionSocket.getInetAddress() 
				+ ": " + clientSentence);

			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);	
			
		}
	}
}
