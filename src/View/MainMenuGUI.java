/**
 * @author satindersingh
 * @version 12/2/2022
 */
package View;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author: Satinder singh
 * @version 12/4/2022
 *
 */

/**
 * This class displays the main menu that the user will interact with on a new Jframe window
 */
public class MainMenuGUI {
    private JFrame myMainFrame;
    private JLabel myHeaderLabel;
    private Panel myControlPanel;
    private boolean soundOn = true;
    private String myBgMusicFile = "Assets/soundBG.wav";
    private Clip clip;
    private long myMusicPauseTime = 0;
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    /**
     * Default constructor that calls prepareGUI() method
     * @throws IOException to see if the file exists
     * @throws UnsupportedAudioFileException to make sure the format is correct
     * @throws LineUnavailableException to make sure data is in bounds
     */
    public MainMenuGUI() {

        prepareGUI();
        playMusic();
    }

    /**
     * prepareGUI():This method displays the choices for the player to navigate the program
     * this also plays the background music
     * @throws IOException to see if the file exists
     * @throws UnsupportedAudioFileException to make sure the format is correct
     * @throws LineUnavailableException to make sure data is in bounds
     */
    private void prepareGUI(){
        myMainFrame = new JFrame("Trivia Maze");

        try {
            AudioInputStream myBackgroundMusic = AudioSystem.getAudioInputStream(new File(myBgMusicFile));
            clip = AudioSystem.getClip();
            clip.open(myBackgroundMusic);

            myMainFrame.setSize(1000, 900);
            myMainFrame.setLayout(null);
            myMainFrame.setLayout(new BorderLayout());
            myMainFrame.setContentPane(new JLabel(new ImageIcon("Assets/pictureBG.png")));
            myMainFrame.setLayout(new FlowLayout());

            myHeaderLabel = new JLabel();
            myHeaderLabel.setBounds(50, 10, 410, 110);
            myHeaderLabel.setFont(new Font("Big Font", Font.BOLD, 30));
            myHeaderLabel.setForeground(Color.RED);
            myHeaderLabel.setText("Welcome to Trivia Maze!");

            myMainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });

            myControlPanel = new Panel(new GridBagLayout());
            myControlPanel.setBounds(100, 80, 900, 400);

            myMainFrame.add(myHeaderLabel);
            myMainFrame.add(myControlPanel);

            myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myMainFrame.setVisible(true);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (UnsupportedAudioFileException e){
            System.out.println(e);
        }
        catch (LineUnavailableException e){
            System.out.println(e);
        }
    }

    /**
     * playMusic(): display sound
     */
    private void playMusic()  {
        if (soundOn) {
            clip.setMicrosecondPosition(myMusicPauseTime);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            myMusicPauseTime = clip.getMicrosecondLength();
            clip.stop();
        }
    }

    /**
     * showActionListener(): This method handles the actionListeners of the main menu and
     * instantiates the correct classes based on user input
     * @return: void
     */
    public void showActionListener(){

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
        JButton mainMenuButton = new JButton("Play Game");

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.getHSBColor(240,100,70));
        JButton loadButton = new JButton("Load Game");

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.getHSBColor(240,100,72));
        JButton instructButton = new JButton("Instructions");

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.getHSBColor(200,100,79));
        JButton helpButton = new JButton("Help");

        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.getHSBColor(240,100,76));
        JButton aboutButton = new JButton("About");

        JPanel panel6 = new JPanel();
        JButton musicButton = new JButton("Music On/Off");
        panel6.setBackground(Color.getHSBColor(240,100,80));

        panel1.add(mainMenuButton);
        mainMenuButton.addActionListener(e -> {
            {
                PlayGameView myPG = new PlayGameView();
                myPG.showEventDemo();
            }
        });
        panel2.add(loadButton);
        loadButton.addActionListener(e -> {
            LoadGame myLG = new LoadGame();
        });
        panel3.add(instructButton);
        instructButton.addActionListener(e -> {
            Instructions myInst = new Instructions();
            myInst.prepareGUI();
        });
        panel4.add(helpButton);
        helpButton.addActionListener(e -> {
            Help myHelp = new Help();
            myHelp.prepareGUI();
        });
        panel5.add(aboutButton);
        aboutButton.addActionListener(e -> {
            About myAbout = new About();
            myAbout.prepareGUI();
        });
        panel6.add(musicButton);
        musicButton.addActionListener(e -> {

            if(soundOn){
                soundOn = false;
                playMusic();

            }else{
                soundOn = true;
                playMusic();
            }

        });

        myControlPanel.add(panel1);
        myControlPanel.add(panel2);
        myControlPanel.add(panel3);
        myControlPanel.add(panel4);
        myControlPanel.add(panel5);
        myControlPanel.add(panel6);

        myMainFrame.setVisible(true);
    }
}