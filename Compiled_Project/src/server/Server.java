import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

import java.io.IOException;


public class Server extends Thread{
   private ServerSocket serverSocket;
   
   public Server(int port) throws IOException{
      //insert missing line here for binding a port to a socket
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000000);
   }

   public void run(){
      boolean connected = true;
      while(connected){
         try{
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            
            /* Start accepting data from the ServerSocket */
            Socket server = serverSocket.accept(); //accepting connection from client here]

            new keepAlive(server, connected).start(); // create a new thread every time the server accepts a connection

            // server.close();
            // connected = false;
            System.out.println("Server ended the connection to "+ server.getRemoteSocketAddress());
         }catch(SocketTimeoutException s){
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e){
            //e.printStackTrace();
            System.out.println("Usage: java Server");
            break;
         }
      } 
   }
}
