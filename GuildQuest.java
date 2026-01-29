import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Random;

class Inventory{
    String inventoryID;
    Character character;
    Inventory(String inventoryID, Character character){
        this.inventoryID = inventoryID;
        this.character = character;
    }
}



class Character{
    String characterID;
    String name;
    String characterClass;
    int level;
    Inventory inventory;
    String description;

    Character(String characterID, String name, String characterClass, int level){
        this.characterID = characterID;
        this.name = name;
        this.characterClass = characterClass;
        this.level = level;
    }
}

class WorldClock{
    int days;
    int hours;
    int minutes;

    WorldClock(int days, int hours, int minutes){
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }
}

class Realm{
    String realmID;
    String name;
    String description;
    String mapId;
    LocalTimeRule localTimeRule;

    Realm(String realmID, String name){
        this.realmID = realmID;
        this.name = name;
    }
}

class LocalTimeRule{
    int offsetDays;
    int offsetHours;
    int offsetMinutes;

    LocalTimeRule(int offsetDays, int offsetHours, int offsetMinutes){
        this.offsetDays = offsetDays;
        this.offsetHours = offsetHours;
        this.offsetMinutes = offsetMinutes;
    }
}

class QuestEvent{
    String eventID;
    WorldClock startTime;
    WorldClock endTime;
    String name;
    Realm realm;
    String description;
    QuestEvent(String eventID, String name, WorldClock startTime, Realm realm){
        this.eventID = eventID;
        this.name = name;
        this.startTime = startTime;
        this.realm = realm;
    }
}


class Campaign {
    String campaignID;
    String name;
    User host;
    ArrayList<QuestEvent> questEvents;
    WorldClock createdAt;

    Campaign(String campaignID, String name, User host) {
        this.campaignID = campaignID;
        this.name = name;
        this.host = host;
    }

}



class User{
    int userID;
    String username;
    ArrayList<Campaign> campaigns;
    ArrayList<Character> characters;
    String email;
    String password;
    User(int userID, String username){
        super();
        this.userID = userID;
        this.username = username;
        this.campaigns = new ArrayList<Campaign>();
    }

    void addCampaign(Campaign campaign){
        this.campaigns.add(campaign);
    }

    void removeCampaign(String campaignID){
        this.campaigns.removeIf(c -> c.campaignID.equals(campaignID));
    }

    void updateCampaign(String campaignID){

    }
}

class UserFrame extends JFrame{
    private User user;
    UserFrame(User user){
        //Frame setting
        this.user = user;
        setTitle("GuildQuest");
        setSize(720,720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addCampaign = new JButton("Add Campaign");
        JButton deleteCampaign = new JButton("Delete Campaign");
        JButton updateCampaign = new JButton("Update Campaign");

        addCampaign.addActionListener(e -> {
            AddCampaignDialog dialog = new AddCampaignDialog(this, this.user);
            dialog.setVisible(true);
            Campaign campaign = dialog.getCampaign();
            if (campaign != null){
                user.addCampaign(campaign);
            }
        });

        deleteCampaign.addActionListener(e -> {
            System.out.println(user.campaigns);
            DefaultListModel<Campaign> campaignModel = new DefaultListModel<>();
            JList<Campaign> campaignList = new JList<>(campaignModel);
            for (Campaign c : user.campaigns) {
                campaignModel.addElement(c);
            }
            campaignList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            RemoveCampaign dialog = new RemoveCampaign(this,campaignList);
            dialog.setVisible(true);
            String campaignID = dialog.getCampaignID();
            if (campaignID != null) {
                user.removeCampaign(campaignID);
            }

        });

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

class RemoveCampaign extends JDialog {
    private Campaign campaign;

    RemoveCampaign(JFrame parent, JList<Campaign> campaignList) {

        super(parent, "Remove Campaign", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Remove");

        JTextField campaignName = new JTextField(20);
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
        return campaign.campaignID;
    }
}

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

class LoginFrame extends JFrame{
    LoginFrame(){

        //Frame setting
        setTitle("Login");
        setSize(720,720);
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

public class GuildQuest {
    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}