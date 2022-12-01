import java.io.Serializable;

 public class Player implements Serializable {

  private static final long serialVersionUID = 6529685098457757690L;  

  int wager;
  String userID;
  Hand hand;
  int bankAmount;
  boolean firsttime = false;
  boolean busted = false;
  boolean isKicked = false;

  public boolean isLoggedInPlayer = false;

  public boolean getisKicked(){
    return isKicked;
  }

  public void setIsKicked(boolean k){
    isKicked = k;
  }

  public void setBooleanHit(boolean h){
    hit = h;
  }
  boolean hit = false;
  boolean stand = false;
  boolean doubleDown = false;
boolean hasDoubledDown;
  public Player(int w,String u,Hand h){
    wager = w;
    hasDoubledDown = false;
    userID = u;
    hand = h;
  }

  public void setBust(boolean v){
    busted = v;
  }

  public Player(){
    hand = new Hand();
  }

  public String returnID(){
    return userID;
  }

  public Hand returnHand(){
    return hand;
  }

  public Card returnNewestCard(){
    return hand.accessCardatIndex(hand.returnNumCards()-1);
  }
  public Player(int w,String u){
    wager = w;
    userID = u;
    hand = new Hand();
  }

  void setWager(int w){
    wager = w;
  }
  
  void setID(String u){
    userID = u;
  }
  
  void setHand(Hand h){
    hand.setEqualTo(h);
  }

  public void addCard(Card c){

    hand.addCardToHand(c);
 
  }

  int getPlayersTotal(){
    return hand.getHandTotal();
  }

  void Quit(){

    System.exit(0);
  }

  public int returnWager(){
    return wager;
  }

  public void setBankAcount(int b){
    bankAmount = b;
  }

public void setStand(boolean B){
  stand = B;
}

  public int getBankAmount(){
    return bankAmount;
  }

  public void SetDoubleDownTrue(){
    hasDoubledDown = true;
  }

  public boolean hasDoubleDown(){
    return hasDoubledDown;

  }

  

  public void setEqualTo(Player p){
    wager = p.returnWager();
    userID = p.returnID();
    
    bankAmount = p.getBankAmount();
    hand.setEqualTo(p.returnHand());
    firsttime = p.getFirstTime();
    isKicked = p.getisKicked();
    hasDoubledDown = p.hasDoubledDown;
    hit = p.getHitBoolean();
    doubleDown = p.getdoubleDownBoolean();
    stand = p.getStandBoolean();
    isLoggedInPlayer = p.isLoggedInPlayer;
    
}

public void setFirstTime(boolean s){

  firsttime = s;

}

public void setPlayerChoice(boolean hit,boolean stand, boolean doubleDown){

  this.hit = hit;
  this.stand = stand;
  this.doubleDown = doubleDown;

}

public boolean getHitBoolean(){
  return hit;
}


public boolean getStandBoolean(){
  return stand;
}

public boolean getdoubleDownBoolean(){
  return doubleDown;
}

public boolean getFirstTime(){
  return firsttime;
}





public int handTotal(){
  return hand.getHandTotal();
}

 }


  


// Need hit, stand , surrender, double down, Place Wager, Double down 
