import java.io.IOException;

class ThreadChild extends Thread {

  private Client client;

  private Account account;
  public ThreadChild(Client c,Account a){

    this.client = c;
    this.account = a;
  }
     
  public void run() {

    account.getPlayer().setIsKicked(true);
    try {
      client.addPlayerToGame(account, false, false, false, false);
    } catch (ClassNotFoundException | IOException e) {
       e.printStackTrace();
    }
       
      
  }
}