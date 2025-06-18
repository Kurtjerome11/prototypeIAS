/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prot;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Harvey
 */
public class AdminLogin {

    private JFrame frame;
    private JLabel title, subtitle, passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public AdminLogin() {
        frame = new JFrame("Mockfinity VR: Admin Login");
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Background
        ImageIcon bgIcon = new ImageIcon("Images//fi1.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 700);
        frame.setContentPane(background);
        background.setLayout(null);

        // Title
        title = new JLabel("MOCKFINITY VR:");
        title.setFont(new Font("Times New Roman", Font.BOLD, 70));
        title.setForeground(Color.WHITE);
        title.setBounds(300, 20, 1000, 210);
        background.add(title);

        // Subtitle
        subtitle = new JLabel("Administrator Login Portal");
        subtitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(390, 80, 1000, 220);
        background.add(subtitle);

        // Password Label
        passwordLabel = new JLabel("Admin Password:");
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(350, 320, 270, 35);
        background.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(520, 320, 270, 35);
        background.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(440, 390, 120, 50);
        loginButton.setFont(new Font("Calibri", Font.PLAIN, 22));
        loginButton.setForeground(Color.BLACK);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        loginButton.addActionListener(e -> checkAdminPassword());
        background.add(loginButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(620, 390, 120, 50);
        backButton.setFont(new Font("Calibri", Font.PLAIN, 22));
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backButton.addActionListener(e -> {
            frame.dispose();
            new Login();
        });
        background.add(backButton);

        frame.setVisible(true);
    }

    private void checkAdminPassword() {
        String inputPassword = new String(passwordField.getPassword());
        if (inputPassword.equals("admin123")) {
            frame.dispose();
            new RealAdmin();
        } else {
            JOptionPane.showMessageDialog(frame, "Incorrect Admin Password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }
    }
}
