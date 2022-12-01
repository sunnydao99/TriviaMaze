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
import javax.swing.*;

//Satinder
public class MainMenuGUI {
    private JFrame myMainFrame;
    private JLabel myHeaderLabel;
    private JLabel myStatusLabel;
    private JPanel myControlPanel;

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public MainMenuGUI() throws IOException {
        prepareGUI();
    }
    private void prepareGUI() throws IOException {
        myMainFrame = new JFrame("Trivia Maze");

//        URL url = new URL("</Users/satindersingh/TriviaMaze/TriviaMaze.iml>");
//        Icon icon = new ImageIcon(url);
//        JLabel label = new JLabel(icon);
//
//        //JFrame f = new JFrame("Animation");
//        myMainFrame.getContentPane().add(label);
//        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        myMainFrame.pack();
//        myMainFrame.setLocationRelativeTo(null);
//        //f.setVisible(true);


//        JFrame f = new JFrame();
//        try {
//            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("MazeGIF.gif")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        f.pack();
//        f.setVisible(true);



        myMainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myMainFrame.setLayout(new GridLayout(3, 1));
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
        myControlPanel.setLayout(new GridLayout(6,3));
        myMainFrame.add(myHeaderLabel);
        myMainFrame.add(myControlPanel);
        myMainFrame.add(myStatusLabel);
        myMainFrame.setVisible(true);
    }
    public void showActionListener(){
        //myHeaderLabel.setText("Welcome to Trivia Maze!");

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
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

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayGame myPG = new PlayGame();
            }
        });
        panel1.add(mainMenuButton);
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
        myMainFrame.setVisible(true);
    }
}