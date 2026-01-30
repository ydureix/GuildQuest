import javax.swing.*;
import java.awt.*;


class getNameDialog extends JDialog {
    String name;
    getNameDialog(JFrame parent) {

        super(parent, "Update Campaign", true);

        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Submit");

        JTextField name = new JTextField(20);

        submit.addActionListener(e -> {
            this.name = name.getText();
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
        panel.add(new JLabel("Campaign Name"), gbc);
        gbc.gridx = 1;
        panel.add(name, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submit, gbc);
        add(panel);
    }

    String getNewName() {
        return this.name;
    }
}
