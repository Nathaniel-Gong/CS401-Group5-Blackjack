import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboard extends JFrame implements ActionListener {

  play Play;

  Account account;

  Container container = getContentPane();
  JLabel playLabel = new JLabel("PLAY!");
  JLabel checkFunds = new JLabel("CHECk FUNDS");
  JLabel quit = new JLabel("QUIT");

  JLabel BlackJackLabel = new JLabel("BLACKJACK");

  JButton playButton = new JButton("PLAY");
  JButton bankButton = new JButton("CHECK BANK");
  JButton quitButton = new JButton("QUIT");

  dashboard(Account a) {

    

    account = new Account(" ", " ");
     account.setEqualto(a);
    setLayoutManager();
    setLocationAndSize();
    addComponentsToContainer();
    addActionEvent();

    BlackJackLabel.setFont(new Font("Phosphate", Font.PLAIN, 45));
     
    Play = new play(account);
  }

  public void setLayoutManager() {
    container.setLayout(null);
  }

  public void setLocationAndSize() {
    BlackJackLabel.setBounds(85, 40, 237, 126);
    ;
    playButton.setBounds(85, 150, 237, 126);
    bankButton.setBounds(85, 285, 237, 126);
    quitButton.setBounds(85, 420, 237, 126);
    playButton.setBorderPainted(false);
    playButton.setBackground(Color.red);
    playButton.setForeground(Color.black);
    playButton.setOpaque(true);
    bankButton.setBorderPainted(false);
    bankButton.setBackground(Color.red);
    bankButton.setOpaque(true);
    bankButton.setForeground(Color.black);
    quitButton.setBorderPainted(false);
    quitButton.setBackground(Color.red);
    quitButton.setOpaque(true);
    quitButton.setForeground(Color.black);

  }

  public void addComponentsToContainer() {
    container.add(BlackJackLabel);
    container.add(playButton);
    container.add(bankButton);
    container.add(quitButton);

  }

  public void initDash() {

    dashboard frame = new dashboard(account);
    frame.setTitle("Dashboard");
    Color myBlue = new Color(51, 153, 255);
    frame.getContentPane().setBackground(myBlue);
    frame.setVisible(true);
    frame.setBounds(10, 10, 407, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

  }

  public void addActionEvent() {
    playButton.addActionListener(this);
    quitButton.addActionListener(this);
    bankButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == playButton && play.isOpen == false) {

      Play.getInstance();

      play.isOpen = true;

    }

    if (e.getSource() == bankButton) {

    }

    if (e.getSource() == quitButton) {

      System.exit(ABORT);

    }

  }

}
