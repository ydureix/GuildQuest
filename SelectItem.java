import javax.swing.*;
import java.awt.*;

class SelectItem extends JDialog {
    private Item item;

    SelectItem(JFrame parent, JList<Item> itemList) {

        super(parent, "Select QuestEvent", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Select");

        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Item selected = itemList.getSelectedValue();
                if (selected != null) {
                    item = selected;
                }
            }
        });

        submit.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(itemList), BorderLayout.CENTER);
        panel.add(submit);
        add(panel);

    }

    Item getItem() {
        if (item != null) {
            return item;
        } else {
            return null;
        }
    }
}
