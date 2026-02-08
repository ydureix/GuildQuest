import javax.swing.*;
import java.awt.*;
import java.util.Random;

class AddEventDialog extends JDialog {
    private QuestEvent event;

    AddEventDialog(JFrame parent, Campaign campaign) {

        super(parent, "Add QuestEvent", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Submit");

        JTextField eventName = new JTextField(20);

        submit.addActionListener(e -> {
            Random rand = new Random();
            int randomNum = rand.nextInt(100000);
            event = new QuestEvent(String.valueOf(randomNum), eventName.getText(), new Realm(String.valueOf(randomNum), eventName.getText()));
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
        panel.add(new JLabel("Quest Event Name"), gbc);
        gbc.gridx = 1;
        panel.add(eventName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submit, gbc);
        add(panel);
    }

    QuestEvent getQuestEvent() {
        return event;
    }
}
