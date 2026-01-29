import javax.swing.*;
import java.awt.*;
import java.util.Random;

class AddCampaignDialog extends JDialog {
    private Campaign campaign;

    AddCampaignDialog(JFrame parent, User user) {

        super(parent, "Add Campaign", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Submit");

        JTextField campaignName = new JTextField(20);

        submit.addActionListener(e -> {
            Random rand = new Random();
            int randomNum = rand.nextInt(100000);
            campaign = new Campaign(campaignName.getText(), String.valueOf(randomNum), user);
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
        panel.add(campaignName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submit, gbc);
        add(panel);
    }

    Campaign getCampaign() {
        return campaign;
    }
}
