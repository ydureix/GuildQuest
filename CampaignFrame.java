import javax.swing.*;
import java.awt.*;



class CampaignFrame extends JFrame {
    private Campaign campaign;

    String getEventID(){
        DefaultListModel<QuestEvent> eventModel = new DefaultListModel<>();
        JList<QuestEvent> eventList = new JList<>(eventModel);
        for (QuestEvent c : campaign.questEvents) {
            eventModel.addElement(c);
        }
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectEvent dialog = new SelectEvent(this, eventList);
        dialog.setVisible(true);
        return dialog.getQuestEventID();
    }

    CampaignFrame(Campaign campaign) {
        //Frame setting
        this.campaign = campaign;
        setTitle("GuildQuest");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addEvent = new JButton("Add Event");
        JButton deleteEvent = new JButton("Delete Event");
        JButton updateEvent = new JButton("Update Event");

        //Add Event Dialog
        addEvent.addActionListener(e -> {
            AddEventDialog dialog = new AddEventDialog(this, this.campaign);
            dialog.setVisible(true);
            QuestEvent event= dialog.getQuestEvent();
            if (event != null) {
                campaign.addQuestEvent(event);
            }
        });

        //Delete Campaign Dialog
//        deleteEvent.addActionListener(e -> {
//            String campaignID = getCampaignID();
//            if (campaignID != null) {
//                campaign.removeCampaign(campaignID);
//            }
//
//        });

        //Update Campaign Dialog
//        updateEvent.addActionListener(e -> {
//            String campaignID = getCampaignID();
//            UpdateCampaignDialog dialog = new UpdateCampaignDialog(this);
//            dialog.setVisible(true);
//            String changedName = dialog.getCampaignName();
//            if (changedName != null && campaignID != null) {
//                campaign.updateCampaign(campaignID, changedName);
//            }
//        });



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
        panel.add(addEvent, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
//        panel.add(new JLabel("Delete Campaign"), gbc);
//        gbc.gridx = 1;
//        panel.add(deleteCampaign, gbc);
//        gbc.gridy = 2;
//        gbc.gridx = 0;
//        panel.add(new JLabel("Update Campaign"), gbc);
//        gbc.gridx = 1;
//        panel.add(updateCampaign, gbc);
        add(panel);
    }
}
