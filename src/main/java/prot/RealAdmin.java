/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Harvey
 */
public class RealAdmin {

    private JFrame frame;

    public RealAdmin() {
        frame = new JFrame("Mockfinity VR – Admin Dashboard");
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon bgIcon = new ImageIcon("Images//al.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1200, 700);
        frame.setContentPane(background);
        background.setLayout(null);

        JLabel title = new JLabel("ADMIN MENU");
        title.setFont(new Font("Georgia", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setBounds(420, 50, 600, 50);
        background.add(title);

        // === Buttons ===
        JButton btnQuestionnaire = createButton("Manage Questionnaire");
        JButton btnUsers = createButton("View Registered Users");
        JButton btnActivity = createButton("View Activity Logs");
        JButton btnSettings = createButton("System Settings");
        JButton btnLogout = createButton("Logout");

        // Set button positions
        btnQuestionnaire.setBounds(430, 150, 320, 60);
        btnUsers.setBounds(430, 230, 320, 60);
        btnActivity.setBounds(430, 310, 320, 60);
        btnSettings.setBounds(430, 390, 320, 60);
        btnLogout.setBounds(430, 480, 320, 60);

        // Add to UI
        background.add(btnQuestionnaire);
        background.add(btnUsers);
        background.add(btnActivity);
        background.add(btnSettings);
        background.add(btnLogout);

        // Button Actions
        btnQuestionnaire.addActionListener(e -> {
            frame.dispose();
            new Admin(); 
        });

        btnUsers.addActionListener(e -> {
            frame.dispose();
            new ViewRegisteredUser();
        });

        btnActivity.addActionListener(e -> {
            frame.dispose();
            new ViewActivityLogs();
        });

        btnSettings.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Feature coming soon: System Settings.");
        });

        btnLogout.addActionListener(e -> {
            frame.dispose();
            new Login();
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setBackground(new Color(240, 240, 240));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return btn;
    }
}