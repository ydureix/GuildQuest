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
        this.event.startTime.printCurrentTime();
        panel.add(new JLabel(this.event.startTime.toString()), gbc);
        add(panel);
    }
}
