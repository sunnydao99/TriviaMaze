package View;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoadGame implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    private JFrame myLoadFrame;
    private JPanel myButtonHolder;
    int[][] mySavedMaze;
    int mySavedX, mySavedY, mySavedDifficulty;
    private String fileName = "StoredData.mze";
    public LoadGame(){
        prepareGUI();
    }
    private void prepareGUI() {
        myLoadFrame = new JFrame("Click the saved game button to load game");
        myLoadFrame.setSize(400, 300);
        myLoadFrame.setLayout(new BorderLayout());
        //myLoadFrame.setBackground(Color.getHSBColor(240,100,70));
        myLoadFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myLoadFrame.setVisible(true);

        myButtonHolder = new JPanel(new GridLayout());
        myButtonHolder.setBackground(Color.red);
        JButton mySavedGameButton = new JButton("Saved Game");

        myButtonHolder.add(mySavedGameButton);
        myLoadFrame.add(myButtonHolder);
        mySavedGameButton.addActionListener(e -> {
            try{
                FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(file);

                mySavedMaze = (int[][])in.readObject();
                mySavedX = (int)in.readObject();
                mySavedY = (int)in.readObject();
                mySavedDifficulty = (int)in.readObject();

                GameView mySavedGame = new GameView(mySavedDifficulty,mySavedMaze,mySavedX,mySavedY);

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
