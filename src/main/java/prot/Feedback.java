package prot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.swing.Timer;
import org.json.JSONArray;
import org.json.JSONObject;

public class Feedback {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton backBtn;
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_cite";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    public Feedback () {
        frame = new JFrame("Admin Panel - Manage Feedback");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // === Background ===
        ImageIcon bgIcon = new ImageIcon("Images//qa.jpg");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        frame.setContentPane(background);
        background.setLayout(null);
        
        // Title
        JLabel title = new JLabel("Admin Panel – Manage Feedback");
        title.setFont(new Font("Georgia", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(300, 30, 800, 50);
        background.add(title);
        
        // Table for user feedback
        String[] columns = {"Timestamp", "Feedback"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 100, 900, 400);
        background.add(scrollPane);
        
        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
        backBtn.setBounds(550, 560, 150, 45);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        background.add(backBtn);
        backBtn.addActionListener(e -> {
            frame.dispose();
            new RealAdmin();
        });
        
        // for feddback's auto-refresh feature
        Timer timer = new Timer(5000, e -> fetchFeedback());
        timer.start();
        fetchFeedback();
        
        
    }
    
    private void fetchFeedback() {
    try {
        URL url = new URL("http://localhost/mockfinityfeedback/fetch.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        JSONArray arr = new JSONArray(response.toString());
        tableModel.setRowCount(0);

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String time = obj.getString("time");
            String text = obj.getString("text");
            tableModel.addRow(new Object[]{time, text});
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
}
