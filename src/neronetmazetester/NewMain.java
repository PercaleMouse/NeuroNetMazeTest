package neronetmazetester;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yuri Trunov
 */
public class NewMain {
    
       
         

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.JFrame f =  new javax.swing.JFrame(); 
        f.setLayout(new FlowLayout()); 
        f.setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
        f.setSize(800,640);
    
          
        //editMazeButton.setSize(50, 30);
        MazePanel mazePanel = new MazePanel();
        f.add(mazePanel);
        f.setVisible(true);
    }
    
                                         

    
}
