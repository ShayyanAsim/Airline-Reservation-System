
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class CrewGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private JTextArea textArea;
    private JTextField userText;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField passText;

        public CrewGUI(){
        super("Crew");
        setupGUI();
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);
        addMouseListener(this);
        
    }
     public void setupGUI(){


        
        
        userLabel = new JLabel("Email");
        userLabel.setBounds(20, 40, 80, 25);
        this.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(120, 40, 165, 25);
        this.add(userText);
        
        passLabel = new JLabel("Password");
        passLabel.setBounds(20, 70, 80, 25);
        this.add(passLabel);

        passText = new JTextField(20);
        passText.setBounds(120, 70, 165, 25);
        this.add(passText);
        
        definition = new JLabel("Login to View Passengers on your current flight:");
        
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
        
        if (User.loginCrew(userText.getText(), passText.getText())) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.createConnection();
        int flightID = dbConnection.getCrewFlightID(userText.getText());
        String textString = Crew.browsePassengers(flightID);
        textArea = new JTextArea(textString);
        textArea.setEditable(false);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(300, 400));
        JOptionPane.showMessageDialog(rootPane, scroll, "Passengers on flight " + flightID + ":",  
                                                        JOptionPane.DEFAULT_OPTION);
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
            new CrewGUI().setVisible(true);        
        });
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }
}