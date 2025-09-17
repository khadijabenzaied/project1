package Pack.Admin;
import Pack.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminInterface extends JFrame implements ActionListener {
    JButton addcarb = new JButton("Add Car");
    JButton userb = new JButton("Check User");
    JButton checkcarb = new JButton("Check Car");
    JButton gobackb = new JButton("Go Back");

    public AdminInterface() {
        super("Admin Car User Check");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        addcarb.setBounds(100, 60, 200, 50);
        userb.setBounds(100, 140, 200, 50);
        checkcarb.setBounds(100, 220, 200, 50); 
        gobackb.setBounds(10, 325, 90, 30); 
        add(addcarb);
        add(userb);
        add(checkcarb); 
        add(gobackb);

        addcarb.addActionListener(this);
        userb.addActionListener(this);
        checkcarb.addActionListener(this); 
        gobackb.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addcarb) {
            new addCar();
        } else if (e.getSource() == userb) {
            new checkUsers();
        } else if (e.getSource() == checkcarb) {
            new checkCar();
        } else if (e.getSource() == gobackb) {
            new App();
        }
        dispose();
    }
}
