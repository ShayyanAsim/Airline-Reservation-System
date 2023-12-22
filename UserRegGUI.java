
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;



import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class UserRegGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private JTextField userText;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField passText;
    private JTextField nameText;
    private JLabel nameLabel;
    private JTextField addressText;
    private JLabel addressLabel;
    private JTextField memberText;
    private JLabel memberLabel;
    private boolean valid;
    
    

        public UserRegGUI(){
        super("User");
        setupGUI();
        setSize(400,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);
        addMouseListener(this);
        
    }
     public void setupGUI(){

        
        
        definition = new JLabel("Make Your Account:");

        userLabel = new JLabel("Enter Your Email:");
        userLabel.setBounds(20, 40, 125, 25);
        this.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(180, 40, 165, 25);
        this.add(userText);
        
        passLabel = new JLabel("Choose a Password:");
        passLabel.setBounds(20, 70, 125, 25);
        this.add(passLabel);

        passText = new JTextField(20);
        passText.setBounds(180, 70, 165, 25);
        this.add(passText);

        nameLabel = new JLabel("Enter Your Full Name:");
        nameLabel.setBounds(20, 100, 150, 25);
        this.add(nameLabel);

        nameText = new JTextField(20);
        nameText.setBounds(180, 100, 165, 25);
        this.add(nameText);

        addressLabel = new JLabel("Enter Your Address:");
        addressLabel.setBounds(20, 130, 125, 25);
        this.add(addressLabel);

        addressText = new JTextField(20);
        addressText.setBounds(180, 130, 165, 25);
        this.add(addressText);

        memberLabel = new JLabel("Become a Memebr? (Yes/No):");
        memberLabel.setBounds(20, 160, 260, 25);
        this.add(memberLabel);

        memberText = new JTextField(20);
        memberText.setBounds(260, 160, 165, 25);
        this.add(memberText);


        
        JButton button = new JButton("Register");
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
        
        JOptionPane.showMessageDialog(rootPane, "Thank you for registering", "Success",  
        JOptionPane.DEFAULT_OPTION);
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.createConnection();
        String ismember;
        if (memberText.getText().toUpperCase().equals("YES")) {
            ismember = "True";
        }
        else {
            ismember = "False";
        }

        dbConnection.insertUser(nameText.getText(), userText.getText(), addressText.getText(), passText.getText(), ismember);
        
        
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
            new UserRegGUI().setVisible(true);        
        });
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }
}