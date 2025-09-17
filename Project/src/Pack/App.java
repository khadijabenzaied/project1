package Pack ;
import Pack.Admin.LoginAdmin;
import Pack.User.LoginUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class App extends JFrame implements ActionListener {
    JButton adminb = new JButton ("ADMIN") ;
    JButton userb = new JButton ("USER") ;
    public App(){
        super("Cars Retail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300) ;
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        adminb.setBounds(100, 60, 200, 60);
        adminb.addActionListener(this);
        userb.setBounds(100, 140, 200, 60);
        userb.addActionListener(this);
        add(adminb);
        add(userb);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            LocalDate currentDate = LocalDate.now();
            int rs =stmt.executeUpdate("UPDATE cars SET status = 'Available' WHERE mat IN (SELECT mat FROM rents WHERE returnDate <= '"+currentDate+"')");
            Statement stmt2 = con.createStatement();
            int rs2 =stmt2.executeUpdate("DELETE FROM rents WHERE returnDate <= '"+currentDate+"'");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(e.getSource()==adminb){
            try {
                new LoginAdmin() ;
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource()==userb){
            new LoginUser() ;
        }
        dispose();
    }

}
