//package hangclient;

import java.awt.Frame;
import java.io.*;
import java.net.*;
import java.util.*;

public class HangmanClient {
  public static void main(String[] args) throws Exception{
    Scanner in = new Scanner(System.in);
    String servMesg;
    String servResp;


    Socket clientSocket = new Socket("localhost", 6789);
    DataOutputStream outToServer =
        new DataOutputStream(clientSocket.getOutputStream());
    Scanner inFromServer =new Scanner(clientSocket.getInputStream());
    servMesg = inFromServer.nextLine();
    GUI gui = new GUI(Integer.parseInt(servMesg));
    boolean keepPlaying = true;
    String guess;
    while (keepPlaying)
    {
      System.out.println("Enter guess");
      guess = in.nextLine();
      outToServer.writeBytes(guess+"\n");
      servResp = inFromServer.nextLine();
      StringTokenizer st = new StringTokenizer(servResp);
      if (!(st.hasMoreTokens()))
        keepPlaying = gui.addMiss(guess);
      else
      {
        String token;
        while (st.hasMoreTokens())
        {
          token = st.nextToken();
          gui.addLetter(guess.charAt(0), Integer.parseInt(token));
        }
        keepPlaying = gui.isNotSolved();
      }

    }
    if (gui.isNotSolved())
    {
      outToServer.writeBytes("lost\n");
      System.out.println("You lose");
      String answer = inFromServer.nextLine();
      gui.setWord(answer);
    }
    else
    {
      System.out.println("You win");
      outToServer.writeBytes("won\n");
    }
    clientSocket.close();
    //System.exit(0);
  }
}