package View;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
public class CountdownTimer {
    JFrame background;
    JLabel counterLabel;
    Font font1 = new Font("Arial", Font.PLAIN, 50);
    Timer timer;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");

    public CountdownTimer() {

        background = new JFrame();
        background.setSize(400, 300);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);

        counterLabel = new JLabel("");
        counterLabel.setBounds(150, 120, 130, 40);
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(font1);

        background.add(counterLabel);
        background.setVisible(true);

       /* counterLabel.setText("01:00");
        second = 0;
        minute = 1;
        countdownTimer();
        timer.start();*/

    }

    public void countdownTimer() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                counterLabel.setText(ddMinute + ":" + ddSecond);

                if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    counterLabel.setText(ddMinute + ":" + ddSecond);
                }
                if(minute==0 && second==0) {
                    timer.stop();
                }
            }
        });
    }
}