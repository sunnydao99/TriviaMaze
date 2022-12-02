package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import Model.*;
import javax.swing.BorderFactory;

public class MainView {
    private JFrame mainFrame;
    private JButton btnOpen;
    private Panel panelRoom;
    private String myCate;
    private int myId;

    public MainView(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("TriviaMaze View");
        mainFrame.setBounds(10, 10, 600, 550);
        //mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        panelRoom = new Panel(new GridBagLayout());
        btnOpen = new JButton("Create Room");
        btnOpen.setBounds(190, 120, 130,40);
        panelRoom.add(btnOpen);

        panelRoom.setBounds(40,80, 400,130);
        panelRoom.setBackground(Color.PINK);

        mainFrame.add(panelRoom);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

    public void showEventDemo(){

        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnOpen,"Indirect to Challenge");
                goToDoor(myCate, myId);
            }
        });

    }
    public void goToDoor(String theCate, int theId){
        if(theCate.equals("MC")){
            RoomMCView viewMC = new RoomMCView(theCate, theId);
        }
        else if(theCate.equals("TF")){
            RoomTFView viewTF = new RoomTFView(theCate, theId);
        }
        else {
            RoomSAView viewSA = new RoomSAView(theCate, theId);
        }
    }
}
