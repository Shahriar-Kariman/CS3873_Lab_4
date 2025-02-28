/* Example from "Computer Networking: A Top-Down Approach" by
 * James Kurose and Keith Ross, 5th Edition */

import java.io.*;
import java.net.*;

class TCPClient {
	public static void main(String args[]) throws Exception {
		QuestionProvidor q_p = QuestionProvidor.getInstance();

		while (q_p.hasMoreQuestions()) {
			Socket clientSocket = new Socket(args[0], 6789);
			DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
					
					String question = q_p.nextQuestion();
					outToServer.writeBytes(question + '\n');
					clientSocket.close();
				}
		
		// String sentence;
		// String modifiedSentence;
		// BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
		// 	System.in));
				
		// System.out.print("Enter message: ");
		// sentence = inFromUser.readLine();
		// outToServer.writeBytes(sentence + '\n');
		// modifiedSentence = inFromServer.readLine();
		// System.out.println("FROM SERVER: " + modifiedSentence);
	}
}
