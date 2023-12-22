
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;



import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;
import java.awt.Component;

public class Main extends JFrame implements ActionListener{
    private JLabel definition;
    OurComponent t;
    // BorderDecorator deco = new BorderDecorator(t, 618, 340);

        public Main(){
        super("Group 7 Airlines");
        t = new Text("Group 7 Airlines", 618, 340);
        setupGUI();
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);   
        
    }
    public void paintComponent(Graphics g){
		int fontSize = 10;
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		// GlassFrameDecorator info: x = 25, y = 25, width = 110, and height = 110
		t = new BorderDecorator(t, 618,340);
		t.draw(g);
	}
     public void setupGUI(){

        definition = new JLabel("Welcome to Group 7 Airlines, Please select an option:");
        
        JButton userLoginButton = new JButton("Login as User");
        userLoginButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                currentFrame.dispose();
                new UserGUI().setVisible(true);

                
            }

        });
        JButton adminLoginButton = new JButton("Login as Admin");
        adminLoginButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                currentFrame.dispose();
                new AdminGUI().setVisible(true);
            }
        });
        JButton crewLoginButton = new JButton("Login as Crew");
        crewLoginButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                currentFrame.dispose();
                new CrewGUI().setVisible(true);
            }
        });
        JButton userRegButton = new JButton("Register as User");
        userRegButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) event.getSource()); 
                currentFrame.dispose();
                new UserRegGUI().setVisible(true);
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
        clientPanel.add(adminLoginButton);
        clientPanel.add(crewLoginButton);
        clientPanel.add(userRegButton);
        this.getContentPane().add(t);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
}

    public void actionPerformed(ActionEvent event){

    }
    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new Main().setVisible(true);        
        });
    }

}