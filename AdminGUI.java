import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;


public class AdminGUI extends JFrame implements ActionListener{
    private JLabel definition;
    private JTextField userText;
    private JLabel userLabel;
    private JLabel passLabel;
    private JTextField passText;
    


        public AdminGUI(){
        super("Admin");
        setupGUI();
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);    
        
    }
     public void setupGUI(){

        userLabel = new JLabel("Enter Your Password:");
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
        
        
        definition = new JLabel("Enter Login Info:");
        
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
        if (User.loginAdmin(userText.getText(), passText.getText())) {
            new AdminOptionsGUI().setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "Please Enter Valid Credentials", "Login Failed",  
            JOptionPane.DEFAULT_OPTION);
        }
        
    }
    
}