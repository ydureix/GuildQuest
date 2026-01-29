import javax.swing.*;
import java.awt.*;
import java.util.Random;

class LoginFrame extends JFrame {
    LoginFrame() {

        //Frame setting
        setTitle("Login");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //GUI Variables
        JTextField userName = new JTextField(20);
        JPasswordField password = new JPasswordField(20);
        JButton submit = new JButton("Submit");

        submit.addActionListener(e -> {
            Random rand = new Random();
            int randomNum = rand.nextInt(100000);
            User user = new User(randomNum, userName.getText());
            new UserFrame(user).setVisible(true);

            dispose();
        });

        //GUI
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(userName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(submit, gbc);
        add(panel);

    }
}
