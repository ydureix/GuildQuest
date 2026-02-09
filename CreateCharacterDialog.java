import javax.swing.*;
import java.awt.*;
import java.util.Random;

class CreateCharacterDialog extends JDialog {
    private Character character;

    CreateCharacterDialog(JFrame parent, User user) {

        super(parent, "Create Character", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Submit");

        String[] classOptions = {"Archer", "Mage", "Tank"};

        JTextField characterName = new JTextField(20);
        JComboBox<String> selectedClass = new JComboBox<>(classOptions);

        submit.addActionListener(e -> {
            Random rand = new Random();
            int randomNum = rand.nextInt(100000);
            this.character = userFactory.createCharacter(String.valueOf(randomNum), characterName.getText(), (String) selectedClass.getSelectedItem(), 1);
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
        panel.add(new JLabel("Character Name"), gbc);
        gbc.gridx = 1;
        panel.add(characterName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Select Class"), gbc);
        gbc.gridy = 1;
        panel.add(selectedClass, gbc);
        add(panel);
    }

    Character getCharacter() {
        return character;
    }
}
