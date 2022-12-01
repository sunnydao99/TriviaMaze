package View;

import javax.swing.*;
import java.awt.*;

public class LoadGame {
    private JFrame myLoadFrame;
    public LoadGame(){
        prepareGUI();
    }
    private void prepareGUI() {
        myLoadFrame = new JFrame("Choose from the following saved games");
        myLoadFrame.setSize(800, 700);
        myLoadFrame.setLayout(new BorderLayout());
        myLoadFrame.setBackground(Color.getHSBColor(240,100,70));
        myLoadFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myLoadFrame.setVisible(true);



    }
}
