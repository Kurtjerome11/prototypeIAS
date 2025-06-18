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
public class ViewRegisteredUser {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public ViewRegisteredUser() {
        frame = new JFrame("Mockfinity VR: Registered Users");
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
        JLabel title = new JLabel("Registered Users");
        title.setFont(new Font("Times New Roman", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBounds(400, 40, 500, 60);
        background.add(title);

        // Table
        model = new DefaultTableModel(new Object[]{"Users"}, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 20));
        table.setRowHeight(28);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 130, 900, 400);
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


        // Load data
        loadUserData();

        frame.setVisible(true);
    }

    private void loadUserData() {
        String url = "jdbc:mysql://localhost:3306/db_cite";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM login")) {

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("name")});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error loading users:\n" + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
