/* Example from "Computer Networking: A Top-Down Approach" by
 * James Kurose and Keith Ross, 5th Edition */

import java.io.*;
import java.net.*;

class TCPClient {
	public static void main(String args[]) throws Exception {
		QuestionProvidor q_p = QuestionProvidor.getInstance();

		Socket clientSocket = new Socket(args[0], 6789);
		DataOutputStream outToServer = new DataOutputStream(
			clientSocket.getOutputStream());
			
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
			clientSocket.getInputStream()));
		while (q_p.hasMoreQuestions()) {
			String question = q_p.nextQuestion();
			outToServer.writeBytes(question + '\n');
			String answer = inFromServer.readLine();
			System.out.println("Answer: " + answer);
		}
		outToServer.writeBytes("DONE\n");
		String response = inFromServer.readLine();
		if(response.equals("CLOSE")){
			clientSocket.close();
			System.out.println("Connection is closed");
		}
	}
}
