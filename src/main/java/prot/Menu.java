package prot;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Menu {
    private JFrame f1 = new JFrame("Mockfinity VR: AI-Powered Job Interview Trainer");
    private JLabel l1, l2, l3, l5;
    private JButton b1, b2, b3, b4; 

    Menu() {
        ImageIcon bgIcon = new ImageIcon("Images//se.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        f1.setContentPane(background);
        f1.getContentPane().setLayout(null);

        l3 = new JLabel("WELCOME TO");
        l3.setBounds(490, -30, 1000, 450);
        l3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l3.setForeground(Color.WHITE);

        l1 = new JLabel("MOCKFINITY VR:");
        l1.setBounds(300, 20, 1000, 460);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        l1.setForeground(Color.WHITE);

        l5 = new JLabel("AI-Powered Job Interview Trainer");
        l5.setBounds(370, 70, 1000, 470);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l5.setForeground(Color.WHITE);

        l2 = new JLabel();
        l2.setBounds(110, 20, 1000, 100);

        b1 = new JButton("Instructions");
        b1.setBounds(600, 360, 150, 50);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Calibri", Font.PLAIN, 22));
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b1.addActionListener(e -> {
            String instructions = "Welcome to Mockfinity VR!\n\n"
                    + "This AI-powered job interview training system is designed to help you:\n"
                    + "- Practice realistic interview scenarios using Virtual Reality\n"
                    + "- Improve communication, confidence, and problem-solving skills\n"
                    + "- Get feedback based on your performance\n\n"
                    + "Instructions:\n"
                    + "1. Click the 'Start' button to begin the simulation.\n"
                    + "2. Wear your VR headset (if supported) and ensure it's connected.\n"
                    + "3. Respond to the AI-generated interview questions as naturally as possible.\n\n"
                    + "Note:\n"
                    + "- Make sure your microphone and headset are working properly.\n"
                    + "- You can repeat the session as many times as you like.\n"
                    + "- Your progress may be recorded for academic evaluation.\n\n"
                    + "For best results, practice in a quiet environment.\n"
                    + "Good luck and enjoy your training with Mockfinity VR!";
            JOptionPane.showMessageDialog(f1, instructions, "Mockfinity VR Instructions", JOptionPane.INFORMATION_MESSAGE);
        });

        b2 = new JButton("Start");
        b2.setBounds(430, 360, 150, 50);
        b2.setForeground(Color.BLACK);
        b2.setFont(new Font("Calibri", Font.PLAIN, 22));
        b2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b2.addActionListener(e -> {
            f1.dispose();
            new Main();
        });

        b3 = new JButton("Logout");
        b3.setBounds(1065, 605, 100, 40);
        b3.setForeground(Color.BLACK);
        b3.setFont(new Font("Calibri", Font.PLAIN, 22));
        b3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b3.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(f1, "Are you sure you want to logout?", "Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                f1.dispose();
                new Login();
            }
        });

        b4 = new JButton("Dashboard");
        b4.setBounds(515, 430, 170, 50);
        b4.setForeground(Color.BLACK);
        b4.setFont(new Font("Calibri", Font.PLAIN, 22));
        b4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        b4.addActionListener(e -> {
            Random rand = new Random();
            int totalSessions = rand.nextInt(50) + 10;
            double avgScore = Math.round((rand.nextDouble() * 5 + 5) * 10.0) / 10.0;
            int positiveFeedback = rand.nextInt(80) + 20;
            int negativeFeedback = rand.nextInt(20);

            String summary = "📊 Mockfinity Session Summary:\n\n"
                    + "🧑 Total Interview Sessions: " + totalSessions + "\n"
                    + "⭐ Average Interview Score: " + avgScore + "/10.0\n"
                    + "👍 Positive Feedbacks: " + positiveFeedback + "\n"
                    + "👎 Negative Feedbacks: " + negativeFeedback + "\n\n"
                    + "Keep practicing and improving your interview skills!";

            JOptionPane.showMessageDialog(f1, summary, "Mockfinity Dashboard Report", JOptionPane.INFORMATION_MESSAGE);
        });


        // Add components
        f1.add(l3);
        f1.add(l1);
        f1.add(l5);
        f1.add(b1);
        f1.add(b2);
        f1.add(b3);
        f1.add(b4);
        f1.add(l2);

        f1.setSize(1200, 700);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
    }

    private void setFieldsEnabled(boolean enabled) {
        b1.setEnabled(enabled);
        b2.setEnabled(enabled);
        b4.setEnabled(enabled);
    }
}
