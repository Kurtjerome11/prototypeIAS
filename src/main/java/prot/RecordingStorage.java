package prot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Criselle Sayo
 */
public class RecordingStorage {
    private JFrame f1 = new JFrame("Mockfinity VR: AI-Powered Job Interview Trainer");
    private JLabel l1, l2;
    private JButton b1, b2;
    
    RecordingStorage() {
        
        ImageIcon bgIcon = new ImageIcon("Images//Storage.png");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        f1.setContentPane(background);
        f1.getContentPane().setLayout(null);
                
        // Title ("View Sessions Here")
        l1 = new JLabel("USER SESSION STORAGE");
        l1.setBounds(50, 20, 1000, 100);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        l1.setForeground(Color.WHITE);
        
        // Text under title
        l2 = new JLabel("View your sessions here:");
        l2.setBounds(55, 70, 1000, 110);
        l2.setFont(new  Font("Times New Roman", Font.BOLD, 30));
        l2.setForeground(Color.WHITE);
        
        //Enter New Session
        b2 = new JButton("Enter New Session");
        b2.setBounds(850, 600, 200, 50);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Calibri", Font.PLAIN, 22));
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                new Main();
            }
        });
        
        // Logout button
        b1 = new JButton("Logout");
        b1.setBounds(1070, 600, 100, 50);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Calibri", Font.PLAIN, 22));
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f1.dispose();
                new Login();
            }
        });
        
        // Final frame settings
        f1.setSize(1200, 700);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        
        // Add components AFTER background is set
        f1.add(l1);
        f1.add(l2);
        f1.add(b1);
        f1.add(b2);
    }
    
}
