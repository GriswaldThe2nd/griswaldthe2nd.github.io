/* Jack Erlandson
   CMSCI 355
   Fall 2021
   Hangman Project: Server
*/
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class HangmanServer {
  public static void main(String[] args) throws Exception {
    ServerSocket welcomeSocket = new ServerSocket(6789); // create welcome socket
    String clientMessage; //Message for client
    while (true)
    {
      Socket connSocket = welcomeSocket.accept(); //Accept the socket connection
      Scanner inFromClient = new Scanner(connSocket.getInputStream()); //Get input
      DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream()); //Create output stream to client
      Word w = new Word("rutabaga"); // ** Word ** 
      outToClient.writeBytes(""+w.getLength()+'\n'); //Write the bytes to client
      clientMessage = inFromClient.nextLine();
      while (!(clientMessage.equals("won") || clientMessage.equals("lost"))) // If the client has not won or if they have lost
      {
        char letter = clientMessage.charAt(0); //first character of the message
        
        outToClient.writeBytes(w.getSpots(letter)+"\n"); //get all the spots of the word
        System.out.println(w.getSpots(letter)); // print the different character spots of the word
        clientMessage = inFromClient.nextLine(); // prompt for new message from client
      }
      if (clientMessage.equals("lost"))
        outToClient.writeBytes(w.getWord()+"\n"); //Send the word if the client has lost the game
      connSocket.close();
    }
  }
}
