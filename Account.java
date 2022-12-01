 
import java.io.Serializable;
public class Account implements Serializable{

  //  Private Variables
  private static final long serialVersionUID = 6529685098267757690L;
  private String userID;
  private String password;
  private int amount;
  public boolean isLoggedIn = false;
 
  private boolean isLocked;
  public Player player;

  public void setBooleanHitPlayer(boolean hit){
    player.setBooleanHit(hit);
  }
  public void setBooleanstand(boolean s){
    player.setStand(s);
  }
  public void setIsLoggedInPlayer(boolean f){
    player.isLoggedInPlayer = f;
  }
  public void setID(String id){
    userID = id;
  }
  public void setpassword(String p){
    password = p;
  }
 
 
  public void setAmount(int p){
    amount = p;
  }

  public void setEqualto(Account a){
    player.setEqualTo(a.getPlayer());
    userID = a.getID();
    amount = a.getAmount();
    password = a.getPassword();
  }
  

  //  Constructor
  public Account(String userID, String password) {
    this.userID = userID;
    this.password = password;
    amount = 100; //USER STARTS WITH 100
  

    player = new Player();

  player.setID(userID);
    player.setBankAcount(amount);
    player.isLoggedInPlayer = isLoggedIn;
 
     
  }

  public Hand returnHand(){
    return player.returnHand();
  }
 


  public void depositMoney(int x){
    amount += x;
  }

  public void withdrawMoney(int x) {
    if (amount >= x)
      amount -= x;
    
    else{
        return;  //TODO
    }

   }

   public void addCardToPlayersHand(Card card){
    player.addCard(card);
    }

  public int returnAmount() {
    return amount;
  }

  public boolean lockedAccount(){
    return isLocked; 
  }

  public void setPlayer(Player p){
    player.setEqualTo(p);
    player.setBankAcount(amount);
  }

  public Player getPlayer(){
    return player;
  }

  public String getPassword(){
    return password;
  }

  public String getID(){
    return userID;
  }

  public void setAmount(String x){
    amount = Integer.parseInt(x);
  }

  public int getAmount(){
    return amount;
  }
  
 
}

 