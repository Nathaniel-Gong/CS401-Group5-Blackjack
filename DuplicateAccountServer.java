import java.io.*;
import java.net.*;
 


class DuplicateAccountServer {
 

  public DuplicateAccountServer() throws FileNotFoundException {

   

  }


  public static void main(String[] args) {
    ServerSocket server = null;
    try {
      // server is listening on port 5000
      server = new ServerSocket(5005);
      server.setReuseAddress(true);
      // running infinite loop for getting
      // client request
      while (true) {
        Server.Load();
        // socket object to receive incoming client
        // requests
        Socket client = server.accept();
    
        

        ClientHandlerCheckDuplicateAccount clientSockC =new ClientHandlerCheckDuplicateAccount(client);
        new Thread(clientSockC).start();


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
 private static class ClientHandlerCheckDuplicateAccount implements Runnable {
  String givenEmail;
   private final Socket clientSocket;

  // Constructor
  public ClientHandlerCheckDuplicateAccount(Socket socket) {
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

      givenEmail = in.readLine();
       

 
     if (Server.accounts.containsKey(givenEmail)){

      PrintWriter outX = new PrintWriter(clientSocket.getOutputStream(), true);

      outX.println("true");

     }

     else{
       PrintWriter outX = new PrintWriter(clientSocket.getOutputStream(), true);
      outX.println("false");
   
     }

     

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