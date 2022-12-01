import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

public class Game implements Serializable
{

  boolean isDealersTurn = false;

  public int returnGamesTurn(){
    return currentPlayerIndexTurn;
  }

  public boolean isPlayersTurn(Player p){
    for (int i=0;i<players.size();i++){
      if (p.returnID().equals(players.get(i).returnID()) && i == currentPlayerIndexTurn){
        return true;
      }
    }

    return false;
  }
  

  private static final long serialVersionUID = 6523123098267757690L;

  public void setEqualTo(Game game){
    deck.setEqualTo(game.returnDeck());
    players = new Vector<>(game.returnPlayerVector());
    dealer.setEqualTo(game.returnDealer());
    this.isGameFull = game.isGameFull;
    currentPlayerIndexTurn = game.returnGamesTurn();

  }


  private Date startTime;
  private Date endTime;
  private final int MAX_NUM_PLAYERS = 2;
  int currentPlayerIndexTurn;


  Deck deck;

  boolean isGameDone = false;

  public Deck returnDeck(){
    return deck;
  }
  

  private long timeElapsed;
  private Dealer dealer;

 private final double maxSeconds = 60.0; //can change this later
 
 private Vector<Player> players;

 public Vector<Player> returnPlayerVector(){
  return players;
 }
 public boolean isGameFull;

 public void clear(){
  players.clear();
 }

 public boolean checkIfEmpty(){
  if (players.size() == 0){
    return true;
  }
  return false;
 }

 

 public int returnsizeofGame(){
  return players.size();

 }

 public boolean checkIfPlayerExists(String p){
  for (int i=0;i<players.size();i++){
    if (players.get(i).userID.equals(p) )
    {
      return true;
    }
  }

  return false;
 }

 


 public Card addCardtoDealer(){
  Card c = deck.getCard();
  dealer.addCardToDealerHand(deck.getCard());

    
  deck.PopCard();

  return c;
 }

 public Game(){
  players = new Vector<>();
   
  dealer = new Dealer();
  currentPlayerIndexTurn = 0;
  isGameFull = false;
  deck = new Deck();
 }

public void incrementPlayerTurn(){
  currentPlayerIndexTurn++;

  if (currentPlayerIndexTurn == players.size()){

    currentPlayerIndexTurn = 0;

    if (dealer.getDealerHandTotal()>=17 && dealer.getDealerHandTotal()<22){
      return;
    }
    else if (dealer.getDealerHandTotal()<17){
      Card card = deck.getCard();
      dealer.addCardToDealerHand(card);
       deck.PopCard();
    }

  
  }
}

public void runPlayersTurn(boolean hit,boolean stand,boolean doubledown){




    if (players.get(currentPlayerIndexTurn).hasDoubleDown() == false){

      if (stand == true){
        currentPlayerIndexTurn++;

        if (currentPlayerIndexTurn == 2){
          isDealersTurn = true;
          dealersTurn();
          currentPlayerIndexTurn = 0;
        }
        else{
          isDealersTurn = false;
        }
      }

      else if (hit == true){
  
        Card c = deck.getCard();
  
        players.get(currentPlayerIndexTurn).addCard(c);
         
        deck.PopCard();

        if (players.get(currentPlayerIndexTurn).returnHand().getHandTotal() >=21){
          currentPlayerIndexTurn++;
        }
  
  
        
      }
  
     
      else if (doubledown == true){
        int newWager = players.get(currentPlayerIndexTurn).returnWager();
        newWager*=2;
    
        if (players.get(currentPlayerIndexTurn).getBankAmount() >=newWager){
       
          players.get(currentPlayerIndexTurn).addCard(deck.getCard());
   
          deck.PopCard();
          players.get(currentPlayerIndexTurn).SetDoubleDownTrue();
        
        }
    
      }
      

  }

 

 



  
 

  
  
}



public void runPlayersTurn(boolean hit,boolean stand,boolean doubledown,int index){

 
  if (players.get(index).hasDoubleDown() == false){
    if (hit == true){

      Card c = deck.getCard();

      players.get(index).addCard(c);
      
      deck.PopCard();


      
    }

   
    else if (doubledown == true){
      int newWager = players.get(index).returnWager();
      newWager*=2;
  
      if (players.get(index).getBankAmount() >=newWager){
     
        players.get(index).addCard(deck.getCard());
        deck.PopCard();
        players.get(index).SetDoubleDownTrue();
      
      }
  
    }
    

  }

  currentPlayerIndexTurn++;

  if (currentPlayerIndexTurn == 2){
    isDealersTurn = true;
    dealersTurn();
    currentPlayerIndexTurn = 0;
  }
  else{
    isDealersTurn = false;
  }
 
  
  
}



public Player getSpecificIndex(int i){
return players.get(i);
}

public int getPlayerIndex(Player p){

 

  for (int i=0;i<players.size();i++){
    if (players.get(i).returnID().equals(p.returnID())){
      return i;
    }
  }

  return 0;
}

/*public void initializePlayerWithCard(int index){
   
  players.get(index).addCard(deck.getCard());
  players.get(index).returnHand().reinitCardValue();

  deck.PopCard();

 
  players.get(index).addCard(deck.getCard());
  deck.PopCard();
   

}*/

 public void initStartPlayersTime(){
  startTime = new Date();
 }

 public void initEndPlayersTime(){
  endTime = new Date();
 }

 public void checkTimeElapsed(){

 timeElapsed =  endTime.getTime() - startTime.getTime();


 }

 public long returnTimeElapsed(){
 return timeElapsed;
 }

 public void removePlayer(Player p){
     for (int i=0;i<players.size();i++){
      if (players.get(i).returnID().equals( p.returnID())){
        
        break;
      }
    }
   

  if (players.size() == MAX_NUM_PLAYERS){
    isGameFull = true;
  }
  else{
    isGameFull = false;
  }

 }

 public void addPlayer(Player p){
  if (players.size() <= MAX_NUM_PLAYERS){
    p.addCard(deck.getCard());
    deck.PopCard();
    p.addCard(deck.getCard());
    deck.PopCard();

 
    players.add(p);

    if (dealer.getDealerHandTotal() == 0){
      dealer.addCardToDealerHand(deck.getCard());
       deck.PopCard();
    }


  }

  if (players.size() == MAX_NUM_PLAYERS){
    isGameFull = true;
  }
  else{
    isGameFull = false;
  }
 

}

public Dealer returnDealer(){
  return dealer;
}

public Player returncurrentPlayer(){
  return players.get(currentPlayerIndexTurn);
}

public Player returnCurrentPlayer(int index){
  return players.get(index);
}

 public void kickPlayer(){
  players.remove(currentPlayerIndexTurn);
  if (players.size()<MAX_NUM_PLAYERS){

    isGameFull = false;

  }
 }


 public void kickPlayer(int index){
  players.remove(index);
  if (players.size()<MAX_NUM_PLAYERS){

    isGameFull = false;

  }
 }

 public boolean checkIfPlayerBusts(int index){

  if (players.get(index).getPlayersTotal() >21){
    return true;
  }
  return false;

 }

 public boolean checkIfDealerBusts(){

  if  (dealer.getDealerHandTotal() >21){
    return true;
  }
  return false;

 }

 

 /*public boolean isGameOver(){

  if (checkBlackJackForDealer() == false && checkBlackJackForPlayer(currentPlayerIndexTurn) == false
   && checkIfDealerBusts()== false && checkIfPlayerBusts() == false){
    isGameDone = false;
    return false;
   }
   isGameDone = true;
   return true;

 }*/

 public boolean checkBlackJackForPlayer(int index){

  if (players.get(index).getPlayersTotal() > dealer.getDealerHandTotal() && players.get(index).getPlayersTotal()<=21 
  && dealer.getDealerHandTotal()<=21 ){

    return true;

  }

  return false;

  
  }

  public boolean checkBlackJackForDealer(){

    if (dealer.getDealerHandTotal() <=21 && players.get(currentPlayerIndexTurn).getPlayersTotal()>21){
      return true;
    }

    return false;

  }

  public boolean checkIfTie(int index){
      if (players.get(index).getPlayersTotal()>=17 && players.get(index).getPlayersTotal() <=21 && 
      dealer.getDealerHandTotal()>=17 && dealer.getDealerHandTotal()<=21 && players.get(index).getPlayersTotal() == dealer.getDealerHandTotal()){
        return true;
      }

      return false;
 }

 public int returnCurrentPlayersTurnIndex(){
  return currentPlayerIndexTurn;
 }

 public void dealersTurn(){
  if (isDealersTurn == true){

    if (dealer.getDealerHandTotal()>=17 && dealer.getDealerHandTotal()<21){
      isDealersTurn = false;

    }
    else{

      dealer.addCardToDealerHand(deck.getCard());
       deck.PopCard();
    }


  }
 }

 

}
