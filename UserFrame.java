import javax.swing.*;
import java.awt.*;



class UserFrame extends JFrame {
    private User user;

    String getCampaignID(){
        DefaultListModel<Campaign> campaignModel = new DefaultListModel<>();
        JList<Campaign> campaignList = new JList<>(campaignModel);
        for (Campaign c : user.getCampaigns()) {
            campaignModel.addElement(c);
        }
        campaignList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectCampaign dialog = new SelectCampaign(this, campaignList);
        dialog.setVisible(true);
        return dialog.getCampaignID();
    }

    String getCharacterID(){
        DefaultListModel<Character> characterModel = new DefaultListModel<>();
        JList<Character> characterList = new JList<>(characterModel);
        for (Character c : user.getCharacters()) {
            characterModel.addElement(c);
        }
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectCharacter dialog = new SelectCharacter(this, characterList);
        dialog.setVisible(true);
        return dialog.getCharacterID();
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
        JButton createCharacter = new JButton("Create Character");
        JButton viewCharacter = new JButton("View Characters");

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
            Campaign found = user.getCampaigns().stream()
                    .filter(c -> c.getCampaignID().equals(campaignID))
                    .findFirst()
                    .orElse(null);
            if (found != null){
                new CampaignFrame(found).setVisible(true);
            }
        });

        //Create Character Dialog
        createCharacter.addActionListener(e -> {
            CreateCharacterDialog dialog = new CreateCharacterDialog(this, user);
            dialog.setVisible(true);
            Character character = dialog.getCharacter();
            if (character != null){
                user.addCharacter(character);
            }
            System.out.println(user.getCharacters());
        });

        //View Character
        viewCharacter.addActionListener(e -> {
            String characterID = getCharacterID();
            Character found = user.getCharacters().stream()
                    .filter(c -> c.getCharacterID().equals(characterID))
                    .findFirst()
                    .orElse(null);
            if (found != null) {
                new CharacterFrame(found).setVisible(true);
            }
        });

        //Panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6); // spacing
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
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(new JLabel("Create Character"), gbc);
        gbc.gridx = 1;
        panel.add(createCharacter, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(new JLabel("View Characters"), gbc);
        gbc.gridx = 1;
        panel.add(viewCharacter, gbc);
        add(panel);
    }
}
