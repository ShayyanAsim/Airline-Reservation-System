
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;



import java.awt.event.*;
import java.awt.FlowLayout;
import java.util.*;

public class AdminOptionsGUI extends JFrame implements ActionListener {
    private JLabel definition;
    private JTextArea textArea = new JTextArea();
    private AdminController adminController = new AdminController();
    private JTextField originText;
    private JLabel originLabel;
    private JLabel aircraftLabel;
    private JTextField aircraftText;
    private JLabel destLabel;
    private JTextField destText;
    private JLabel crewLabel;
    private JTextField crewText;

        public AdminOptionsGUI(){
        super("Admin Menu");
        setupGUI();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);   
        
    }
     public void setupGUI(){

        definition = new JLabel("Logged in as Admin, Please select an option:");
        
        JButton browseFlightsButton = new JButton("Browse Flights");
        browseFlightsButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                new AdminViewFlightsGUI(false).setVisible(true);
            }

        });
        JButton browseUsersButton = new JButton("Browse Users");
        browseUsersButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                String textString = adminController.browseUsers();
                textArea = new JTextArea(textString);
                textArea.setEditable(false);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true); 
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setPreferredSize(new Dimension(600, 400));
                JOptionPane.showMessageDialog(rootPane, scroll, "All Users in Database:",  
                                                                JOptionPane.DEFAULT_OPTION);

            }
        });
        JButton browseAircraftsButton = new JButton("Browse Aircrafts");
        browseAircraftsButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                String textString = adminController.browseAircrafts();
                textArea = new JTextArea(textString);
                textArea.setEditable(false);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true); 
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setPreferredSize(new Dimension(600, 400));
                JOptionPane.showMessageDialog(rootPane, scroll, "All Aircrafts in Database:",  
                                                                JOptionPane.DEFAULT_OPTION);
            }
        });

        originLabel = new JLabel("Enter Origin:");
        originLabel.setBounds(120, 160, 125, 25);
        this.add(originLabel);

        originText = new JTextField(20);
        originText.setBounds(280, 160, 165, 25);
        this.add(originText);
        
        destLabel = new JLabel("Enter Destination:");
        destLabel.setBounds(120, 190, 125, 25);
        this.add(destLabel);

        destText = new JTextField(20);
        destText.setBounds(280, 190, 165, 25);
        this.add(destText);

        aircraftLabel = new JLabel("Enter Aircraft:");
        aircraftLabel.setBounds(120, 220, 125, 25);
        this.add(aircraftLabel);

        aircraftText = new JTextField(20);
        aircraftText.setBounds(280, 220, 165, 25);
        this.add(aircraftText);

        crewLabel = new JLabel("Enter Crew:");
        crewLabel.setBounds(120, 250, 125, 25);
        this.add(crewLabel);

        crewText = new JTextField(20);
        crewText.setBounds(280, 250, 165, 25);
        this.add(crewText);

        JButton addFlightButton = new JButton("Add Flight");
        addFlightButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                dbConnection.createConnection();
                List<String> ids = dbConnection.getFlightIDs();
                int newId = Integer.valueOf(ids.get(ids.size()));
                adminController.addFlight(newId, originText.getText(), destText.getText(), crewText.getText(), aircraftText.getText());

            }
        });
        JButton removeFlightButton = new JButton("Remove Flight");
        removeFlightButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                new AdminViewFlightsGUI(true).setVisible(true);
            }
        });

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(definition);

        addFlightButton.setBounds(400, 400, 200, 100);
        clientPanel.add(browseFlightsButton);
        clientPanel.add(browseUsersButton);
        clientPanel.add(browseAircraftsButton);
        submitPanel.add(addFlightButton);
        clientPanel.add(removeFlightButton);

        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}

    public void actionPerformed(ActionEvent event){

    }
    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new AdminOptionsGUI().setVisible(true);        
        });
    }

}