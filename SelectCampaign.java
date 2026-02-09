import javax.swing.*;
import java.awt.*;

class SelectCampaign extends JDialog {
    private Campaign campaign;

    SelectCampaign(JFrame parent, JList<Campaign> campaignList) {

        super(parent, "Select Campaign", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Select");

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
            return campaign.getCampaignID();
        } else {
            return null;
        }
    }
}
