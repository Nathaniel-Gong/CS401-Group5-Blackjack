import java.io.*;
import java.net.*;
 
  
// Server class
class getAccountFundInfoServer {
   
 
 
  public static File myObj = new File("AccountData.txt");

  public getAccountFundInfoServer(){
    
 

  }

 

  public static void main(String[] args) throws FileNotFoundException {
    ServerSocket server = null;
    

     
     
 
    try {
      // server is listening on port 7000
      server = new ServerSocket(7001);
      server.setReuseAddress(true);
      // running infinite loop for getting
      // client request
      while (true) {

        Server.Load();

 
        
        // socket object to receive incoming client
        // requests
        Socket client = server.accept();
    
        // create a new thread object
        ClientHandlerFundInfo clientSock = new ClientHandlerFundInfo(client);
        // This thread will handle the client
        // separately
        new Thread(clientSock).start();
 
        


      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (server != null) {
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    
    
  }

  // ClientHandler class
  private static class ClientHandlerFundInfo implements Runnable {
 
    private final Socket clientSocket;

    // Constructor
    public ClientHandlerFundInfo(Socket socket) {
      this.clientSocket = socket;
    }

    public void run() {
      PrintWriter out = null;
     
      BufferedReader in = null;
      try {
        // get the outputstream of client
        out = new PrintWriter(
            clientSocket.getOutputStream(), true);
        // get the inputstream of client
        in = new BufferedReader(
            new InputStreamReader(
                clientSocket.getInputStream()));

        String givenEmail = in.readLine();
       
     
      System.out.println(givenEmail);
         
       
            out.println(String.valueOf(Server.accounts.get(givenEmail).returnAmount()));
        

             
         

       
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (out != null) {
            out.close();
          }
          if (in != null) {
            in.close();
            clientSocket.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

 

 


 

 



 

}