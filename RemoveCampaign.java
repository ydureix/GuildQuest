import javax.swing.*;
import java.awt.*;

class RemoveCampaign extends JDialog {
    private Campaign campaign;

    RemoveCampaign(JFrame parent, JList<Campaign> campaignList) {

        super(parent, "Remove Campaign", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Remove");

        campaignList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Campaign selected = campaignList.getSelectedValue();
                if (selected != null) {
                    campaign = selected;
                }
            }
        });

        submit.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(campaignList), BorderLayout.CENTER);
        panel.add(submit);
        add(panel);

    }

    String getCampaignID() {
        if (campaign != null) {
            return campaign.campaignID;
        } else {
            return null;
        }
    }
}
