import javax.swing.*;
import java.awt.*;



class CharacterFrame extends JFrame {
    private Character character;

    Item getItem(){
        DefaultListModel<Item> itemModel = new DefaultListModel<>();
        JList<Item> itemList = new JList<>(itemModel);
        for (Item i : character.getInventory().items) {
            itemModel.addElement(i);
        }
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectItem dialog = new SelectItem(this, itemList);
        dialog.setVisible(true);
        return dialog.getItem();
    }

    CharacterFrame(Character character) {
        //Frame setting
        this.character = character;
        setTitle("GuildQuest");
        setSize(720, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton viewInventory = new JButton("Select");
        JButton addItem = new JButton("Select");
        JButton removeItem = new JButton("Select");

        //View Inventory
        viewInventory.addActionListener(e ->{
            Item item = getItem();
            if (item != null){
                new ItemFrame(item).setVisible(true);
            }
        });

        //Add item
        addItem.addActionListener(e ->{
            CreateItemDialog dialog = new CreateItemDialog(this);
            dialog.setVisible(true);
            Item item = dialog.getItem();
            if (item != null) {
                character.getInventory().addItem(item);
            }
        });

        //Remove item
        removeItem.addActionListener(e ->{
            Item item = getItem();
            character.getInventory().removeItem(item);
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
        panel.add(new JLabel(this.character.getCharacterName()), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Class"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(this.character.getCharacterClass()), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Add item"), gbc);
        gbc.gridx = 1;
        panel.add(addItem, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Remove item"), gbc);
        gbc.gridx = 1;
        panel.add(removeItem, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("View Inventory"), gbc);
        gbc.gridx = 1;
        panel.add(viewInventory, gbc);

        add(panel);
    }
}
