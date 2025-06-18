package prot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

public class Login {
    private JFrame f1 = new JFrame("Mockfinity VR: AI-Powered Job Interview Trainer");
    private JLabel l1, l2, l3, l4, l5;
    private JButton b1, b2, b3;
    private JTextField t1;
    private JPasswordField t2;
    private int failedAttempts = 0;
    private Timer lockoutTimer;
    public static String loggedInUser = null;

    Login() {

        ImageIcon bgIcon = new ImageIcon("Images//fi1.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        f1.setContentPane(background);
        f1.getContentPane().setLayout(null);

        // Sign In label
        l1 = new JLabel("MOCKFINITY VR:");
        l1.setBounds(300, 20, 1000, 210);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        l1.setForeground(Color.WHITE);
        
        l5 = new JLabel("AI-Powered Job Interview Trainer");
        l5.setBounds(370, 70, 1000, 220);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l5.setForeground(Color.WHITE);

        // Optional title (currently unused)
        l2 = new JLabel();
        l2.setBounds(110, 20, 1000, 100);

        // Username and Password labels
        l3 = new JLabel("Username:");
        l3.setBounds(400, 285, 270, 35);
        l3.setFont(new Font("Calibri", Font.PLAIN, 23));
        l3.setForeground(Color.WHITE);

        l4 = new JLabel("Password:");
        l4.setBounds(400, 375, 270, 35);
        l4.setFont(new Font("Calibri", Font.PLAIN, 23));
        l4.setForeground(Color.WHITE);

        // Text fields
        t1 = new JTextField();
        t1.setBounds(520, 285, 270, 35);

        t2 = new JPasswordField();
        t2.setBounds(520, 375, 270, 35);

        // Sign In button
        b1 = new JButton("Sign In");
        b1.setBounds(620, 460, 150, 50);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Calibri", Font.PLAIN, 22));
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (failedAttempts < 3) {
                    String userName = t1.getText();
                    String password = new String(t2.getPassword());
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");

                        PreparedStatement st = connection.prepareStatement("Select name, password from login where name=? and password=?");
                        st.setString(1, userName);
                        st.setString(2, password);
                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            loggedInUser = userName;
                            logLoginAttempt(userName, "SUCCESS");
                            f1.dispose();
                            new Loading(); 
                        } else {
                            failedAttempts++;
                            logLoginAttempt(userName, "FAILED");
                            if (failedAttempts >= 3) {
                                lockoutLogin();
                            } else {
                                JOptionPane.showMessageDialog(f1, "Wrong Username & Password. Attempt " + failedAttempts + " of 3.");
                            }
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        // Register button
        b2 = new JButton("Sign Up");
        b2.setBounds(450, 460, 150, 50);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Calibri", Font.PLAIN, 22));
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                new Loading1(); 
            }
        });
        
        // Register button
         b3 = new JButton("Admin");
        b3.setBounds(1065, 605, 100, 40);
        b3.setForeground(Color.BLACK);
        b3.setFont(new Font("Calibri", Font.PLAIN, 22));
        b3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b3.addActionListener(e -> {
            f1.dispose();
            new AdminLogin();
        });


        //components
        f1.add(l1);
        f1.add(l3);
        f1.add(l4);
        f1.add(l5);
        f1.add(t1);
        f1.add(t2);
        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(l2);

        // Final frame
        f1.setSize(1200, 700);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
    }

    private void lockoutLogin() {
        JOptionPane.showMessageDialog(f1, "Too many failed attempts. Please wait 30 seconds before trying again.");
        setFieldsEnabled(false);

        lockoutTimer = new Timer();
        lockoutTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setFieldsEnabled(true);
                failedAttempts = 0;
                lockoutTimer.cancel();
            }
        }, 30000);
    }

    private void setFieldsEnabled(boolean enabled) {
        t1.setEnabled(enabled);
        t2.setEnabled(enabled);
        b1.setEnabled(enabled);
        b2.setEnabled(enabled);
        
        
    }
    
    private void logLoginAttempt(String username, String status) {
    try {
        String ipAddress = java.net.InetAddress.getLocalHost().getHostAddress();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");

        String sql = "INSERT INTO user_activity (username, login_time, ip_address, status) VALUES (?, NOW(), ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, ipAddress);
        statement.setString(3, status);

        statement.executeUpdate();
        statement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    


}