package View;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * @author: Satinder singh
 * @version 12/11/2022
 *
 */

/**
 * LoadGame class save status of maze
 */
public class LoadGame implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private JFrame myLoadFrame;
    private JPanel myPannelHolder;
    int[][] mySavedMaze;
    int mySavedX, mySavedY, mySavedLevel;
    private String fileName = "Assets/StoredData.txt";

    /**
     * LoadGame(): constructor
     */
    public LoadGame(){
        prepareGUI();
    }

    /**
     * prepareGUI(): set up for components
     */
    private void prepareGUI() {
        myLoadFrame = new JFrame("Click the saved game button to load game");
        myLoadFrame.setSize(400, 300);
        myLoadFrame.setLayout(null);
        myLoadFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myLoadFrame.setVisible(true);

        myPannelHolder = new JPanel();
        myPannelHolder.setBackground(Color.RED);
        myPannelHolder.setToolTipText("Please, click the saved game button to load game");
        myPannelHolder.setBounds(120, 100, 130, 40);

        JButton mySavedGameButton = new JButton("Saved Game");
        mySavedGameButton.setBounds(120, 120, 90, 30);

        myPannelHolder.add(mySavedGameButton);
        myLoadFrame.add(myPannelHolder);

        mySavedGameButton.addActionListener(e -> {
            try{
                FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(file);

                mySavedMaze = (int[][])in.readObject();
                mySavedX = (int)in.readObject();
                mySavedY = (int)in.readObject();

                GameView mySavedGame = new GameView(mySavedMaze,mySavedX,mySavedY);

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}
