package prot;

import javax.swing.*;
import java.awt.*;

public class Loading1 {
    private JFrame f1 = new JFrame("Mockfinity VR:");
    private JProgressBar progressBar;
    private JLabel loadingLabel;
    private JLabel limg;

    public Loading1() {
        f1.setSize(600, 400);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setUndecorated(true); 

        JPanel content = new JPanel();
        content.setBackground(Color.LIGHT_GRAY);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        // Logo
        ImageIcon i1 = new ImageIcon("C:\\Users\\Mhacee Caryl\\Desktop\\bubuy\\j1.png");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        limg = new JLabel(new ImageIcon(i2));
        limg.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        JLabel title = new JLabel("Mockfinity VR: Ai-Powered Job Interview Trainer");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(54, 33, 89));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Loading text
        loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loadingLabel.setForeground(Color.DARK_GRAY);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setForeground(new Color(54, 33, 89));
        progressBar.setBackground(Color.LIGHT_GRAY);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(300, 25));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add to panel
        content.add(limg);
        content.add(Box.createVerticalStrut(10));
        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(loadingLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(progressBar);

        f1.add(content);
        f1.setVisible(true);

        new Thread(new Task()).start();
    }

    private class Task implements Runnable {
        public void run() {
            for (int i = 0; i <= 100; i++) {
                final int currentProgress = i;
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(currentProgress);
                    loadingLabel.setText("Loading" + ".".repeat((currentProgress / 10) % 4)); 
                });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            SwingUtilities.invokeLater(() -> {
                f1.dispose();
                new Register(); 
            });
        }
    }
}
