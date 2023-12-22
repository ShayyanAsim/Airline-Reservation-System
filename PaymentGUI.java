
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Component;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class PaymentGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private int seat;
    private double price;
    private String flight;
    private String email;
    private JTextField userText;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField passText;
    private JLabel insuranceLabel;
    private JTextField insuranceText;

        public PaymentGUI(int seat, String flight, String email, double price){
        super("Seat Select");
        this.seat = seat;
        this.flight = flight;
        this.email = email;
        this.price = price;
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);    
        addMouseListener(this);
        
    }
     public void setupGUI(){

        
        
        definition = new JLabel("Enter Payment Info:");

        userLabel = new JLabel("Please Enter Email For Flight Detalis:");
        userLabel.setBounds(20, 40, 260, 25);
        this.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(260, 40, 165, 25);
        this.add(userText);
        
        passLabel = new JLabel("Enter Your Credit Card Number:");
        passLabel.setBounds(20, 70, 260, 25);
        this.add(passLabel);

        passText = new JTextField(20);
        passText.setBounds(260, 70, 165, 25);
        this.add(passText);

        passLabel = new JLabel("Expiration Date:");
        passLabel.setBounds(160, 100, 150, 25);
        this.add(passLabel);

        passText = new JTextField(20);
        passText.setBounds(260, 100, 50, 25);
        this.add(passText);

        passLabel = new JLabel("CVV:");
        passLabel.setBounds(320, 100, 30, 25);
        this.add(passLabel);

        passText = new JTextField(20);
        passText.setBounds(360, 100, 30, 25);
        this.add(passText);

        insuranceLabel = new JLabel("Cancellation Insurance? (Yes/No):");
        insuranceLabel.setBounds(20, 130, 260, 25);
        this.add(insuranceLabel);

        insuranceText = new JTextField(20);
        insuranceText.setBounds(260, 130, 165, 25);
        this.add(insuranceText);
        
        JButton button = new JButton("Make Payment");
        button.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(definition);

        submitPanel.add(button);
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.getConnection();
        
        String member = dbConnection.isMember(email);
        if (member.equals("True")) {
            price = price - 10;
            JLabel discountLabel = new JLabel("10 Dollar Discount Because of Membership!");
            discountLabel.setBounds(180, 190, 300, 25);
            this.add(discountLabel);
        }
        JLabel priceLabel = new JLabel("Price: " + price);
        priceLabel.setBounds(180, 160, 260, 25);
        this.add(priceLabel);


        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}

    public void actionPerformed(ActionEvent event){
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.getConnection();
        String cancel;
        String name = dbConnection.getNameFromEmail(email);
        if (insuranceText.getText().toUpperCase().equals("YES")) {
            cancel = "yes";
        }
        else {
            cancel = "no";
        }

        String[] options = {"CONFIRM", "CANCEL"};
        int optionSelected =JOptionPane.showOptionDialog(rootPane, "Confirm Payment",
        "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
        if (optionSelected == 0) {
            dbConnection.insertPassengers(Integer.valueOf(flight), name, seat, cancel);
            // dbConnection.bookSeatAndUpdateMaxseat(Integer.valueOf(flight));
            JOptionPane.showMessageDialog(rootPane, "Thank you for booking your flight!", "Success",  
            JOptionPane.DEFAULT_OPTION);
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
            currentFrame.dispose();
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
    
    


    
    public void mouseClicked(MouseEvent e) {
        
    }
}