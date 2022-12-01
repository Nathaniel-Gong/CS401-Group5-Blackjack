import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

  public Client() {

  }

  public Game runGame(Player p,boolean hit,boolean stand, boolean doubleDown) throws UnknownHostException, IOException, ClassNotFoundException{

    try (Socket socket = new Socket("localhost", 5512)) {

      InputStream inputStream = socket.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
      OutputStream outputStream = socket.getOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

      p.setPlayerChoice(hit, stand, doubleDown);


       

      objectOutputStream.writeObject(p);
      Game game = new Game();
    
      game.setEqualTo( (Game) objectInputStream.readObject());




     return game;



    }

     

  }

   

  public Game addPlayerToGame(Account a,boolean hit, boolean stand, boolean doubleDown,boolean again) throws UnknownHostException, IOException, ClassNotFoundException{

    try (Socket socket = new Socket("localhost", 5500)) {




   
      InputStream inputStream = socket.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
      OutputStream outputStream = socket.getOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
  
        a.getPlayer().setPlayerChoice(hit, stand, doubleDown);

       if (again == false){
          a.getPlayer().setFirstTime(true);

        }
        else{
          a.getPlayer().setFirstTime(false);
        }

           
            objectOutputStream.writeObject(a.getPlayer());
  
 
       
    
          //Next, get back the updated player (new cards for player)
    
          Game game = new Game();
    
           game.setEqualTo( (Game) objectInputStream.readObject());


 
     
          return game;
    
 

    }

  }
 
 


  public String getFunds(String email) throws UnknownHostException, IOException {

    try (Socket socket = new Socket("localhost", 7001)) {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // object of scanner class
      // writing to server
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      out.println(email);
      out.flush();

      String x = in.readLine();

      return x;
    }

  }

  public boolean checkIfAccountExists(String email) {

    boolean isCorrect = false;

    try (Socket socket = new Socket("localhost", 5005)) {

      // writing to server
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      // reading from server
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // object of scanner class

      out.println(email);
      out.flush();

      if (in.readLine().equals("true")) {

        isCorrect = true;
      } else {
        isCorrect = false;
      }

      in.close();
      out.close();

    }

    catch (Exception e) {

    }

    return isCorrect;

  }

  public boolean verifyAccount(String email, String password) {

    boolean isCorrect = false;

    try (Socket socket = new Socket("localhost", 4000)) {

      // writing to server
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      // reading from server
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // object of scanner class

      out.println(email);
      out.flush();
      out.println(password);
      out.flush();

      if (in.readLine().equals("true")) {

        isCorrect = true;
      } else {
        isCorrect = false;
      }

      in.close();
      out.close();

    }

    catch (Exception e) {

    }

    return isCorrect;

  }

  public void addAccount(Account account) {

    try (Socket socket = new Socket("localhost", 3005)) {

      // writing to server
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      // reading from server
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // object of scanner class

      out.println(account.getID());
      out.flush();
      out.println(account.getPassword());
      out.flush();

      in.close();
      out.close();

    }

    catch (Exception e) {

    }

  }

  /*
   * public static void main(String[] args){
   * 
   * 
   * 
   * 
   * // Asking for port and host number for socket temp until static
   * Scanner sc= new Scanner(System.in);
   * // System.out.print("Enter the port number to connect to: <1124>");
   * int port = sc.nextInt();
   * //System.out.print("Enter the host address to connect to: <localhost> ");
   * String host = sc.next();
   * 
   * 
   * try (Socket socket = new Socket("localhost",4000)){
   * 
   * //System.out.println("Connected to " + host + ":" + port);
   * 
   * OutputStream outputStream = socket.getOutputStream();
   * ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
   * 
   * InputStream inputStream = socket.getInputStream();
   * ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
   * 
   * 
   * objectOutputStream.writeObject(account);
   * 
   * 
   * 
   * while (d.getDealerHandTotal()>17 && d.getDealerHandTotal()<22 && ISBLACKJACK
   * == FALSE)
   * {
   * 
   * 
   * try {
   * 
   * Account.player = (Player)objectInputStream.readObject();
   * 
   * }
   * catch (Exception e) {
   * }
   * if (Dealer.getDealerHandTotal()<17){
   * Card c = deck.getCard();
   * deck.popCard();
   * dealer.addCardToDealer(c);
   * }
   * 
   * if (Dealer.getDealerHandTotal()>=17 && Dealer.getDealerHandTotal()<22){
   * 
   * player.getPlay();
   * 
   * break;
   * 
   * //DEALER IS STILL IN GAME
   * }
   * if (Dealer.getDealerHandTotal() > 22){
   * system.out.println("Bust current hand total is ", getDealerHandTotal());
   * break;
   * //DEALER LOSES
   * }
   * 
   * }
   * 
   * sc.close();
   * objectOutputStream.close();
   * objectInputStream.close();
   * 
   * }
   * catch (IOException e) {
   * e.printStackTrace();
   * }
   * }
   */
}
