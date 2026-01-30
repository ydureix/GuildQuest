import javax.swing.*;
import java.awt.*;



class UserFrame extends JFrame {
    private User user;

    String getCampaignID(){
        DefaultListModel<Campaign> campaignModel = new DefaultListModel<>();
        JList<Campaign> campaignList = new JList<>(campaignModel);
        for (Campaign c : user.campaigns) {
            campaignModel.addElement(c);
        }
        campaignList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectCampaign dialog = new SelectCampaign(this, campaignList);
        dialog.setVisible(true);
        return dialog.getCampaignID();
    }

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
        JButton viewCampaign = new JButton("View Campaign");

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
            String campaignID = getCampaignID();
            if (campaignID != null) {
                user.removeCampaign(campaignID);
            }

        });

        //Update Campaign Dialog
        updateCampaign.addActionListener(e -> {
            String campaignID = getCampaignID();
            getNameDialog dialog = new getNameDialog(this);
            dialog.setVisible(true);
            String changedName = dialog.getNewName();
            if (changedName != null && campaignID != null) {
                user.updateCampaign(campaignID, changedName);
            }
        });

        //Select Campaign Frame
        viewCampaign.addActionListener(e ->{
            String campaignID = getCampaignID();
            Campaign found = user.campaigns.stream()
                    .filter(c -> c.campaignID.equals(campaignID))
                    .findFirst()
                    .orElse(null);
            if (found != null){
                new CampaignFrame(found).setVisible(true);
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
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Update Campaign"), gbc);
        gbc.gridx = 1;
        panel.add(updateCampaign, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("View Campaign"), gbc);
        gbc.gridx = 1;
        panel.add(viewCampaign, gbc);
        add(panel);
    }
}
