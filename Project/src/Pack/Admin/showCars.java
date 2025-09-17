package Pack.Admin;

import Pack.DatabaseConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static Pack.Admin.checkCar.*;
import static Pack.verification_functions.replace;


public class showCars extends JFrame implements ActionListener {
    JButton backButton = new JButton("Go Back");
    JTable tblData = new JTable();
    DefaultTableModel model;

    public showCars() {
        super("Cars");
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
            String brandS=replace(brandField.getText());
            String modelS=replace(modelField.getText());
            String priceS=replace(priceField.getText());
            String statusS=replace(statusField.getText());
            String colorS=replace(colorField.getText());
            String matS=replace(licenseField1.getText()+licenseField2.getText());
            String ageS=replace(ageField.getText());

            String select="SELECT * FROM Cars where mat LIKE ? and brand LIKE ? and model LIKE ? and price LIKE ? and color LIKE ? and status LIKE ? and age LIKE ?";

            PreparedStatement stmt = con.prepareStatement(select);
            stmt.setString(1, matS);
            stmt.setString(2, brandS);
            stmt.setString(3, modelS);
            stmt.setString(4, priceS);
            stmt.setString(5, colorS);
            stmt.setString(6, statusS);
            stmt.setString(7, ageS);

            ResultSet rs = stmt.executeQuery();
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
            new checkCar();
            dispose();
        }
    }
}