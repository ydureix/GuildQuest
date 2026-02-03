import javax.swing.*;
import java.awt.*;



class CharacterFrame extends JFrame {
    private Character character;


    CharacterFrame(Character character) {
        //Frame setting
        this.character = character;
        setTitle("GuildQuest");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton viewInventory = new JButton("Select");

        //View Inventory
        viewInventory.addActionListener(e ->{

        });

        //Panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // spacing
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.character.name), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Class"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.character.characterClass), gbc);
        add(panel);
    }
}
