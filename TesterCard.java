import org.junit.Test;
 

public class TesterCard {

  @Test

  public void TestSuit(){
    Card card = new Card(7, "Club");

    assert(card.getSuit().equals("Club"));
  }


  @Test
  public void TestCardValue(){
    Card card = new Card(7, "Club");

    assert(card.getcardValue() == 7);
  }

  @Test
  public void TestGetImgName(){
    Card card = new Card(7, "Club");

    assert(card.returnImgName().equals("Club7"));
  }
  
  
}
