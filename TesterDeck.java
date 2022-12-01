import org.junit.Test;


public class TesterDeck {

  @Test

  public void testPop(){
    Deck deck = new Deck();

    assert(deck.returnstackofcards().size() == 52);
  }
  
}
