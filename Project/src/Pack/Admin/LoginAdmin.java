package Pack.Admin;
import Pack.App;
import Pack.DatabaseConnection;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import static Pack.verification_functions.*;

public class LoginAdmin extends JFrame implements ActionListener {
    JLabel login =new JLabel("login") ;
    JLabel password =new JLabel("Password");
    static JTextField loginTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();
    JButton loginb = new JButton("login");
    JButton gobackb = new JButton("goback");


    public LoginAdmin () throws SQLException, ClassNotFoundException {
        super("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        login.setBounds(50, 50, 100, 30);
        login.setFont(new  Font("Arial", Font.BOLD, 14) );
        add(login);

        loginTextField.setBounds(160, 50, 180, 30);
        loginTextField.setFont(new  Font("Arial", Font.BOLD, 14) );
        add(loginTextField);
        
        password.setBounds(50, 100, 100, 30);
        password.setFont(new  Font("Arial", Font.BOLD, 14) );
        add(password);

        passwordTextField.setBounds(160, 100, 180, 30);
        passwordTextField.setFont(new  Font("Arial", Font.BOLD, 14) );
        add(passwordTextField);
        loginb.setBounds(280, 175, 90, 30);
        gobackb.setBounds(10, 175, 90, 30);
        loginb.addActionListener(this);
        gobackb.addActionListener(this);
        add(loginb);
        add(gobackb);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==loginb){
            String loginS = loginTextField.getText();
            String pwdS = passwordTextField.getText();
            if((!veriflogin(loginS)) || (!verifPassword(pwdS))){
                JOptionPane.showMessageDialog(this, "Invalid login or password");
                loginTextField.setText("");
                passwordTextField.setText("");
                loginTextField.requestFocus();
            }
            else{
                try {
                    Connection con = DatabaseConnection.getConnection();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT login,password FROM Users where role='Administrator'");
                    boolean enter = false;
                    while (rs.next() && !enter) {
                        String login = rs.getString("login");
                        String password = rs.getString("password");
                        if(login.equals(loginS) && password.equals(pwdS)){
                            JOptionPane.showMessageDialog(this, "Welcome Admin");
                            enter = true;
                            new AdminInterface();
                            dispose();
                        }
                    }
                    if(!enter){
                        JOptionPane.showMessageDialog(this, "Admin doesnt exist");
                        loginTextField.setText("");
                        passwordTextField.setText("");
                        loginTextField.requestFocus();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(e.getSource()==gobackb){
            new App();
            dispose();
        }
    }


}