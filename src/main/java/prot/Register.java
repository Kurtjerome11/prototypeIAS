package prot;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Register{
    private JFrame f1 = new JFrame("Sign Up");
    private JLabel l1, l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,limg;
    private JButton b1, b2; 
    private JPanel p1;
    private JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    private JPasswordField t12;
    
    

    Register() {

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\Mhacee Caryl\\Downloads\\fi.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        f1.setContentPane(background);
        f1.getContentPane().setLayout(null);
        // Frame settings
        f1.setSize(1200, 700);
        f1.getContentPane().setBackground(Color.BLACK);
        
        // Label settings
        l1 = new JLabel("Sign Up/Register");
        l1.setBounds(350, 40, 1000, 100);
        l1.setFont(new Font("Times New Roman",Font.BOLD,70));
        l1.setForeground(Color.WHITE);
        
        l2 = new JLabel();
        l2.setBounds(110, 20, 1000, 100);
        
        l3 = new JLabel("Student No. :");
        l3.setBounds(130, 170, 270, 35);
        l3.setFont(new Font("Calibri",Font.PLAIN,23));
        l3.setForeground(Color.WHITE);
        
        l4 = new JLabel("Last Name:");
        l4.setBounds(130, 230, 270, 35);
        l4.setFont(new Font("Calibri",Font.PLAIN,23));
        l4.setForeground(Color.WHITE);
        
        l5 = new JLabel("First Name:");
        l5.setBounds(130, 290, 270, 35);
        l5.setFont(new Font("Calibri",Font.PLAIN,23));
        l5.setForeground(Color.WHITE);
        
        l6 = new JLabel("Middle Name:");
        l6.setBounds(130, 350, 270, 35);
        l6.setFont(new Font("Calibri",Font.PLAIN,23));
        l6.setForeground(Color.WHITE);
        
        l7 = new JLabel("Course:");
        l7.setBounds(130, 410, 270, 35);
        l7.setFont(new Font("Calibri",Font.PLAIN,23));
        l7.setForeground(Color.WHITE);
        
        l8 = new JLabel("Year:");
        l8.setBounds(600, 170, 270, 35);
        l8.setFont(new Font("Calibri",Font.PLAIN,23));
        l8.setForeground(Color.WHITE);
        
        l13 = new JLabel("Address:");
        l13.setBounds(130, 470, 270, 35);
        l13.setFont(new Font("Calibri",Font.PLAIN,23));
        l13.setForeground(Color.WHITE);
        
        l9 = new JLabel("Contact No.:");
        l9.setBounds(600, 230, 270, 35);
        l9.setFont(new Font("Calibri",Font.PLAIN,23));
        l9.setForeground(Color.WHITE);
        
        l10 = new JLabel("Birthday:");
        l10.setBounds(600, 290, 270, 35);
        l10.setFont(new Font("Calibri",Font.PLAIN,23));
        l10.setForeground(Color.WHITE);
        
        l11 = new JLabel("Position:");
        l11.setBounds(600, 350, 270, 35);
        l11.setFont(new Font("Calibri",Font.PLAIN,23));
        l11.setForeground(Color.WHITE);
        
        l12 = new JLabel("Organization:");
        l12.setBounds(600, 410, 270, 35);
        l12.setFont(new Font("Calibri",Font.PLAIN,23));
        l12.setForeground(Color.WHITE);
        
        l14 = new JLabel("New Password:");
        l14.setBounds(600, 476, 270, 35);
        l14.setFont(new Font("Calibri",Font.PLAIN,23));
        l14.setForeground(Color.WHITE);
        
        //buttons and its settings
        b1 = new JButton("Back to Sign In");
        b1.setBounds(1000, 600, 120, 30);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                new Login();
            }
        });
              
        b2 = new JButton("Register");
        b2.setBounds(520, 540, 150, 50);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Calibri", Font.PLAIN, 22));
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register1();
   
                String StudentN = t1.getText();
                String LastName = t2.getText();
                String FirstName = t3.getText();
                String MiddleName = t4.getText();
                String Course = t5.getText();
                String Year = t6.getText();
                String Address = t7.getText();
                String ContactN = t8.getText();
                String Bday = t9.getText();
                String Position = t10.getText();
                String Org = t11.getText();
                String pass = t12.getText();
                

                try {
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root","")) {
                        String query = "INSERT INTO tbl_cite (Student_ID, Student_LastName , Student_FirstName , Student_MiddleName , Course , Student_Year , Address , Contact_Number , Birthday , Position,Affiliation ) "
                                + "Values('" + StudentN + "','" + LastName + "','" + FirstName + "','" + MiddleName + "','" + Course + "','" + Year + "','"+Address+"','"+ContactN+"','"+Bday+"','"+Position+"','"+Org+"')";
                        
                        Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                
                try {
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root","")) {
                        String query = "INSERT INTO login (name,password) "
                                + "Values('" + FirstName + "','" + pass + "')";
                        
                        Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                t7.setText("");
                t8.setText("");
                t9.setText("");
                t10.setText("");
                t11.setText("");
                t12.setText("");
            }
        });
        
        t1 = new JTextField();
        t1.setBounds(280, 170, 270, 35);
    
        t2 = new JTextField();
        t2.setBounds(280, 230, 270, 35);
        
        t3 = new JTextField("(This will serve as your 'Username')");
        t3.setBounds(280, 290, 270, 35);
        t3.setForeground(Color.GRAY);
        t3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (t3.getText().equals("(This will serve as your 'Username')")) {
                    t3.setText("");
                    t3.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (t3.getText().isEmpty()) {
                    t3.setForeground(Color.GRAY);
                    t3.setText("(This will serve as your 'Username')");
                }
            }
        });
        
        t4 = new JTextField();
        t4.setBounds(280, 350, 270, 35);
        
        t5 = new JTextField();
        t5.setBounds(280, 410, 270, 35);
        
        t6 = new JTextField();
        t6.setBounds(280, 470, 270, 35);
        
        t7 = new JTextField();
        t7.setBounds(750, 170, 270, 35);
        
        t8 = new JTextField();
        t8.setBounds(750, 230, 270, 35);
        
        t9 = new JTextField();
        t9.setBounds(750, 290, 270, 35);
        
        t10 = new JTextField();
        t10.setBounds(750, 350, 270, 35);
        
        t11 = new JTextField();
        t11.setBounds(750, 410, 270, 35);
        
        t12 = new JPasswordField();
        t12.setBounds(750, 475, 270, 35);
              

        
        
        // Add objects to the frame
        f1.add(l1);
        f1.add(l3);
        f1.add(l4);
        f1.add(l5);
        f1.add(l6);
        f1.add(l7);
        f1.add(l8);
        f1.add(l9);
        f1.add(l10);
        f1.add(l11);
        f1.add(l13);
        f1.add(l14);
        f1.add(l12);
        f1.add(t1);
        f1.add(t2);
        f1.add(t3);
        f1.add(t4);
        f1.add(t5);
        f1.add(t6);
        f1.add(t7);
        f1.add(t8);
        f1.add(t9);
        f1.add(t10);
        f1.add(t11);
        f1.add(t12);
        f1.add(b1);
        f1.add(b2);
        
        f1.add(l2);
        
        
        // Frame settings 2.0
        f1.setVisible(true);
        f1.setLocationRelativeTo(null);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void Register1() {
                String StudentN = t1.getText();
                String LastName = t2.getText();
                String FirstName = t3.getText();
                String MiddleName = t4.getText();
                String Course = t5.getText();
                String Year = t6.getText();
                String Address = t7.getText();
                String ContactN = t8.getText();
                String Bday = t9.getText();
                String Position = t10.getText();
                String Org = t11.getText();
                String pass = t12.getText();

        if (StudentN.isEmpty() || LastName.isEmpty() || FirstName.isEmpty() || MiddleName.isEmpty() || Course.isEmpty()| 
                Year.isEmpty()| Address.isEmpty()| ContactN.isEmpty()| Bday.isEmpty()| Position.isEmpty()| Org.isEmpty()| 
                pass.isEmpty()) {
            JOptionPane.showMessageDialog(f1, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
                       JOptionPane.showMessageDialog(f1, "You are Successfully Registered!", "Success!", 1);
                    }
     
    }
    


}

