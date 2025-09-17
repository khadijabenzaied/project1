package Pack.User;

import Pack.DatabaseConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static Pack.User.checkCarUser.*;
import static Pack.verification_functions.replace;

import static Pack.User.LoginUser.loginTextField;


public class rentCar extends JFrame implements ActionListener {
    JButton backButton = new JButton("Go Back");
    JButton rentbutton = new JButton("Rent");
    JTable tblData = new JTable();
    JLabel label = new JLabel("Enter Date of return (YYYY-MM-DD): ");
    JTextField dateField = new JTextField(10);
    DefaultTableModel model;

    public rentCar() {
        super(" Rent a car");
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
        rentbutton.setBounds(900, 520, 150, 30);
        rentbutton.addActionListener(this);
        add(rentbutton);
        label.setBounds(350, 520, 250, 30);
        dateField.setBounds(600, 520, 150, 30);
        add(label);
        add(dateField);

        Connection con = DatabaseConnection.getConnection();
        try {
            String brandS=replace(brandField.getText());
            String modelS=replace(modelField.getText());
            String priceS=replace(priceField.getText());
            String colorS=replace(colorField.getText());
            String matS=replace(licenseField1.getText()+licenseField2.getText());
            String ageS=replace(ageField.getText());

            String select="SELECT * FROM Cars where mat LIKE ? and brand LIKE ? and model LIKE ? and price LIKE ? and color LIKE ? and status LIKE 'Available' and age LIKE ?";

            PreparedStatement stmt = con.prepareStatement(select);
            stmt.setString(1, matS);
            stmt.setString(2, brandS);
            stmt.setString(3, modelS);
            stmt.setString(4, priceS);
            stmt.setString(5, colorS);
            stmt.setString(6, ageS);

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
            new checkCarUser();
            dispose();
        }
        else if(e.getSource() == rentbutton){
            String date = dateField.getText().trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date2 = LocalDate.parse(date, formatter);
                    if (date2.isBefore(LocalDate.now())) {
                        JOptionPane.showMessageDialog(this, "Invalid Date! Please use a date after today.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        int row=tblData.getSelectedRow();
                        if(row==-1){
                            JOptionPane.showMessageDialog(this, "Please select a car to rent");
                        }
                        else{
                            String carid = tblData.getValueAt(row, 0).toString();
                            Connection con = DatabaseConnection.getConnection();
                            try {
                                String loginS=loginTextField.getText();
                                LocalDate currentDate = LocalDate.now();
                                Statement stmt = con.createStatement();
                                stmt.executeUpdate("UPDATE Cars SET status='Rented' WHERE mat='"+carid+"'");
                                stmt.executeUpdate("INSERT INTO Rents (loginUser,mat, rentDate,returnDate) VALUES ('"+loginS+"','"+carid+"','"+currentDate+"','"+date+"')");
                                JOptionPane.showMessageDialog(this, "Car rented successfully");
                                dispose();
                                new rentCar();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            dispose();
                        }
                    }
                }catch(DateTimeParseException ex){
                    JOptionPane.showMessageDialog(this,"Invalid Date! Please use the format YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this,"Invalid Date! Please use the format YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}