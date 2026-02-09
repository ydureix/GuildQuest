import javax.swing.*;
import java.awt.*;

class SelectCharacter extends JDialog {
    private Character character;

    SelectCharacter(JFrame parent, JList<Character> characterList) {

        super(parent, "Select Campaign", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Select");

        characterList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Character selected = characterList.getSelectedValue();
                if (selected != null) {
                    character = selected;
                }
            }
        });

        submit.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(characterList), BorderLayout.CENTER);
        panel.add(submit);
        add(panel);

    }

    String getCharacterID() {
        if (character != null) {
            return character.getCharacterID();
        } else {
            return null;
        }
    }
}
