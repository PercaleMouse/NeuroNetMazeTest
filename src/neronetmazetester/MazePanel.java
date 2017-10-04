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

/**
 *
 * @author Admin
 */
public class MazePanel extends javax.swing.JPanel {
   
    private boolean enableEdit;
    private int mazeSize;
    private int vectorSize;
    private JButton[] buttons;
    private ImageIcon wallIcon;
    private ImageIcon flourIcon;
    private ImageIcon iconN;
    private ImageIcon iconE;
    private ImageIcon iconS;
    private ImageIcon iconW;
    private ActionListener al;

    MazePanel() {
        iconW = new ImageIcon(getClass().getResource("img/W.png"));
        iconS = new ImageIcon(getClass().getResource("img/S.png"));
        iconE = new ImageIcon(getClass().getResource("img/E.png"));
        iconN = new ImageIcon(getClass().getResource("img/N.png"));
        flourIcon = new ImageIcon(getClass().getResource("img/tile_4_1.png"));
        wallIcon = new ImageIcon(getClass().getResource("img/tile_1_2.png"));
        
        mazeSize = 16;
        vectorSize = mazeSize * mazeSize;
        
        buttons = new JButton[vectorSize];
        
        enableEdit = false;
        
        al = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            if (enableEdit) {
                if (b.getIcon().equals(wallIcon)) {
                    b.setIcon(flourIcon);
                } else {
                    b.setIcon(wallIcon);
                }
            }
        };

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
        enableEdit = true;
        for (int i = 0; i < vectorSize; i++) {
            buttons[i].setBorder(new LineBorder(Color.BLACK));
        }
    }

    public void setEditModeOFF() {
        enableEdit = false;
        for (int i = 0; i < vectorSize; i++) {
            buttons[i].setBorder(null);
        }
        writeMaze();
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
