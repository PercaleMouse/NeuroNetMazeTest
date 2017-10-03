package neronetmazetester;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class MazePanel extends javax.swing.JPanel {

    boolean enableEdit = true;

    int mazeSize = 16;
    int vectorSize = mazeSize * mazeSize;
    JButton[] buttons = new JButton[vectorSize];

    ImageIcon wallIcon = new ImageIcon(getClass().getResource("../img/tile_1_2.png"));
    ImageIcon flourIcon = new ImageIcon(getClass().getResource("../img/tile_4_1.png"));

    ActionListener al;

    MazePanel() {
        al = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            if (enableEdit) {
                if (b.getIcon().equals(wallIcon)) {
                    b.setIcon(flourIcon);
                } else {
                    b.setIcon(wallIcon);
                }
            }
            System.out.println(b.getActionCommand());
        };

       // setSize(mazeSize * 20, mazeSize * 20);
        setLayout(new GridLayout(mazeSize, mazeSize, 0, 0));
        setBorder(BorderFactory.createLineBorder(Color.black));
        for (int i = 0; i < vectorSize; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(al);
            buttons[i].setSize(16, 16);
            buttons[i].setIcon(flourIcon);
            buttons[i].setActionCommand(String.valueOf(i));
            buttons[i].enableInputMethods(false);
            buttons[i].setBorder(null);
            add(buttons[i]);
        }

    }

    public void setEditModeON() {
        for (int i = 0; i < vectorSize; i++) {
            enableEdit = true;
            buttons[i].setBorder(new LineBorder(Color.BLACK));
        }
    }

    public void setEditModeOFF() {
        for (int i = 0; i < vectorSize; i++) {
            enableEdit = false;
            buttons[i].setBorder(null);
        }
    }

}
