package View;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
public class CountdownTimer {
  /*  JFrame background;
    JLabel counterLabel;
    Font font1 = new Font("Arial", Font.PLAIN, 20);*/
    Timer timer;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");
    private String myStrTimer;

    public CountdownTimer() {
        myStrTimer = "01:00";

       /* background = new JFrame();
        background.setSize(400, 300);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);

        counterLabel = new JLabel("");
        counterLabel.setBounds(150, 120, 130, 40);
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(font1);

        background.add(counterLabel);
        background.setVisible(true);*/

        //counterLabel.setText("01:00");
        second = 60;
        minute = 0;
        countdownTimer();
        timer.start();


    }

    public void countdownTimer() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                //counterLabel.setText(ddMinute + ":" + ddSecond);
/*
                if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    myStrTimer = ddMinute + ":" + ddSecond;
                    System.out.println("test string timer 2: "+myStrTimer);
                    timer.stop();
                    //counterLabel.setText(ddMinute + ":" + ddSecond);
                }*/
                if(minute==0 && second==0) {
                    timer.stop();

                }
                else{
                    second--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    myStrTimer = ddMinute + ":" + ddSecond;
                    //System.out.println("test string timer 1: "+myStrTimer);
                }
            }
        });
    }

    public String getStrTimer(){
        return myStrTimer;
    }
}