import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

class AddEventDialog extends JDialog {
    private QuestEvent event;

    AddEventDialog(JFrame parent, Campaign campaign) {

        super(parent, "Add QuestEvent", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Submit");

        //Allow only numbers
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        JTextField eventName = new JTextField(20);
        JFormattedTextField dayField = new JFormattedTextField(formatter);
        JFormattedTextField hoursField = new JFormattedTextField(formatter);
        JFormattedTextField minutesField = new JFormattedTextField(formatter);

        dayField.setColumns(20);
        hoursField.setColumns(20);
        minutesField.setColumns(20);

        submit.addActionListener(e -> {
            Random rand = new Random();
            int randomNum = rand.nextInt(100000);
            event = new QuestEvent(String.valueOf(randomNum), eventName.getText(), new Realm(String.valueOf(randomNum), eventName.getText(), new OffsetTimeStrategy(Integer.parseInt(dayField.getText()), Integer.parseInt(hoursField.getText()),Integer.parseInt(minutesField.getText()))));
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
        panel.add(new JLabel("Local Time Rule (Day)"), gbc);
        gbc.gridx = 1;
        panel.add(dayField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Local Time Rule (Hour)"), gbc);
        gbc.gridx = 1;
        panel.add(hoursField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Local Time Rule (Minutes)"), gbc);
        gbc.gridx = 1;
        panel.add(minutesField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(submit, gbc);
        add(panel);
    }

    QuestEvent getQuestEvent() {
        return event;
    }
}
