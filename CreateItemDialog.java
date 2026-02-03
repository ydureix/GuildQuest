import javax.swing.*;
import java.awt.*;

class CreateItemDialog extends JDialog {
    private Item item;

    CreateItemDialog(JFrame parent) {

        super(parent, "Create Item", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Submit");

        String[] classOptions = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};

        JTextField ItemName = new JTextField(20);
        JComboBox<String> rarity = new JComboBox<>(classOptions);

        submit.addActionListener(e -> {
            this.item = new Item(ItemName.getText(), (String) rarity.getSelectedItem());
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
        panel.add(new JLabel("Item Name"), gbc);
        gbc.gridx = 1;
        panel.add(ItemName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Rarity"), gbc);
        gbc.gridy = 1;
        panel.add(rarity, gbc);
        add(panel);
    }

    Item getItem() {
        return this.item;
    }
}
