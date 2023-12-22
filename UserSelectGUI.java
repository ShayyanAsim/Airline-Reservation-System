
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Component;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class UserSelectGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private String email;
    private JTextField userText;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField passText;


        public UserSelectGUI(String email){
        super("User");
        this.email = email;
        setupGUI();
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);    
        addMouseListener(this);
        
    }
     public void setupGUI(){

        definition = new JLabel("Please Select an Option:");

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        JButton bookFlightButton = new JButton("Book a Flight");
        bookFlightButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
            currentFrame.dispose();
                new ViewFlights(email).setVisible(true);
            }

        });
        JButton cancelFlightButton = new JButton("Cancel a Flight");
        cancelFlightButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
            currentFrame.dispose();
                new CancelGUI(email).setVisible(true);
            }
        });
        
        headerPanel.add(definition);
        clientPanel.add(bookFlightButton);
        clientPanel.add(cancelFlightButton);


        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}

    public void actionPerformed(ActionEvent event){}

    
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