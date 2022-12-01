 
import java.io.Serializable;
 
public class Card implements Serializable{

  //  Private Variables
  private Integer cardValue;
  private String suit; 
  private static final long serialVersionUID = 6523685098267757690L;
 

  //  Constructor
  public Card(Integer cardValue, String suit) {
    this.cardValue  = cardValue;
    this.suit = suit; 

    
    if (cardValue == 1){
      isAce = true;
    }
    else{
      isAce = false;
    }
  
 
 }

 public String returnImgName(){
  return (suit + String.valueOf(cardValue));
 }

  //  Getters
  public Integer getcardValue(){
    return cardValue;
  }
  public String getSuit(){
    return suit;
  }


  public boolean isAce;
 
}