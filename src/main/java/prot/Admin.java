package prot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author Harvey
 */
public class Admin {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private final String DB_URL = "jdbc:mysql://localhost:3306/db_cite";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    public Admin() {
        frame = new JFrame("Admin Panel - Manage Questionnaire");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // === Background ===
        ImageIcon bgIcon = new ImageIcon("Images//qa.jpg");
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1300, 600);
        frame.setContentPane(background);
        background.setLayout(null);

        JLabel title = new JLabel("Admin Panel – Manage Questionnaire");
        title.setFont(new Font("Georgia", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(300, 30, 800, 50);
        background.add(title);

        // === Table ===
        tableModel = new DefaultTableModel(new Object[]{"Numbers", "Questions"}, 0);
        table = new JTable(tableModel);
        
        table.setFont(new Font("SansSerif", Font.PLAIN, 20));
        table.setRowHeight(28);
        
       
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 100, 900, 400);
        background.add(scrollPane);

        // === Buttons ===
        JButton addBtn = createButton("Add Question");
        JButton editBtn = createButton("Edit Selected");
        JButton deleteBtn = createButton("Delete Selected");
        JButton clearBtn = createButton("Clear All");
        JButton backBtn = createButton("Back");

        addBtn.setBounds(150, 520, 180, 45);
        editBtn.setBounds(360, 520, 180, 45);
        deleteBtn.setBounds(570, 520, 180, 45);
        clearBtn.setBounds(780, 520, 180, 45);
        backBtn.setBounds(1000, 620, 100, 35);

        background.add(addBtn);
        background.add(editBtn);
        background.add(deleteBtn);
        background.add(clearBtn);
        background.add(backBtn);

        // === Button Actions ===
        addBtn.addActionListener(e -> addQuestion());
        editBtn.addActionListener(e -> editQuestion());
        deleteBtn.addActionListener(e -> deleteQuestion());
        clearBtn.addActionListener(e -> clearQuestions());
        backBtn.addActionListener(e -> {
            frame.dispose();
            new RealAdmin();
        });

        loadQuestions();
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return btn;
    }

    private void loadQuestions() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM questions")) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("question"));
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading questions from database.");
        }
    }

    private void addQuestion() {
        String q = JOptionPane.showInputDialog(frame, "Enter new question:");
        if (q != null && !q.trim().isEmpty()) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement pst = conn.prepareStatement("INSERT INTO questions (question) VALUES (?)")) {
                pst.setString(1, q.trim());
                pst.executeUpdate();
                loadQuestions();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding question.");
            }
        }
    }

    private void editQuestion() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            int id = (int) tableModel.getValueAt(selected, 0);
            String currentQ = (String) tableModel.getValueAt(selected, 1);
            String updated = JOptionPane.showInputDialog(frame, "Edit question:", currentQ);
            if (updated != null && !updated.trim().isEmpty()) {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     PreparedStatement pst = conn.prepareStatement("UPDATE questions SET question=? WHERE id=?")) {
                    pst.setString(1, updated.trim());
                    pst.setInt(2, id);
                    pst.executeUpdate();
                    loadQuestions();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error updating question.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a question to edit.");
        }
    }

    private void deleteQuestion() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            int id = (int) tableModel.getValueAt(selected, 0);
            int confirm = JOptionPane.showConfirmDialog(frame, "Delete selected question?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     PreparedStatement pst = conn.prepareStatement("DELETE FROM questions WHERE id=?")) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                    loadQuestions();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error deleting question.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a question to delete.");
        }
    }

    private void clearQuestions() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Clear all questions?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM questions");
                loadQuestions();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error clearing questions.");
            }
        }
    }
}