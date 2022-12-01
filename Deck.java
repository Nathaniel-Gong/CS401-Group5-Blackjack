import java.util.Stack;
import java.util.Collections;
import java.io.Serializable;


public class Deck implements Serializable{

  private static final long serialVersionUID = 6529685198267757690L;
  //  Private Variable(s)
  private Stack<Card> cards;

  //  Constructor
  public Deck(){
    
     
  

    //  create a new stack
    cards = new Stack<>();

    //  create cards and add to the stack
    for (int i = 1; i <= 13; i++){

      Card card;

      card = new Card(i,"Heart");   
      cards.push(card);
      
      card = new Card(i,"Diamond");  
      cards.push(card);
      
      card = new Card(i,"Spade");    
      cards.push(card);
      
      card = new Card(i,"Club");    
      cards.push(card);
      
    }

    //  shuffle the deck
    Collections.shuffle(cards);
    
  }

  public Stack<Card> returnstackofcards(){
    return cards;
    }

  //  Getters
  public Card getCard() {
    return cards.peek();
  }

  //  Other Methods
  public void PopCard() {
    cards.pop();
  }

  public void setEqualTo(Deck d){
    cards.addAll(d.returnstackofcards());
  }

}