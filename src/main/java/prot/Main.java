 package prot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public JFrame f1 = new JFrame("Mockfinity VR: AI-Powered Job Interview Trainer");
    private JLabel l1, l2, l3, l4, l5;
    private JButton b1;
    private ScreenRecorder recorder;
    
    Main() {
        // Final frame settings
        f1.setSize(1200, 700);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
    
        // For screen recorder
        ScreenRecorder recorder = new ScreenRecorder(f1);
        recorder.start();

        ImageIcon bgIcon = new ImageIcon("Images//fi2.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        f1.setContentPane(background);
        f1.getContentPane().setLayout(null);

        // Sign In label
        l1 = new JLabel("WELCOME!");
        l1.setBounds(50, 20, 1000, 100);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        l1.setForeground(Color.WHITE);
        
        // Text under Welcome
        l5 = new JLabel("Your session is being recorded.");
        l5.setBounds(55, 70, 1000, 110);
        l5.setFont(new  Font("Times New Roman", Font.BOLD, 30));
        l5.setForeground(Color.WHITE);
        
        // View Recordings Button
        b1 = new JButton("End Session");
        b1.setBounds(1020, 600, 150, 50);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Calibri", Font.PLAIN, 22));
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recorder.stop();
                f1.dispose();
                new RecordingStorage();
            }
        });
                
                
        // Add components AFTER background is set
        f1.add(l1);
        f1.add(l5);
        f1.add(b1);
    }

}
