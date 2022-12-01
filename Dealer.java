import java.io.Serializable;


public class Dealer implements Serializable{

  //  Private Variables
  Hand dealerHand;
  boolean isDealerTurn = false;

  public void setIsDealerTurn(boolean v){
    isDealerTurn = v;
  }

  public boolean returnDealersTurnBool(){
    return isDealerTurn;
  }
  private static final long serialVersionUID = 6539685098267757690L;

  public Hand returnHand(){
    return dealerHand;
  }

  public void setEqualTo(Dealer d){

    dealerHand.setEqualTo(d.returnHand());
  }


  
  //  Constructor
  public Dealer () {
       dealerHand = new Hand();
  }

  //  Getters
  //  get Dealer hand total
  public int getDealerHandTotal () {
    return dealerHand.getHandTotal();
  }

  //  Other Methods
  //  add card to Dealers hand
  public void addCardToDealerHand (Card card) {
    dealerHand.addCardToHand(card);
   }

  public Card getLatestCard(){
    return dealerHand.accessCardatIndex(dealerHand.returnNumCards()-1);
  }
}