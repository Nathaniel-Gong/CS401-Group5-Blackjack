import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.*;
import javax.mail.internet.*;
 

public class createAccount extends JFrame implements ActionListener {
  private Client client = new Client();
 

  public void getInstance(){
     
      this.initCreateAccount();
     
    
  }
  
  Container container = getContentPane();
  JLabel emailLabel = new JLabel("EMAIL");
  JLabel passwordLabel = new JLabel("PASSWORD");
  JLabel extraPasswordLabel = new JLabel("CONFIRM PASSWORD");
  JTextField emailTextField = new JTextField();
  JPasswordField passwordTextField = new JPasswordField();
  JPasswordField confirmPasswordTextField = new JPasswordField();
  JButton finishButton = new JButton("CREATE");
  JLabel BlackJackLabel = new JLabel("BLACKJACK");

  JCheckBox showPassword = new JCheckBox("Show Password");
 

  createAccount() {
      setLayoutManager();
      setLocationAndSize();
      addComponentsToContainer();
      addActionEvent();
      BlackJackLabel.setFont(new Font("Phosphate", Font.PLAIN, 50));
      
     

  }

  public void setLayoutManager() {
      container.setLayout(null);
  }

  public void setLocationAndSize() {
    emailLabel.setBounds(10, 150, 100, 30);
    emailTextField.setBounds(150, 150, 150, 30);
    passwordLabel.setBounds(10, 220, 100, 30);
    passwordTextField.setBounds(150, 220, 150, 30);
    extraPasswordLabel.setBounds(10, 290, 150, 30);
    confirmPasswordTextField.setBounds(150, 290, 150, 30);
    showPassword.setBounds(150, 320, 150, 30);
    finishButton.setBounds(135, 460, 100, 30);
    BlackJackLabel.setBounds(50, 50 , 300, 100);
  }

  public void addComponentsToContainer() {
      container.add(emailLabel);
      container.add(passwordLabel);
      container.add(extraPasswordLabel);
      container.add(emailTextField);
      container.add(passwordTextField);
      container.add(confirmPasswordTextField);
      container.add(finishButton);
      container.add(showPassword);
      container.add(BlackJackLabel);

      
  }

  private void initCreateAccount(){

    createAccount frame = new createAccount();
    frame.setTitle("Create Account");
    Color myBlue = new Color(51,153,255);   
    frame.getContentPane().setBackground(myBlue);
    frame.setVisible(true);
    frame.setBounds(10, 10, 370, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

  }

  public void addActionEvent() {
    finishButton.addActionListener(this);
    showPassword.addActionListener(this);
   
}

@Override
public void actionPerformed(ActionEvent e) {
 
        if (e.getSource() == finishButton) {

          if (passwordTextField.getText().equals(confirmPasswordTextField.getText()) == false){

            JOptionPane.showMessageDialog(this, "Error. Passwords don't match");
            return;

          }

          int randomNum = ThreadLocalRandom.current().nextInt(100000,200000);

        // Recipient's email ID needs to be mentioned.
      String to = emailTextField.getText();

      if (client.checkIfAccountExists(to) == true){
        JOptionPane.showMessageDialog(this, "Error. Account already exists");
         login.isAccountWindowOpen = false;
        dispose();
        
      }
      else{



              
      // Sender's email ID needs to be mentioned
      String from = "Gameof21@yahoo.com";

      // Assuming you are sending email from localhost
     
      // Get system properties
      Properties properties = System.getProperties();
     
      // Setup mail server
      properties.setProperty("mail.smtp.host", "mail.smtpbucket.com");
        properties.setProperty("mail.smtp.port", "8025");
 
      // Get the default Session object.
      Session session = Session.getInstance(properties);
      

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
         

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,false));

         // Set Subject: header field
         message.setSubject("Verify Email");

         // Now set the actual message
         message.setText("Your code is " + randomNum);

         // Send message
         Transport.send(message);
         
          
        


      } catch (MessagingException mex) {
         mex.printStackTrace();
      }

      String code = JOptionPane.showInputDialog("Enter the code sent to your email for verification");
      

      if (code.equals( String.valueOf(randomNum))){

        Account account = new Account(emailTextField.getText(), passwordTextField.getText());

        client.addAccount(account);

          

        JOptionPane.showMessageDialog(this, "Your account is verified and ready for use");

        login.isAccountWindowOpen = false;
        dispose();

      



      }
      else{
        JOptionPane.showMessageDialog(this, "Error.Wrong Code");
        login.isAccountWindowOpen = false;
        dispose();
       
      }
    

      }
 
        }

        if (e.getSource() == showPassword) {
          if (showPassword.isSelected()) {
              passwordTextField.setEchoChar((char) 0);
              confirmPasswordTextField.setEchoChar((char) 0);
              
          } else {
              passwordTextField.setEchoChar('*');
              confirmPasswordTextField.setEchoChar('*');
          }


      }
    
     
     

 
}

 
}
