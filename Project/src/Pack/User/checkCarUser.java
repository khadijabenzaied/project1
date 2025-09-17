package Pack.User;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checkCarUser extends JFrame implements ActionListener {
    JLabel brand = new JLabel("Brand");
    JLabel model = new JLabel("Model");
    JLabel price = new JLabel("Price");
    JLabel color = new JLabel("Color");
    JLabel license = new JLabel("License Plate");
    JLabel age = new JLabel("Age");
    JLabel tunis = new JLabel("تونس");
    JLabel dt = new JLabel("DT");
    JLabel ans = new JLabel("ANS");

    static JTextField brandField = new JTextField(20);
    static JTextField modelField = new JTextField(20);
    static JTextField priceField = new JTextField(20);
    static JTextField colorField = new JTextField(20);
    static JTextField licenseField1 = new JTextField(20);
    static JTextField licenseField2 = new JTextField(20);
    static JTextField ageField = new JTextField(20);
    JButton checkCarButton = new JButton("Check Car");
    JButton goBackButton = new JButton("Go Back");

    public checkCarUser() {
        super("Check Car");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setIconImage(new ImageIcon("src/logo.png").getImage());

        brand.setBounds(50, 30, 100, 30);
        brandField.setBounds(180, 30, 210, 30);
        add(brand);
        add(brandField);

        model.setBounds(50, 80, 100, 30);
        modelField.setBounds(180, 80, 210, 30);
        add(model);
        add(modelField);

        price.setBounds(50, 130, 100, 30);
        priceField.setBounds(180, 130, 180, 30);
        dt.setBounds(370, 130, 30, 30);
        add(price);
        add(priceField);
        add(dt);

        color.setBounds(50, 180, 100, 30);
        colorField.setBounds(180, 180, 210, 30);
        add(color);
        add(colorField);

        license.setBounds(50, 230, 120, 30);
        licenseField1.setBounds(180, 230, 80, 30);
        tunis.setBounds(270, 230, 60, 30);
        licenseField2.setBounds(310, 230, 80, 30);
        add(license);
        add(licenseField1);
        add(tunis);
        add(licenseField2);

        age.setBounds(50, 280, 100, 30);
        ageField.setBounds(180, 280, 175, 30);
        ans.setBounds(360, 280, 30, 30);
        add(age);
        add(ageField);
        add(ans);

        checkCarButton.setBounds(370, 320, 100, 30);
        goBackButton.setBounds(20, 320, 100, 30);
        checkCarButton.addActionListener(this);
        goBackButton.addActionListener(this);
        add(checkCarButton);
        add(goBackButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkCarButton) {
            new rentCar();
            dispose();
        } else if (e.getSource() == goBackButton) {
            new userInteface();
            dispose();
        }
    }

}
