import javax.swing.*;
import java.awt.*;



class ItemFrame extends JFrame {


    ItemFrame(Item item) {
        //Frame setting
        setTitle("Item");
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
        panel.add(new JLabel("Name"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(item.getItemName()), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Rarity"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(item.getItemRarity()), gbc);
        add(panel);
    }
}
