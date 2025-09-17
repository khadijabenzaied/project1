package Pack.User;


import Pack.DatabaseConnection;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import static Pack.verification_functions.*;

public class UserSignup extends JFrame implements ActionListener {

    JLabel name = new JLabel("Name");
    JLabel surname = new JLabel("Surname");
    JLabel country = new JLabel("Country");
    JLabel phone = new JLabel("Phone Number");
    JLabel email = new JLabel("Email");
    JLabel login = new JLabel("Login");
    JLabel password = new JLabel("Password");
    JLabel verifyPassword = new JLabel("Verify Password");
    JLabel robotCheck = new JLabel("I am not a robot");
    JLabel gender = new JLabel("Gender");

    JTextField nameField = new JTextField(20);
    JTextField surnameField = new JTextField(20);
    JTextField countryField = new JTextField(20);
    JTextField phoneField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField loginField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JPasswordField verifyPasswordField = new JPasswordField(20);
    JCheckBox robotCheckBox = new JCheckBox();
    JRadioButton maleRadio = new JRadioButton("Male");
    JRadioButton femaleRadio = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();
    JButton signupb = new JButton("Sign Up");
    JButton gobackb = new JButton("Go Back");

    public UserSignup() {
        super("User Sign Up");
        setSize(500, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        name.setBounds(50, 30, 100, 30);
        name.setFont(new Font("Arial", Font.BOLD, 14));
        add(name);

        nameField.setBounds(180, 30, 200, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(nameField);

        surname.setBounds(50, 80, 100, 30);
        surname.setFont(new Font("Arial", Font.BOLD, 14));
        add(surname);

        surnameField.setBounds(180, 80, 200, 30);
        surnameField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(surnameField);

        country.setBounds(50, 130, 100, 30);
        country.setFont(new Font("Arial", Font.BOLD, 14));
        add(country);

        countryField.setBounds(180, 130, 200, 30);
        countryField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(countryField);

        phone.setBounds(50, 180, 120, 30);
        phone.setFont(new Font("Arial", Font.BOLD, 14));
        add(phone);

        phoneField.setBounds(180, 180, 200, 30);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(phoneField);

        email.setBounds(50, 230, 100, 30);
        email.setFont(new Font("Arial", Font.BOLD, 14));
        add(email);

        emailField.setBounds(180, 230, 200, 30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(emailField);

        login.setBounds(50, 280, 100, 30);
        login.setFont(new Font("Arial", Font.BOLD, 14));
        add(login);

        loginField.setBounds(180, 280, 200, 30);
        loginField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(loginField);

        password.setBounds(50, 330, 100, 30);
        password.setFont(new Font("Arial", Font.BOLD, 14));
        add(password);

        passwordField.setBounds(180, 330, 200, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(passwordField);

        verifyPassword.setBounds(50, 380, 150, 30);
        verifyPassword.setFont(new Font("Arial", Font.BOLD, 14));
        add(verifyPassword);

        verifyPasswordField.setBounds(180, 380, 200, 30);
        verifyPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(verifyPasswordField);

        gender.setBounds(50, 430, 100, 30);
        gender.setFont(new Font("Arial", Font.BOLD, 14));
        add(gender);

        maleRadio.setBounds(160, 430, 80, 30);
        maleRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        genderGroup.add(maleRadio);
        add(maleRadio);

        femaleRadio.setBounds(250, 430, 80, 30);
        femaleRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        genderGroup.add(femaleRadio);
        add(femaleRadio);

        robotCheck.setBounds(50, 480, 200, 30);
        robotCheck.setFont(new Font("Arial", Font.PLAIN, 14));
        add(robotCheck);

        robotCheckBox.setBounds(180, 480, 20, 20);
        add(robotCheckBox);

        signupb.setBounds(320, 530, 100, 30);
        gobackb.setBounds(80, 530, 100, 30);
        signupb.addActionListener(this);
        gobackb.addActionListener(this);

        add(signupb);
        add(gobackb);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==signupb){
            String emailS = emailField.getText();
            String loginS = loginField.getText();
            String pwdS = passwordField.getText();
            String verifyPwdS = verifyPasswordField.getText();
            String nameS = nameField.getText();
            String surnameS = surnameField.getText();
            String telS = phoneField.getText();
            String g="M";
            if (femaleRadio.isSelected()){
                g="F";
            }
            else {
                g="M";
            }
            if (!verifEmail(emailS) || !verifPassword(pwdS) || !(pwdS.equals(verifyPwdS)) || loginS.isEmpty() || nameS.isEmpty() || surnameS.isEmpty() || !isNumber(telS) || telS.length() != 8 || !veriflogin(loginS)) {
                JOptionPane.showMessageDialog(this, "Check your informations please.");
                robotCheckBox.setSelected(false);
            }
            else if(!maleRadio.isSelected() && !femaleRadio.isSelected()){
                JOptionPane.showMessageDialog(this, "Select the gender");
            }
            else if(!robotCheckBox.isSelected()){
                JOptionPane.showMessageDialog(this, "Check the robot box please.");
            }
            else{
                try {
                    Connection con = DatabaseConnection.getConnection();
                    Statement stmt = con.createStatement();
                    int rs=stmt.executeUpdate("INSERT INTO Users (login,name,surname,email,password,phone,country,sex,role) " +
                                                "VALUES ('"+loginS+"',,'"+nameS+"','"+surnameS+"','"+emailS+"','"+pwdS+"','"+telS+"','Tunisia','"+g+"','Client')");
                } catch (SQLException  ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "Sign up successful");
                dispose();
                new LoginUser();
            }
        }
        else if(e.getSource()==gobackb){
            new LoginUser() ;
            dispose();
        }

    }
}
