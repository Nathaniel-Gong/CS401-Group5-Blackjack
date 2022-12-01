import java.io.*;
import java.net.*;
 


class createAccountServer {
 

  public createAccountServer() {

    

  }


  public static void main(String[] args) throws FileNotFoundException {
   
    ServerSocket server = null;
    try {
      
      // server is listening on port 3000
      server = new ServerSocket(3005);
      server.setReuseAddress(true);
      // running infinite loop for getting
      // client request
      while (true) {
        // socket object to receive incoming client
        // requests
        Socket client = server.accept();

        Server.Load();
    
        

        ClientHandlerCreateAccount clientSockB = new ClientHandlerCreateAccount(client);
        new Thread(clientSockB).start();

     


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
  private static class ClientHandlerCreateAccount implements Runnable {
    String givenEmail;
    String givenPassword;
    private final Socket clientSocket;

    // Constructor
    public ClientHandlerCreateAccount(Socket socket) {
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
        givenPassword = in.readLine();
 
        Account account = new Account(givenEmail, givenPassword);  

        Server.accounts.put(givenEmail, account);

 
   
        Server.save();
 
       

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