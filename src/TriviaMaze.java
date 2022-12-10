import View.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class TriviaMaze {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
	// An Testing

       /* QA bankQA = new QA("MC", 1);*/
        /*bankQA.createDB("Database_QA.db");*/
      /*  bankQA.connection();*/
       /* bankQA.createTableMC();
        bankQA.createTableTF();
        bankQA.createTableSA();*/

       /* bankQA.insertTableMC();// completed tableMC
        bankQA.insertTableSA();//completed tableSA
        bankQA.insertTableTF();*/



        /*bankQA = new QAMC("MC", 1);
        bankQA.getQuestion("MC", 1);
        bankQA.getAnswer("MC", 1);
        bankQA.getChoices("MC",1);
        ((QAMC) bankQA).printChoicesMC();
        ((QAMC) bankQA).getArrRedChoiceMC("MC", 1);
        ((QAMC) bankQA).printRedChoiceMC();
       /* bankQA = new QATF("TF", 2);
        bankQA.getQuestion("TF", 2);
        bankQA.getAnswer("TF", 2);
        bankQA.getChoices("TF", 2);*/
      /*  bankQA = new QASA();
        bankQA.getQuestion("SA", 2);
        bankQA.getAnswer("SA", 2);
        bankQA.getChoices("SA", 2);
        ((QASA) bankQA).getHintSA("SA", 2);
*/

        //Test QAMC
       /* QAMC bankMC = new QAMC("MC", 2);*/
        /*System.out.println(bankMC.getQuestion("MC", 2));
        System.out.println(bankMC.getAnswer("MC", 2));
        bankMC.getChoices("MC", 2);
        System.out.println("print Array Choice:");
        bankMC.printChoicesMC();
        bankMC.getArrRedChoiceMC("MC", 2);
        System.out.println("print Array Reduce Choice:")
        bankMC.printRedChoiceMC();;*/
       /* bankMC.getOptionForRedChoice("MC", 2);*/


        //Test for RoomSAView
       /* RoomSAView rmSA = new RoomSAView("SA", 7);
        rmSA.showEventDemo();
        rmSA.roomShow();*/

        //TestRoom TFview
        /*RoomTFView rmtf = new RoomTFView("TF", 9);
        rmtf.showEventDemo();
        rmtf.roomShow();*/


       /* QA bk = new QA();
        bk.connection();*/

        //Test View
        /*RoomMCView rwMC = new RoomMCView("MC", 8);
        rwMC.showEventDemo();
        rwMC.roomShow();*/

        /*RoomTFView rwTF = new RoomTFView("TF", 3);

        RoomSAView rwSA = new RoomSAView("SA", 1);*/


        //Test for PlayGameView
        //PlayGameView lv = new PlayGameView();

        /*RoomSAView saview = new RoomSAView("SA", 7);
        RoomTFView tfview = new RoomTFView("TF", 5);*/
       /* RoomMCView mcview = new RoomMCView("MC",9);
        mcview.roomShow();*/
        //Test LvEasyView
        /*SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {

                LvEasyView view = new LvEasyView();

            }
        });*/

        //testing for counter timer
        /*CountdownTimer cnttimer = new CountdownTimer();
        cnttimer.countdownTimer();*/
        //testing for QATFE
       /* QA bankExtra = new QA();
        bankExtra.connection();*/
        //bankExtra.insertTableTFExtra();
        //QATFExtra extra = new QATFExtra();
       /* QATFExtra tfExtra = new QATFExtra("TFE", 3);
        tfExtra.getQuestion("TFE", 3);
        tfExtra.getAnswer("TFE", 3);
        tfExtra.getChoices("TFE", 3);
*/


        //Test for About, Help...
        setupGUI();


    }
    public static void setupGUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        MainMenuGUI myMenuListener = new MainMenuGUI();
        myMenuListener.showActionListener();


    }
}
