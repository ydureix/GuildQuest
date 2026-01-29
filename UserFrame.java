import javax.swing.*;
import java.awt.*;

class UserFrame extends JFrame {
    private User user;

    UserFrame(User user) {
        //Frame setting
        this.user = user;
        setTitle("GuildQuest");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addCampaign = new JButton("Add Campaign");
        JButton deleteCampaign = new JButton("Delete Campaign");
        JButton updateCampaign = new JButton("Update Campaign");

        //Add Campaign Dialog
        addCampaign.addActionListener(e -> {
            AddCampaignDialog dialog = new AddCampaignDialog(this, this.user);
            dialog.setVisible(true);
            Campaign campaign = dialog.getCampaign();
            if (campaign != null) {
                user.addCampaign(campaign);
            }
        });

        //Delete Campaign Dialog
        deleteCampaign.addActionListener(e -> {
            System.out.println(user.campaigns);
            DefaultListModel<Campaign> campaignModel = new DefaultListModel<>();
            JList<Campaign> campaignList = new JList<>(campaignModel);
            for (Campaign c : user.campaigns) {
                campaignModel.addElement(c);
            }
            campaignList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            RemoveCampaign dialog = new RemoveCampaign(this, campaignList);
            dialog.setVisible(true);
            String campaignID = dialog.getCampaignID();
            if (campaignID != null) {
                user.removeCampaign(campaignID);
            }

        });


        //Panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Add Campaign"), gbc);
        gbc.gridx = 1;
        panel.add(addCampaign, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Delete Campaign"), gbc);
        gbc.gridx = 1;
        panel.add(deleteCampaign, gbc);
        add(panel);
    }
}
