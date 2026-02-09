import javax.swing.*;
import java.awt.*;



class EventFrame extends JFrame {
    private QuestEvent event;


    EventFrame(QuestEvent event) {
        //Frame setting
        this.event = event;
        setTitle("GuildQuest");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("World Time"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.event.getStartTime()), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Realm Name"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.event.getRealm().getName()), gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Realm ID"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.event.getRealm().getRealmID()), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Realm Local Time Rule"),gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.event.getRealm().getLocalTime()),gbc);
        add(panel);
    }
}
