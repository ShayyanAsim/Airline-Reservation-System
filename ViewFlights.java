
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Component;


import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class ViewFlights extends JFrame implements ActionListener, MouseListener{
    private JLabel definition;
    private String email;
    private JTextArea textArea;
   

    


    /**
     * Constructs a new GUI object.
     */
    public ViewFlights(String email){
        super("View Available Flights");
        this.email = email;
        setupGUI();
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);    
        addMouseListener(this);
        
    }
    
    /**
     * Sets up the graphical user interface.
     */
    public void setupGUI(){
        JButton button = new JButton("Select Flight");
        button.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        String textString = "";
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.createConnection();
        List<String> flights = dbConnection.getFlightDetails();
        List<String> ids = dbConnection.getFlightIDs();
        List<Flight> theFlights = dbConnection.getFlights();
        for (int i = 0; i < flights.size(); i++) {
            JButton flightButton = new JButton(flights.get(i));
            flightButton.setBounds(20, 40, 125, 25);
            final int index = i;

            flightButton.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event){
                    String[] options = {"CONFIRM", "CANCEL"};
                    int optionSelected =JOptionPane.showOptionDialog(rootPane, "Confirmation: " + flights.get(index),
                    "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
                    if (optionSelected == 0) {
                        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                        currentFrame.dispose();
                        new SeatSelectGUI(ids.get(index), email).setVisible(true);
                    }
                }
    
            });
            clientPanel.add(flightButton);
        }
        textArea = new JTextArea(textString);
        
        
        definition = new JLabel("Available Filghts:");
        

        
        headerPanel.add(definition);

        // submitPanel.add(button);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
        

 
        // textArea.setEditable(false);  
        // textArea.setLineWrap(true);  
        // textArea.setWrapStyleWord(true); 
        // JScrollPane scroll = new JScrollPane(textArea);
        // scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // this.add(scroll, BorderLayout.CENTER);
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
            new ViewFlights("yoyoyo").setVisible(true);        
        });
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }
}



