/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
/**
 *
 * @author Harvey
 */
public class ViewActivityLogs {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public ViewActivityLogs() {
        frame = new JFrame("Mockfinity VR: User Activity Logs");
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Background
        ImageIcon bgIcon = new ImageIcon("Images//fi2.gif");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 700);
        frame.setContentPane(background);
        background.setLayout(null);

        // Title
        JLabel title = new JLabel("User Activity Logs");
        title.setFont(new Font("Times New Roman", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBounds(380, 40, 600, 60);
        background.add(title);

        // Table (excluding IP address)
        model = new DefaultTableModel(new Object[]{"Username", "Login Time", "Status"}, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 18));
        table.setRowHeight(28);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 130, 1000, 400);
        background.add(scrollPane);

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Calibri", Font.PLAIN, 22));
        backBtn.setBounds(550, 560, 150, 45);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backBtn.addActionListener(e -> {
            frame.dispose();
            new RealAdmin();
        });
        background.add(backBtn);


        loadActivityLogs();

        frame.setVisible(true);
    }

    private void loadActivityLogs() {
        String url = "jdbc:mysql://localhost:3306/db_cite";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT username, login_time, status FROM user_activity")) {

            while (rs.next()) {
                String name = rs.getString("username");
                String loginTime = rs.getString("login_time");
                String status = rs.getString("status");

                model.addRow(new Object[]{name, loginTime, status});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error loading logs:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}