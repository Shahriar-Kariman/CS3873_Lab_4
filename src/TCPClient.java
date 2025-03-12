/* Example from "Computer Networking: A Top-Down Approach" by
 * James Kurose and Keith Ross, 5th Edition */

import java.io.*;
import java.net.*;

class TCPClient {

	public static void main(String args[]) throws Exception {
		QuestionProvidor q_p = QuestionProvidor.getInstance();
		
		Socket clientSocket = new Socket(args[0], 6789);
		DataOutputStream outToServer = new DataOutputStream(
			clientSocket.getOutputStream()
		);
			
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
			clientSocket.getInputStream())
		);
		int num = 1;
		double sum_turnAround = 0;
		int total_recieved = 0;
		while (q_p.hasMoreQuestions()) {
			String question = q_p.nextQuestion();
			System.out.println("#" + num + "----------------------------");
			System.out.println("Question from client: " + question);
			long start_time = System.currentTimeMillis();
			outToServer.writeBytes(question + '\n');
			String answer = inFromServer.readLine();
			long end_time = System.currentTimeMillis();
			System.out.println("Answer from server: " + answer);
			long elapsed_time = end_time - start_time;
			sum_turnAround += (double) elapsed_time;
			total_recieved += answer.length();
			num++;
		}
		outToServer.writeBytes("DONE\n");
		String response = inFromServer.readLine();
		total_recieved += response.length();
		if(response.equals("CLOSE")){
			clientSocket.close();
			System.out.println("Q&A END*******************************");
			int sent = outToServer.size();
			System.out.println(
				"Total characters sent: " + sent + "\n" +
				"Total characters received: " + total_recieved + "\n" +
				"Avg turn-around delay per question-answer: " + (sum_turnAround/num) + "ms."
			);
		}
	}
}
