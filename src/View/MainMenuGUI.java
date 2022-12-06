package View;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaName;
import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.*;

//Satinder
public class MainMenuGUI {
    private JFrame myMainFrame;
    private JFrame myGifFrame;
    private JLabel myHeaderLabel;
    private JLabel myStatusLabel;
    private JPanel myControlPanel;

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public MainMenuGUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        prepareGUI();
    }
    private void prepareGUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        myMainFrame = new JFrame("Trivia Maze");
        String myBgMusicFile = "kim-lightyear-angel-eyes-chiptune-edit-110226-_1_.wav";
        AudioInputStream myBackgroundMusic = AudioSystem.getAudioInputStream(new File(myBgMusicFile));
        Clip clip = AudioSystem.getClip();
        clip.open(myBackgroundMusic);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        //ImageIcon gif = new ImageIcon("http://doc.gold.ac.uk/compartsblog/wp-content/uploads/2017/05/frames.gif");

        //myGifFrame.setIconImage(gif.getImage());

        //myMainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myMainFrame.setLayout(new GridLayout(3, 1));
        myMainFrame.setSize(700, 600);
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //BufferedImage myPicture = ImageIO.read(new File("TriviaMaze.iml"));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));


        myHeaderLabel = new JLabel("",JLabel.CENTER );
        myHeaderLabel.setSize(350,400);
        myStatusLabel = new JLabel("",JLabel.CENTER);
        myStatusLabel.setSize(350,400);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        myControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myControlPanel.setLayout(new GridLayout(6,1));
        //myMainFrame.add(myGifFrame);
        myMainFrame.add(myHeaderLabel);
        myMainFrame.add(myControlPanel);
        myMainFrame.add(myStatusLabel);
        //myGifFrame.setVisible(true);
        myMainFrame.setVisible(true);
    }
    public void showActionListener(){
        //myHeaderLabel.setText("Welcome to Trivia Maze!");

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
        panel1.setSize(500,150);
        //panel1.setLayout(myBL);
        JButton mainMenuButton = new JButton("Play Game");

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.getHSBColor(240,100,70));
        JButton loadButton = new JButton("Load Game");

        JPanel panel3 = new JPanel();
        //panel3.setPreferredSize(new Dimension(200,100));
        panel3.setBackground(Color.getHSBColor(240,100,72));
        JButton instructButton = new JButton("Instructions");

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.getHSBColor(240,100,74));
        JButton helpButton = new JButton("Help");

        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.getHSBColor(240,100,76));
        JButton aboutButton = new JButton("About");

        JPanel panel6 = new JPanel();
        panel6.setBackground(Color.getHSBColor(240,100,78));
        JButton exitButton = new JButton("Exit");
        //panel6.setBackground(Color.getHSBColor(240,100,80));

//        mainMenuButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                PlayGameView myPG = new PlayGameView();
//            }
//        });
        panel1.add(mainMenuButton);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    PlayGameView myPG = new PlayGameView();
                }
            }
        });
        panel2.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadGame myLG = new LoadGame();
            }
        });
        panel3.add(instructButton);
        instructButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructions myInst = new Instructions();
                //myInst.showListener();
            }
        });
        panel4.add(helpButton);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Help myHelp = new Help();
            }
        });
        panel5.add(aboutButton);
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About myAbout = new About();
            }
        });
//        panel6.add(exitButton);
//        aboutButton.add(new WindowAdapter() {
//            @Override
//            public void windowClosing() {
//                windowClosing(null);
//            }
//
//        });
//
        myControlPanel.add(panel1);
        myControlPanel.add(panel2);
        myControlPanel.add(panel3);
        myControlPanel.add(panel4);
        myControlPanel.add(panel5);
        //myControlPanel.add(panel6);
        //myGifFrame.setVisible(true);
        myMainFrame.setVisible(true);
    }
}