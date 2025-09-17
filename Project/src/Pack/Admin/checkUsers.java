package Pack.Admin;

import Pack.DatabaseConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class checkUsers extends JFrame implements ActionListener {
    JButton backButton = new JButton("Go Back");
    JTable tblData = new JTable();
    DefaultTableModel model;

    public checkUsers() {
        super("Check User");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        JScrollPane sp = new JScrollPane(tblData);
        sp.setBounds(50, 50, 1000, 450);
        add(sp);

        backButton.setBounds(50, 520, 150, 30);
        backButton.addActionListener(this);
        add(backButton);

        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            ResultSetMetaData rsmd = rs.getMetaData();
            model = (DefaultTableModel) tblData.getModel();
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 1; i <= cols; i++) {
                colName[i - 1] = rsmd.getColumnName(i);
            }
            model.setColumnIdentifiers(colName);
            while (rs.next()) {
                Object[] rowData = new Object[cols];
                for (int i = 1; i <= cols; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new AdminInterface();
            dispose();
        }
    }
}