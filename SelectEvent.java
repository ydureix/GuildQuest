import javax.swing.*;
import java.awt.*;

class SelectEvent extends JDialog {
    private QuestEvent QuestEvent;

    SelectEvent(JFrame parent, JList<QuestEvent> QuestEventList) {

        super(parent, "Select QuestEvent", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JButton submit = new JButton("Select");

        QuestEventList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                QuestEvent selected = QuestEventList.getSelectedValue();
                if (selected != null) {
                    QuestEvent = selected;
                }
            }
        });

        submit.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(QuestEventList), BorderLayout.CENTER);
        panel.add(submit);
        add(panel);

    }

    String getQuestEventID() {
        if (QuestEvent != null) {
            return QuestEvent.eventID;
        } else {
            return null;
        }
    }
}
