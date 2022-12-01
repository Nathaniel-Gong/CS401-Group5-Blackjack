import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
 
public class login extends JFrame implements ActionListener {

    public Client client;

    public Account mainAccount = new Account(" ","  " );

 
    static boolean isAccountWindowOpen = false;
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel BlackJackLabel = new JLabel("BLACKJACK");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton createAccountButton = new JButton("CREATE ACCOUNT");
    JPanel pictureFrame = new JPanel();
    JCheckBox showPassword = new JCheckBox("Show Password");

    dashboard Dash;
    createAccount AccountCreator;
 
 
    login() {
 

        Dash = new dashboard(mainAccount);
        AccountCreator = new createAccount();

        client = new Client();

        
        
        BlackJackLabel.setFont(new Font("Phosphate", Font.PLAIN, 50));
        createAccountButton.setFont(new Font("Arial", Font.PLAIN, 8));
        loginButton.setFont(new Font("Arial",Font.PLAIN,8));

        ImageIcon imageIcon = new ImageIcon("Blackjack2.png"); 
        Image image = imageIcon.getImage();  
        Image newimg = image.getScaledInstance(150, 173,  java.awt.Image.SCALE_SMOOTH);   
        imageIcon = new ImageIcon(newimg);  

      

        pictureFrame.add(new JLabel(imageIcon));

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        BlackJackLabel.setBounds(50, 50 , 300, 100);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        createAccountButton.setBounds(200, 300, 100, 30);
        pictureFrame.setBounds(110, 350, 150, 173);
 
 
    }
 
    public void addComponentsToContainer() {
        container.add(BlackJackLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(createAccountButton);
        container.add(pictureFrame);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
 
     

            if (client.verifyAccount(userText, pwdText) == true){
               
                try {
                    mainAccount.isLoggedIn = true;
                    mainAccount.setAmount(client.getFunds(userText));
                    mainAccount.setID(userText);
                    mainAccount.setpassword(pwdText);
                    mainAccount.setIsLoggedInPlayer(true);

 
                    Dash = new dashboard(mainAccount);
                  
                } catch (UnknownHostException e1) {
                   
                    e1.printStackTrace();
                } catch (IOException e1) {
              
                    e1.printStackTrace();
                }
                Dash.initDash();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
 
        }
         if (e.getSource() == createAccountButton && login.isAccountWindowOpen == false) {

           

            AccountCreator.getInstance();

            login.isAccountWindowOpen = true;
            
        }
         if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
 
        }
    }
 

 
 
    public void initLogin() {
        login frame = new login();
        frame.setTitle("Login");
        Color myBlue = new Color(51,153,255);   
        frame.getContentPane().setBackground(myBlue);
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
 
    }
    protected  void finalize ()
{  
    mainAccount.isLoggedIn = false;
    mainAccount.setIsLoggedInPlayer(false);
 
}  
  

}
