import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

class GamePlayServer {

  final static long MAX_TIME = 10;
  static Vector<Game> games;
  // private int MAX_PLAYERS = 2;

 

  public GamePlayServer() {

  }

  public static void main(String []args) throws FileNotFoundException {

    ServerSocket server = null;
    games = new Vector<>();
    games.clear();
    
     
    
    try {

         

      
      

      server = new ServerSocket(5500);
      server.setReuseAddress(true);
      // running infinite loop for getting
      // client request
      while (true) {
 
        boolean allGamesFull = true;
        int indexA = 0;

        for (int i = 0; i < games.size(); i++) {
          if (games.get(i).isGameFull == false) {

            allGamesFull = false;
            indexA = i;

            break;

          }

        

        }



        if (allGamesFull == true) {
          Game game = new Game();
          GamePlayServer.games.add(game);
          indexA = games.size() - 1;
        }
      


        
        

        Server.Load();

        // socket object to receive incoming client
        // requests
        Socket client = server.accept();

        ClientHandlerPlayGame clientSockB = new ClientHandlerPlayGame(client,indexA);
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
  private static class ClientHandlerPlayGame implements Runnable {

    
    
    OutputStream outputStream;
    ObjectOutputStream objectOutputStream;

    InputStream inputStream;
    ObjectInputStream objectInputStream;

    private final Socket clientSocket;

    private int index;
    // Constructor
    public ClientHandlerPlayGame(Socket socket,int i) {
      this.clientSocket = socket;
      index = i;
    }

    public void run() {
 
      try {



        
 
        outputStream = clientSocket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);

        inputStream = clientSocket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        Player newPlayer = new Player();

        newPlayer.setEqualTo((Player) objectInputStream.readObject());

      

        for (int i=0;i<games.size();i++){
          if (games.get(i).checkIfPlayerExists(newPlayer.returnID())){
            index = i;
          
            break;
          }
        }

    

 
 

        if (newPlayer.getFirstTime() == true ) {
          
           
          GamePlayServer.games.get(index).addPlayer(newPlayer);

        }

        int playerIndex = games.get(index).getPlayerIndex(newPlayer);

           if (newPlayer.getHitBoolean() == true)
          {
             GamePlayServer.games.get(index).runPlayersTurn(newPlayer.getHitBoolean(), newPlayer.getStandBoolean(), newPlayer.getdoubleDownBoolean());
            
          }

          if (newPlayer.isKicked == true){
            games.get(index).removePlayer(newPlayer);
          }

          if (newPlayer.getStandBoolean() == true){
            
       

            GamePlayServer.games.get(index).runPlayersTurn(false, newPlayer.getStandBoolean(), false);


          }
          
          if (games.get(index).checkIfPlayerBusts(playerIndex) == true){
            games.get(index).getSpecificIndex(games.get(index).currentPlayerIndexTurn).setBust(true); 
          }
         

   
        objectOutputStream.writeObject(games.get(index));
 
         
          
      }

      

      catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      } finally {

        try {
          if (objectInputStream != null) {
            objectInputStream.close();
          }
          if (objectOutputStream != null) {

            objectOutputStream.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }

      }

    }

  }

}