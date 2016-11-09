import java.io.*;

public class MainServer{
	
   /* Main Server Start */
   public static void main(String [] args){
      try{
         int port = Integer.parseInt(args[0]);  // take the port as a command input
         
         /* Start the server */
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e){
         //e.printStackTrace();
         System.out.println("Usage: java GreetingServer <port no.>");
      }catch(ArrayIndexOutOfBoundsException e){
         System.out.println("Usage: java GreetingServer <port no.> ");
      }
   }

}