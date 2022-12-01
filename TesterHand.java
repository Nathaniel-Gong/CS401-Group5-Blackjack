import org.junit.Test;
public class TesterHand {

  @Test

  public void TestReturnLatest(){
    Card card = new Card(6, "Heart");

    Hand hand = new Hand();

    hand.addCardToHand(card);

    assert(hand.returnLatest().getSuit().equals("Heart") && hand.returnLatest().getcardValue() == 6); 
  }
  
  @Test
  public void TestSetHandTotal(){

    Hand hand = new Hand();
    hand.setHandTotal(21);

    assert(hand.getHandTotal() == 21);

  }

  @Test
  public void TestReturnNumCards(){

    Hand hand = new Hand();

    Card card = new Card(6, "Heart");



    hand.addCardToHand(card);
 
    assert(hand.returnNumCards() == 1);

  }


  @Test
  public void TestAccessCardAtIndex(){

    Hand hand = new Hand();

    Card card = new Card(6, "Heart");



    hand.addCardToHand(card);
 
    assert(hand.accessCardatIndex(0).getSuit().equals("Heart") && hand.accessCardatIndex(0).getcardValue() == 6);

  }

  @Test
  public void TestSetEqualTo(){

    Hand hand = new Hand();

    Card card = new Card(6, "Heart");



    hand.addCardToHand(card);

    Hand handV = new Hand();

    Card cardV = new Card(6, "Heart");



    handV.addCardToHand(cardV);
 
    assert(hand.returnNumCards() ==  handV.returnNumCards() && hand.returnLatest().getSuit().equals(handV.returnLatest().getSuit()) 
    && hand.returnLatest().getcardValue() == handV.returnLatest().getcardValue());

  }



}
