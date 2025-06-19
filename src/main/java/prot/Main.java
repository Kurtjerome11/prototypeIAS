package prot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public JFrame f1 = new JFrame("Mockfinity VR: AI-Powered Job Interview Trainer");
    private JLabel l1, l5, l6,feedbackLabel;
    private JButton b1, submitBtn;
    private JTextField feedbackField;
    private ScreenRecorder recorder;

    Main() {
        // Final frame settings
        f1.setSize(1200, 700);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);

        // For screen recorder
        recorder = new ScreenRecorder(f1);
        recorder.start();

        // Background
        ImageIcon bgIcon = new ImageIcon("Images//fi2.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        f1.setContentPane(background);
        f1.getContentPane().setLayout(null);

        // Welcome label
        l1 = new JLabel("WELCOME!");
        l1.setBounds(50, 20, 1000, 100);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        l1.setForeground(Color.WHITE);

        // Subtext label
        l5 = new JLabel("Answer Honestly, GOODLUCK!");
        l5.setBounds(55, 70, 1000, 110);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l5.setForeground(Color.WHITE);
        
        l6 = new JLabel("NOTE: Your session is being recorded.");
        l6.setBounds(900, -25, 1000, 110);
        l6.setFont(new Font("Times New Roman", Font.BOLD, 15));
        l6.setForeground(Color.WHITE);

        // Feedback label
        feedbackLabel = new JLabel("Feedback/Comment:");
        feedbackLabel.setBounds(25, 600, 200, 30);
        feedbackLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        feedbackLabel.setForeground(Color.WHITE);

        // Feedback text field (single-line)
        feedbackField = new JTextField();
        feedbackField.setBounds(240, 600, 400, 30);
        feedbackField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        feedbackField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        feedbackField.setBackground(new Color(255, 255, 255, 230));

        // Submit feedback button
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(650, 600, 100, 30);
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitBtn.setBackground(new Color(200, 255, 200));
        submitBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submitBtn.setFocusPainted(false);
        submitBtn.addActionListener(e -> {
            String feedback = feedbackField.getText().trim();
            if (!feedback.isEmpty()) {
                JOptionPane.showMessageDialog(f1, "Thank you for your feedback!", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
                feedbackField.setText("");
            } else {
                JOptionPane.showMessageDialog(f1, "Please enter your feedback before submitting.", "Empty Feedback", JOptionPane.WARNING_MESSAGE);
            }
        });

        // End Session button
        b1 = new JButton("End Session");
        b1.setBounds(1020, 600, 150, 50);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Calibri", Font.PLAIN, 22));
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b1.addActionListener(e -> {
            recorder.stop();
            f1.dispose();
            new RecordingStorage();
        });

        // Add components to the content pane
        f1.add(l1);
        f1.add(l5);
        f1.add(l6);
        f1.add(feedbackLabel);
        f1.add(feedbackField);
        f1.add(submitBtn);
        f1.add(b1);
    }
}
