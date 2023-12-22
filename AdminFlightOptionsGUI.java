
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Component;


public class AdminFlightOptionsGUI extends JFrame {
    private JLabel definition;
    private JTextArea textArea = new JTextArea();
    private int id;

        public AdminFlightOptionsGUI(int id){
        super("Admin Flight Menu");
        this.id = id;
        setupGUI();
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);   
        
    }
     public void setupGUI(){

        definition = new JLabel("Please select an option:");
        
        JButton userLoginButton = new JButton("View Passengers");
        userLoginButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                dbConnection.createConnection();
                
                String textString = Crew.browsePassengers(id);
                textArea = new JTextArea(textString);
                textArea.setEditable(false);  
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true); 
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setPreferredSize(new Dimension(300, 400));
                JOptionPane.showMessageDialog(rootPane, scroll, "Passengers on flight " + id + ":",  
                                                                JOptionPane.DEFAULT_OPTION);
            }

        });

        JButton userRegButton = new JButton("Modify Flight");
        userRegButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                new ModifyFlightGUI(id).setVisible(true);
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                        currentFrame.dispose();

            }
        });

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(definition);

        clientPanel.add(userLoginButton);
        clientPanel.add(userRegButton);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }

}
