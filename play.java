import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.awt.image.BufferedImage;

public class play extends JFrame implements ActionListener {
  Hand playersHand = new Hand();
  Client client = new Client();

  int playerIndex = 0;

  int playersHandTotal = 0;

  int numPlayerCards = 0;
  int numDealersCards;

  Runtime current = Runtime.getRuntime();

  Player player = new Player();

  Dealer dealer = new Dealer();

  Game game = new Game();

  int newCardPositionX = 100;
  int newCardPositionY = 310;

  int newDealerCardPositionX = 100;
  int newDealerCardPositionY = 100;

  Container container = getContentPane();
  JPanel ChipBox = new JPanel();
  BufferedImage buttonIcon;
  BufferedImage buttonIconB;
  BufferedImage buttonIconC;
  BufferedImage buttonIconD;
  BufferedImage buttonIconE;
  BufferedImage buttonIconF;

  JButton resetButton = new JButton("RESET");
  JButton betButton = new JButton("BET");

  JButton hitButton = new JButton("HIT");
  JButton doubleDownButton = new JButton("DOUBLE DOWN");
  JButton standButton = new JButton("STAND");

  JLabel Bet = new JLabel("$0");
  JLabel WAITINGFORPLAYERS = new JLabel("WAITING FOR PLAYERS");
  JLabel WAITINGFORTURN = new JLabel("WAIT FOR YOUR TURN");
  JLabel WINLABEL = new JLabel("YOU'VE WON");
  JLabel LOSELABEL = new JLabel("YOU'VE BUSTED");
 

  int betAmount = 0;

  JButton buttonA;
  JLabel BankFunds;
  JButton buttonB;
  JButton buttonC;
  JButton buttonD;
  JButton buttonE;
  JButton buttonF;

  int bankamount;
  Color myGreen = new Color(144, 238, 144, 123);

  Account account;

  public static boolean isOpen = false;

  public void getInstance() {

    this.initPlay();

  }

  public void AddCardToDisplay(Card card) throws IOException {
    BufferedImage myPicture = ImageIO
        .read(new File("/Users/marlontrejo/Documents/GUI/assets/" + card.returnImgName() + ".png"));
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    picLabel.setBounds(newCardPositionX, newCardPositionY, 127, 182);
    container.add(picLabel);

    revalidate();
    repaint();
    newCardPositionX += 127;

  }

  public void AddCardToDealerDisplay(Card card) throws IOException {

    BufferedImage myPicture = ImageIO
        .read(new File("/Users/marlontrejo/Documents/GUI/assets/" + card.returnImgName() + ".png"));
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    picLabel.setBounds(newDealerCardPositionX, newDealerCardPositionY, 127, 182);
    container.add(picLabel);
    revalidate();
    repaint();
    newDealerCardPositionX += 127;

  }

  public play(Account a) {
    account = new Account(" ", " ");
    account.setEqualto(a);
    player.setEqualTo(a.getPlayer());

    current.addShutdownHook(new ThreadChild(client, account));

    // copyOfmain = new Account(account.getID(),account.getPassword());

    // copyOfmain.setPlayer(account.getPlayer());

    // copyOfmain.setAmount(copyOfmain.getAmount());

    bankamount = account.returnAmount();
    BankFunds = new JLabel("In Bank:" + String.valueOf(bankamount));
    Bet.setFont(new Font("Phosphate", Font.PLAIN, 30));

    BankFunds.setFont(new Font("Phosphate", Font.PLAIN, 30));
    ChipBox.setBackground(myGreen);

    try {
      buttonIcon = ImageIO.read(new File("01.png"));
      buttonIconB = ImageIO.read(new File("02.png"));
      buttonIconC = ImageIO.read(new File("03.png"));
      buttonIconD = ImageIO.read(new File("04.png"));
      buttonIconE = ImageIO.read(new File("05.png"));
      buttonIconF = ImageIO.read(new File("06.png"));

    } catch (Exception e) {

    }

    buttonA = new JButton(new ImageIcon(buttonIcon));
    buttonB = new JButton(new ImageIcon(buttonIconB));
    buttonC = new JButton(new ImageIcon(buttonIconC));
    buttonD = new JButton(new ImageIcon(buttonIconD));
    buttonE = new JButton(new ImageIcon(buttonIconE));
    buttonF = new JButton(new ImageIcon(buttonIconF));

    buttonA.setBorder(BorderFactory.createEmptyBorder());
    buttonB.setBorder(BorderFactory.createEmptyBorder());
    buttonC.setBorder(BorderFactory.createEmptyBorder());
    buttonD.setBorder(BorderFactory.createEmptyBorder());
    buttonE.setBorder(BorderFactory.createEmptyBorder());
    buttonF.setBorder(BorderFactory.createEmptyBorder());

    setLayoutManager();
    setLocationAndSize();
    addComponentsToContainer();
    addActionEvent();

  }

  public void setLayoutManager() {
    container.setLayout(null);
  }

  public void setLocationAndSize() {

    ChipBox.setBounds(200, 650, 600, 150);

    buttonA.setBounds(200, 680, 100, 100);

    buttonB.setBounds(300, 680, 100, 100);

    buttonC.setBounds(400, 680, 100, 100);

    buttonD.setBounds(500, 680, 100, 100);

    buttonE.setBounds(600, 680, 100, 100);

    buttonF.setBounds(700, 680, 100, 100);

    Bet.setBounds(200, 570, 100, 100);

    resetButton.setBounds(450, 600, 100, 50);

    BankFunds.setBounds(600, 570, 200, 100);

    betButton.setBounds(450, 550, 100, 50);

    WAITINGFORPLAYERS.setBounds(400, 300, 200, 100);
    WAITINGFORTURN.setBounds(400, 400, 200, 100);
    WINLABEL.setBounds(400, 500, 200, 100);

    LOSELABEL.setBounds(400, 100, 200, 100);
 
    betButton.setBorderPainted(false);
    betButton.setBackground(Color.darkGray);
    betButton.setOpaque(true);
    betButton.setForeground(Color.white);

  }

  public void addComponentsToContainer() {

    container.add(ChipBox);
    container.add(buttonA);
    container.add(buttonB);
    container.add(buttonC);
    container.add(buttonD);
    container.add(buttonE);
    container.add(buttonF);
    container.add(Bet);
    container.add(betButton);
    container.add(BankFunds);
    container.add(resetButton);
    container.add(WAITINGFORPLAYERS);
    container.add(WAITINGFORTURN);
    container.add(WINLABEL);
    container.add(LOSELABEL);
    WAITINGFORTURN.setVisible(false);
    WINLABEL.setVisible(false);
    LOSELABEL.setVisible(false);
    revalidate();
    repaint();
  }

  private void initPlay() {

    play frame = new play(account);
    frame.setTitle("Game");
    Color myBlue = new Color(51, 153, 255);
    frame.getContentPane().setBackground(myBlue);
    frame.setVisible(true);
    frame.setBounds(10, 10, 1000, 1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

  }

  public void addActionEvent() {
    buttonA.addActionListener(this);
    buttonB.addActionListener(this);
    buttonC.addActionListener(this);
    buttonD.addActionListener(this);
    buttonE.addActionListener(this);
    buttonF.addActionListener(this);
    resetButton.addActionListener(this);
    betButton.addActionListener(this);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == buttonA && betAmount + 1 <= bankamount) {

      betAmount += 1;
      player.setWager(betAmount);
      Bet.setText("$" + String.valueOf(betAmount));

    }

    if (e.getSource() == buttonB && betAmount + 5 <= bankamount) {

      betAmount += 5;
      player.setWager(betAmount);
      Bet.setText("$" + String.valueOf(betAmount));

    }

    if (e.getSource() == buttonC && betAmount + 10 <= bankamount) {

      betAmount += 10;
      player.setWager(betAmount);
      Bet.setText("$" + String.valueOf(betAmount));

    }

    if (e.getSource() == buttonD && betAmount + 20 <= bankamount) {

      betAmount += 20;
      player.setWager(betAmount);
      Bet.setText("$" + String.valueOf(betAmount));

    }

    if (e.getSource() == buttonE && betAmount + 50 <= bankamount) {

      betAmount += 50;
      player.setWager(betAmount);
      Bet.setText("$" + String.valueOf(betAmount));

    }

    if (e.getSource() == buttonF && betAmount + 100 <= bankamount) {

      betAmount += 100;
      player.setWager(betAmount);
      Bet.setText("$" + String.valueOf(betAmount));

    }

    if (e.getSource() == resetButton) {
      betAmount = 0;
      player.setWager(betAmount);
      Bet.setText("$0");
    }

    if (e.getSource() == betButton && betAmount > 0) {

      numDealersCards++;

      playerIndex = 0;

      try {

        game.setEqualTo(client.addPlayerToGame(account, false, false, false, false));

        if (game.returnsizeofGame() == 1) {

          while (game.returnsizeofGame() == 1) {

            game.setEqualTo(client.addPlayerToGame(account, false, false, false, true));


            revalidate();
            repaint();

            }

        } else {
          playerIndex = 1;
        }

        hitButton.setBounds(200, 540, 150, 100);
        doubleDownButton.setBounds(650, 540, 150, 100);
        standButton.setBounds(420, 540, 150, 100);

        BankFunds.setVisible(!BankFunds.isVisible());
        Bet.setVisible(!Bet.isVisible());
        resetButton.setVisible(false);
        betButton.setVisible(false);

        hitButton.addActionListener(this);
        doubleDownButton.addActionListener(this);
        standButton.addActionListener(this);
        player.setEqualTo(game.returnCurrentPlayer(playerIndex));

        
        playersHand.setEqualTo(player.returnHand());

        dealer.setEqualTo(game.returnDealer());

        AddCardToDealerDisplay(dealer.getLatestCard());

        for (int i = 0; i < playersHand.returnNumCards(); i++) {
          AddCardToDisplay((playersHand.accessCardatIndex(i)));
           numPlayerCards++;

        }

        if (playersHandTotal ==  21) {

          WINLABEL.setVisible(true);
         

             
              revalidate();
              repaint();

        account.depositMoney(betAmount);

      }

      if (game.isPlayersTurn(player) == false){
        WAITINGFORTURN.setVisible(true);
        revalidate();
        repaint();
      }

      while (game.isPlayersTurn(player) == false){


    
       
        try {
          game.setEqualTo(client.addPlayerToGame(account, false, false, false, true));
        } catch (ClassNotFoundException | IOException e1) {
           e1.printStackTrace();
        }
 
      }

      } catch (UnknownHostException e1) {

        e1.printStackTrace();
      } catch (ClassNotFoundException e1) {

        e1.printStackTrace();
      } catch (IOException e1) {

        e1.printStackTrace();
      } finally {
        container.remove(WAITINGFORPLAYERS);

        container.add(hitButton);
        container.add(doubleDownButton);
        container.add(standButton);
        revalidate();
        repaint();

        

      }

   

    }

    if (e.getSource() == hitButton && game.returnCurrentPlayersTurnIndex() == playerIndex && playersHandTotal < 21) {
      

      WAITINGFORTURN.setVisible(false);
      revalidate();
      repaint();

 
      try {

      

        

        account.getPlayer().setPlayerChoice(true, false, false);
        account.setBooleanHitPlayer(true);
         game.setEqualTo((client.addPlayerToGame(account, true, false, false, true)));

        player.setEqualTo(game.getSpecificIndex(playerIndex));


         playersHand.setEqualTo(player.returnHand());

 
  
           AddCardToDisplay((playersHand.returnLatest()));

            playersHandTotal = 0;

           for (int i=0;i<playersHand.returnNumCards();i++){
 
            if (playersHand.accessCardatIndex(i).getcardValue() == 1 && playersHandTotal<11){

              playersHandTotal += 11;
        
            }
            else if (playersHand.accessCardatIndex(i).getcardValue() == 1 && playersHandTotal>=11){
              playersHandTotal+=1;
            }
            else if (playersHand.accessCardatIndex(i).getcardValue() > 10){
              playersHandTotal += 10;
            }
            else{
              playersHandTotal += playersHand.accessCardatIndex(i).getcardValue();
            }
          
   
    

           }

           if (game.isPlayersTurn(player) == false){
            WAITINGFORTURN.setVisible(true);
            revalidate();
            repaint();
           }

      while (game.isPlayersTurn(player) == false){


    
       
        try {
          game.setEqualTo(client.addPlayerToGame(account, false, false, false, true));
        } catch (ClassNotFoundException | IOException e1) {
           e1.printStackTrace();
        }
 
      }
       
       
      
        
             if (playersHandTotal> 21) {

              System.out.print("efwew");

            WAITINGFORTURN.setVisible(false);
           

              LOSELABEL.setVisible(true);
                revalidate();
                repaint();
 
             

 
 
 
 
           

          account.withdrawMoney(betAmount);

        

         
        }

               
        if (playersHandTotal ==  21) {

          WINLABEL.setVisible(true);
         

             
              revalidate();
              repaint();

           





         

        account.depositMoney(betAmount);

      

       
      }
 



       

      } catch (ClassNotFoundException | IOException e1) {

        e1.printStackTrace();
      }

      finally{
        
        

      }

    }
     

    if (game.returnCurrentPlayersTurnIndex() != playerIndex && e.getSource() == hitButton && game.returnDealer().isDealerTurn == false) {

  


      
      account.setBooleanHitPlayer(false);

      
      WAITINGFORTURN.setVisible(true);
      revalidate();
      repaint();

      while (game.isPlayersTurn(player) == false){
        System.out.println("werfewf");
        try {
          game.setEqualTo(client.addPlayerToGame(account, false, false, false, true));
        } catch (ClassNotFoundException | IOException e1) {
           e1.printStackTrace();
        }
 
      }

     

  
 

    }

    if ( game.returnDealer().isDealerTurn == true) {


      try {
        AddCardToDealerDisplay(game.returnDealer().getLatestCard());
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }



      while (game.isPlayersTurn(player) == false){
       
        try {
          game.setEqualTo(client.addPlayerToGame(account, false, false, false, true));
        } catch (ClassNotFoundException | IOException e1) {
           e1.printStackTrace();
        }
 
      }
       

    }


    if (e.getSource() == standButton) {

      WAITINGFORTURN.setVisible(true);
           
 
        revalidate();
        repaint();


      account.getPlayer().setPlayerChoice(false, true, false);

      account.setBooleanstand(true);
      account.setBooleanHitPlayer(false);

      try {
        game.setEqualTo((client.addPlayerToGame(account, false, true, false, true)));
      } catch (ClassNotFoundException | IOException e1) {

        e1.printStackTrace();
      }

      player.setEqualTo(game.returnCurrentPlayer(playerIndex));

      if (game.isDealersTurn == true) {

        try {
          AddCardToDealerDisplay(dealer.returnHand().returnLatest());
        } catch (IOException e1) {
           e1.printStackTrace();
        }

      }



      while (game.isPlayersTurn(player) == false){
       
        try {
          game.setEqualTo(client.addPlayerToGame(account, false, false, false, true));
        } catch (ClassNotFoundException | IOException e1) {
           e1.printStackTrace();
        }
 
      }
       

    }

    if (e.getSource() == doubleDownButton) {

    }

  }

}
