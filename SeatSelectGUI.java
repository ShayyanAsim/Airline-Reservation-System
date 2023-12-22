
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Component;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;
import java.awt.Color;

public class SeatSelectGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private String flight;
    private String email;
    private int availableSeats;
    private int counter =0;

        public SeatSelectGUI(String flight, String email){
        super("Seat Select");
        this.flight = flight;
        this.email = email;
        setupGUI();
        setSize(250,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);    
        addMouseListener(this);
        
    }
     public void setupGUI(){

        
        
        definition = new JLabel("Select Seat:");
        
        JButton button = new JButton("Confirm");
        button.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(definition);
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.createConnection();
        availableSeats = dbConnection.numSeats(Integer.valueOf(flight));
        submitPanel.add(button);
        for (int i = 0; i  < availableSeats * 0.1; i++) {
            JButton flightButton = new JButton(Integer.toString(counter + 1) + ' ');
            flightButton.setBounds(20, 40, 125, 25);
            final int index = counter + 1;
            if (dbConnection.isSeatTaken(Integer.valueOf(flight), index)) {
                flightButton.setBackground(Color.RED);
            }
            else {
                flightButton.setBackground(Color.GREEN);
            }
 
            flightButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event){
                    String[] options = {"CONFIRM", "CANCEL"};
                    if (!dbConnection.isSeatTaken(Integer.valueOf(flight), index)) {
                        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                        dbConnection.createConnection();
                        double price = dbConnection.getPrice(Integer.valueOf(flight));
                        price = price * 2.1;
                        int optionSelected =JOptionPane.showOptionDialog(rootPane, "Confirmation: Seat " + index + ". This is a Business Class Seat, Price: " + price ,
                        "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
                        if (optionSelected == 0) {
                            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                            currentFrame.dispose();
                            new PaymentGUI(index, flight, email, price).setVisible(true);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Seat is taken please select another seat", "Seat Taken",  
                        JOptionPane.DEFAULT_OPTION);
                    }
                    
                }

                
    
            });
            clientPanel.add(flightButton);
            counter++;
        }
        for (int i = 0; i  < availableSeats * 0.205; i++) {
            JButton flightButton = new JButton(Integer.toString(counter + 1) + ' ');
            flightButton.setBounds(20, 40, 125, 25);
            final int index = counter + 1;
            if (dbConnection.isSeatTaken(Integer.valueOf(flight), index)) {
                flightButton.setBackground(Color.RED);
            }
            else {
                flightButton.setBackground(Color.YELLOW);
            }
 
            flightButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event){
                    String[] options = {"CONFIRM", "CANCEL"};
                    if (!dbConnection.isSeatTaken(Integer.valueOf(flight), index)) {
                        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                        dbConnection.createConnection();
                        double price = dbConnection.getPrice(Integer.valueOf(flight));
                        price = price * 1.4;
                        int optionSelected =JOptionPane.showOptionDialog(rootPane, "Confirmation: Seat " + index + ". This is a Comfort Class Seat, Price: " + price,
                        "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
                        if (optionSelected == 0) {
                            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                            currentFrame.dispose();
                            new PaymentGUI(index, flight, email, price).setVisible(true);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Seat is taken please select another seat", "Seat Taken",  
                        JOptionPane.DEFAULT_OPTION);
                    }
                    
                }
    
            });
            clientPanel.add(flightButton);
            counter++;
        }

        for (int i = 0; i  < availableSeats * 0.68; i++) {
            JButton flightButton = new JButton(Integer.toString(counter + 1) + ' ');
            flightButton.setBounds(20, 40, 125, 25);
            final int index = counter + 1;
            if (dbConnection.isSeatTaken(Integer.valueOf(flight), index)) {
                flightButton.setBackground(Color.RED);
            }

 
            flightButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event){
                    String[] options = {"CONFIRM", "CANCEL"};
                    if (!dbConnection.isSeatTaken(Integer.valueOf(flight), index)) {
                        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                        dbConnection.createConnection();
                        double price = dbConnection.getPrice(Integer.valueOf(flight));
                        int optionSelected =JOptionPane.showOptionDialog(rootPane, "Confirmation: Seat " + index + ". This is an Ordinary Class Seat, Price: " + price,
                        "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
                        if (optionSelected == 0) {
                            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                            currentFrame.dispose();
                            new PaymentGUI(index, flight, email, price).setVisible(true);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Seat is taken please select another seat", "Seat Taken",  
                        JOptionPane.DEFAULT_OPTION);
                    }
                    
                }
    
            });
            clientPanel.add(flightButton);
            counter++;
        }
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}

    public void actionPerformed(ActionEvent event){

        
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
            new SeatSelectGUI("wooo", "email").setVisible(true);        
        });
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }
}