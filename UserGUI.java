
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Component;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class UserGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private JTextField userText;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField passText;


        public UserGUI(){
        super("User");
        setupGUI();
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);    
        addMouseListener(this);
        
    }
     public void setupGUI(){

        
        
        definition = new JLabel("Enter Login Info:");
        userLabel = new JLabel("Enter Your Email:");
        userLabel.setBounds(20, 40, 125, 25);
        this.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(180, 40, 165, 25);
        this.add(userText);
        
        passLabel = new JLabel("Enter Your Password:");
        passLabel.setBounds(20, 70, 125, 25);
        this.add(passLabel);

        passText = new JTextField(20);
        passText.setBounds(180, 70, 165, 25);
        this.add(passText);
        
        JButton button = new JButton("Login");
        button.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(definition);

        submitPanel.add(button);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}

    public void actionPerformed(ActionEvent event){
  

        if (User.loginUser(userText.getText(), passText.getText())) {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
            currentFrame.dispose();
            new UserSelectGUI(userText.getText()).setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Valid Credentials", "Login Failed",  
            JOptionPane.DEFAULT_OPTION);
        }
    }
    
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){


    }
    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new UserGUI().setVisible(true);        
        });
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }
}