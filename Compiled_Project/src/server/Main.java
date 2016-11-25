import java.io.IOException;

public class Main{
	
   /* Main Server Start */
   public static void main(String [] args){
      new UDPServer();
      
      try{
         // int port = Integer.parseInt(args[0]);  // take the port as a command input
         
         /* Start the server */
         int port = 1024;         
         Thread t = new Server(port);
         t.start();
      }catch(IOException e){
         //e.printStackTrace();
         System.out.println("Usage: java GreetingServer <port no.>");
      }catch(ArrayIndexOutOfBoundsException e){
         System.out.println("Usage: java GreetingServer <port no.> ");
      }
   }

}