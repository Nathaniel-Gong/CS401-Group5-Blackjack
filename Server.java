import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
 
// Server class
class Server {
  public static HashMap<String,Account> accounts = new HashMap<String,Account>();
  public static File myObj = new File("AccountData.txt");

  public Server(){
 
 
    
 

  }

  public static void Load() throws FileNotFoundException{
 
    
 
 
  Scanner myReader = new Scanner(myObj);
  while (myReader.hasNextLine()) {
     
    String userName = myReader.nextLine();
 
     
    String pw = myReader.nextLine();

    
    
    String amount = myReader.nextLine();
 
    
  
    Account account = new Account(userName,pw);
    account.setAmount(amount);
    Server.accounts.put(userName,account);
 
  }

  myReader.close();

   
   
 
 
  }

  public static void main(String[] args) throws FileNotFoundException {
    ServerSocket server = null;
    

     
     
 
    try {
      // server is listening on port 4000
      server = new ServerSocket(4000);
      server.setReuseAddress(true);
      // running infinite loop for getting
      // client request
      while (true) {

        Server.Load();

 
        
        // socket object to receive incoming client
        // requests
        Socket client = server.accept();
    
        // create a new thread object
        ClientHandlerLogin clientSock = new ClientHandlerLogin(client);
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
  private static class ClientHandlerLogin implements Runnable {
    String givenEmail;
    String givenPassword;
    private final Socket clientSocket;

    // Constructor
    public ClientHandlerLogin(Socket socket) {
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

      
        
          if (Server.accounts.containsKey(givenEmail) == true && accounts.get(givenEmail).getPassword().equals(givenPassword)){
           
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

 

 



public static void save(){

 



  try (FileWriter out = new FileWriter(myObj)) {

    for (String key : accounts.keySet()) {

      out.write(accounts.get(key).getID());
      out.write("\n");
      out.write(accounts.get(key).getPassword());
      out.write("\n");
      out.write(String.valueOf(accounts.get(key).returnAmount()));
      out.write("\n");
     
  }


  out.close();
}
catch(Exception e){
  e.printStackTrace();
}


}

 



 

}