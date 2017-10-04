package neronetmazetester;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class MazePanel extends javax.swing.JPanel {

    boolean enableEdit = false;

    int mazeSize = 16;
    int vectorSize = mazeSize * mazeSize;
    JButton[] buttons = new JButton[vectorSize];

    ImageIcon wallIcon = new ImageIcon(getClass().getResource("../img/tile_1_2.png"));
    ImageIcon flourIcon = new ImageIcon(getClass().getResource("../img/tile_4_1.png"));
    ImageIcon iconN = new ImageIcon(getClass().getResource("../img/N.png"));
    ImageIcon iconE = new ImageIcon(getClass().getResource("../img/E.png"));
    ImageIcon iconS = new ImageIcon(getClass().getResource("../img/S.png"));
    ImageIcon iconW = new ImageIcon(getClass().getResource("../img/W.png"));
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
        try {
            readMaze();
        } catch (IOException ex) {
            Logger.getLogger(MazePanel.class.getName()).log(Level.SEVERE, null, ex);
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
            writeMaze();
        }
    }

    public void writeMaze() {
        File file = new File("Maze.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (int i = 0; i < vectorSize; i++) {
                if (buttons[i].getIcon().equals(wallIcon)) {
                    fr.write("1");
                } else {
                    fr.write("0");
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
            }
        }
    }

    public void readMaze() throws IOException {
        Path path = Paths.get("Maze.txt");
        //считываем содержимое файла в массив байт
        byte[] bytes = Files.readAllBytes(path);
        for (int i = 0; i < vectorSize; i++) {
            if (bytes[i] == 49) {
                buttons[i].setIcon(wallIcon);
            } else {
                buttons[i].setIcon(flourIcon);
            }
        }
    }

    public int tryMoveN(int i) {

        if (buttons[i - mazeSize].getIcon().equals(wallIcon)) {
            buttons[i].setIcon(iconN);
            return (i);

        } else {
            buttons[i].setIcon(flourIcon);
            buttons[i - mazeSize].setIcon(iconN);
            return (i - mazeSize);
        }
    }

    public int tryMoveE(int i) {
        if (buttons[i + 1].getIcon().equals(wallIcon)) {
            buttons[i].setIcon(iconE);
            return (i);
        } else {
            buttons[i].setIcon(flourIcon);
            buttons[i + 1].setIcon(iconE);
            return (i + 1);
        }
    }

    public int tryMoveS(int i) {
        if (buttons[i + mazeSize].getIcon().equals(wallIcon)) {
            buttons[i].setIcon(iconS);
            return (i);
        } else {
            buttons[i].setIcon(flourIcon);
            buttons[i + mazeSize].setIcon(iconS);
            return (i + mazeSize);
        }
    }

    public int tryMoveW(int i) {
        if (buttons[i - 1].getIcon().equals(wallIcon)) {
            buttons[i].setIcon(iconW);
            return (i);
        } else {
            buttons[i].setIcon(flourIcon);
            buttons[i - 1].setIcon(iconW);
            return (i - 1);
        }
    }
}
