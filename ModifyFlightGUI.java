
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;


public class ModifyFlightGUI extends JFrame {
    private JLabel definition;
    private AdminController adminController = new AdminController();
    private JTextField originText;
    private JLabel originLabel;
    private JLabel aircraftLabel;
    private JTextField aircraftText;
    private JLabel destLabel;
    private JTextField destText;
    private JLabel crewLabel;
    private JTextField crewText;
    private int id;

        public ModifyFlightGUI(int id){
        super("Admin Menu");
        this.id = id;
        setupGUI();
        setSize(700,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);   
        
    }
     public void setupGUI(){

        definition = new JLabel("Modifying Flight:");
        

        originLabel = new JLabel("New Origin:");
        originLabel.setBounds(420, 130, 100, 25);
        this.add(originLabel);

        originText = new JTextField(20);
        originText.setBounds(530, 130, 100, 25);
        this.add(originText);
        
        destLabel = new JLabel("New Destination:");
        destLabel.setBounds(420, 160, 100, 25);
        this.add(destLabel);

        destText = new JTextField(20);
        destText.setBounds(530, 160, 100, 25);
        this.add(destText);

        aircraftLabel = new JLabel("New Aircraft:");
        aircraftLabel.setBounds(220, 130, 100, 25);
        this.add(aircraftLabel);

        aircraftText = new JTextField(20);
        aircraftText.setBounds(300, 130, 100, 25);
        this.add(aircraftText);

        crewLabel = new JLabel("New Crew:");
        crewLabel.setBounds(20, 130, 100, 25);
        this.add(crewLabel);

        crewText = new JTextField(20);
        crewText.setBounds(100, 130, 100, 25);
        this.add(crewText);

        JButton addCrewButton = new JButton("Add Crew");
        addCrewButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                adminController.selectCrew(id, crewText.getText(), 0);
                JOptionPane.showMessageDialog(rootPane, "Crew Selected", "Success",  
                JOptionPane.DEFAULT_OPTION);

            }
        });
        JButton addAircraftButton = new JButton("Add Aircraft");
        addAircraftButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                adminController.selectAircraft(id, aircraftText.getText());
                JOptionPane.showMessageDialog(rootPane, "Aircraft Selected", "Success",  
            JOptionPane.DEFAULT_OPTION);
            }
        });
        JButton changeFlightButton = new JButton("Change Flight Plan");
        changeFlightButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                adminController.modifyFlightPlan(id, originText.getText(), destText.getText());
                JOptionPane.showMessageDialog(rootPane, "Flight Plan Changed", "Success",  
            JOptionPane.DEFAULT_OPTION);
            }
        });

        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(definition);

        addCrewButton.setBounds(400, 400, 200, 100);

        clientPanel.add(addCrewButton);
        clientPanel.add(addAircraftButton);
        clientPanel.add(changeFlightButton);


        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}




}