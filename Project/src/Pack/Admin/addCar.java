package Pack.Admin;

import Pack.DatabaseConnection;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import static Pack.verification_functions.isNumber;
import static Pack.verification_functions.verifyLicense;

public class addCar extends JFrame implements ActionListener {
    JLabel brand = new JLabel("Brand");
    JLabel model = new JLabel("Model");
    JLabel price = new JLabel("Price");
    JLabel status = new JLabel("Status");
    JLabel color = new JLabel("Color");
    JLabel license = new JLabel("License Plate");
    JLabel age = new JLabel("Age");
    JLabel tunis = new JLabel("تونس");
    JLabel dt = new JLabel("DT");
    JLabel ans = new JLabel("ANS");

    JTextField brandField = new JTextField(20);
    JTextField modelField = new JTextField(20);
    JTextField priceField = new JTextField(20);
    JTextField statusField = new JTextField(20);
    JTextField colorField = new JTextField(20);
    JTextField licenseField1 = new JTextField(20);
    JTextField licenseField2 = new JTextField(20);
    JTextField ageField = new JTextField(20);
    JButton addCarButton = new JButton("Add Car");
    JButton goBackButton = new JButton("Go Back");

    public addCar() {
        super("Add Car");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        brand.setBounds(50, 30, 100, 30);
        brand.setFont(new Font("Arial", Font.BOLD, 14));
        add(brand);

        brandField.setBounds(180, 30, 210, 30);
        brandField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(brandField);

        model.setBounds(50, 80, 100, 30);
        model.setFont(new Font("Arial", Font.BOLD, 14));
        add(model);

        modelField.setBounds(180, 80, 210, 30);
        modelField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(modelField);

        price.setBounds(50, 130, 100, 30);
        price.setFont(new Font("Arial", Font.BOLD, 14));
        add(price);

        priceField.setBounds(180, 130, 180, 30);
        priceField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(priceField);

        dt.setBounds(370, 130, 20, 30);
        dt.setFont(new Font("Arial", Font.BOLD, 14));
        add(dt);


        status.setBounds(50, 180, 100, 30);
        status.setFont(new Font("Arial", Font.BOLD, 14));
        add(status);

        statusField.setBounds(180, 180, 210, 30);
        statusField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(statusField);

        color.setBounds(50, 230, 100, 30);
        color.setFont(new Font("Arial", Font.BOLD, 14));
        add(color);

        colorField.setBounds(180, 230, 210, 30);
        colorField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(colorField);

        license.setBounds(50, 280, 120, 30);
        license.setFont(new Font("Arial", Font.BOLD, 14));
        add(license);

        licenseField1.setBounds(180, 280, 80, 30);
        licenseField1.setFont(new Font("Arial", Font.PLAIN, 14));
        add(licenseField1);

        tunis.setBounds(270, 280, 60, 30);
        tunis.setFont(new Font("Arial", Font.BOLD, 14));
        add(tunis);

        licenseField2.setBounds(310, 280, 80, 30);
        licenseField2.setFont(new Font("Arial", Font.PLAIN, 14));
        add(licenseField2);

        age.setBounds(50, 330, 100, 30);
        age.setFont(new Font("Arial", Font.BOLD, 14));
        add(age);

        ageField.setBounds(180, 330, 175, 30);
        ageField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(ageField);

        ans.setBounds(360, 330, 30, 30);
        ans.setFont(new Font("Arial", Font.BOLD, 14));
        add(ans);

        addCarButton.setBounds(370, 400, 100, 30);
        goBackButton.setBounds(20, 400, 100, 30);

        addCarButton.addActionListener(this);
        goBackButton.addActionListener(this);

        add(addCarButton);
        add(goBackButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addCarButton) {
        String brandS = brandField.getText();
        String modelS = modelField.getText();
        String priceS = priceField.getText();
        String statusS = statusField.getText();
        String colorS = colorField.getText();
        String license1S = licenseField1.getText();
        String license2S = licenseField2.getText();
        String ageS = ageField.getText();

        if (brandS.isEmpty() || modelS.isEmpty() || priceS.isEmpty() || statusS.isEmpty() || colorS.isEmpty() || license1S.isEmpty() || license2S.isEmpty() || ageS.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Check your information, please.");
        } else if (!verifyLicense(license1S, license2S)) {
            licenseField1.setText("");
            licenseField2.setText("");
            JOptionPane.showMessageDialog(this, "Check license plate, please.");
        } else if (!isNumber(priceS)) {
            priceField.setText("");
            JOptionPane.showMessageDialog(this, "Check your price, please.");
        } else if (!isNumber(ageS)) {
            ageField.setText("");
            JOptionPane.showMessageDialog(this, "Check your age, please.");
        } else {
            try {
                Connection con = DatabaseConnection.getConnection();
                Statement stmt = con.createStatement();
                int rows = stmt.executeUpdate("INSERT INTO Cars (brand, model, price, status, color, mat, age) VALUES ('"
                    + brandS + "','" + modelS + "','" + priceS + "','" + statusS + "','" + colorS + "','" + license1S + license2S + "','" + ageS + "')");
                JOptionPane.showMessageDialog(this, "Car added successfully.");
                brandField.setText("");
                modelField.setText("");
                priceField.setText("");
                statusField.setText("");
                colorField.setText("");
                licenseField1.setText("");
                licenseField2.setText("");
                ageField.setText("");
            } catch ( SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    } else if (e.getSource() == goBackButton) {
        new AdminInterface();
        dispose();
        }
    }


}
