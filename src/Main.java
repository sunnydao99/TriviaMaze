
import Model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import View.*;

public class Main {

    public static void main(String[] args) {
	// An Testing
        QA bankQA = new QA();
        /*bankQA.createDB("Database_QA.db");*/
        bankQA.connection();
       /* bankQA.createTableMC();
        bankQA.createTableTF();
        bankQA.createTableSA();*/

       /* bankQA.insertTableMC();// completed tableMC
        bankQA.insertTableSA();//completed tableSA
        bankQA.insertTableTF();*/


       /* bankQA = new QAMC("MC", 1);
        bankQA.getQuestion("MC", 1);
        bankQA.getAnswer("MC", 1);
        bankQA.getChoices("MC",1);
        ((QAMC) bankQA).printChoicesMC();
        ((QAMC) bankQA).getArrRedChoiceMC("MC", 1);
        ((QAMC) bankQA).printRedChoiceMC();*/
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
       /* QAMC bankMC = new QAMC();
        System.out.println(bankMC.getQuestionMC(2, "MC"));
        System.out.println(bankMC.getCorrAnsMC(2, "MC"));
        bankMC.getArrChoicesMC(2, "MC");
        System.out.println("print Array Choice:");
        bankMC.printChoicesMC();
        bankMC.getArrRedChoiceMC(2, "MC");
        System.out.println("print Array Reduce Choice:");
        bankMC.printRedChoiceMC();*/

        //testQATF
        /*QATF bankTF = new QATF();
        System.out.println(bankTF.getQuestionTF(3, "TF"));
        System.out.println(bankTF.getCorrAnsTF(3, "TF"));
        bankTF.getArrChoicesTF(3, "TF");
        System.out.print("print Arr choices TF: ");
        bankTF.printChoicesTF();*/

        //test QASA
       /* QASA bankSA = new QASA();
        System.out.println(bankSA.getQuestionSA(2, "SA"));
        System.out.println(bankSA.getCorrAnsSA(2, "SA"));
        System.out.println(bankSA.getHintSA(2, "SA"));*/

        //TestRoom
        /*Room room = new Room();
        room.randomIDCategory();
        room.randomQA();*/

        /*for(int i = 0; i < id_list.size(); i++){
            System.out.print( id_list.get(i) + ", ");
        }
        System.out.println();*/

        RoomView rw = new RoomView("Integer Calculator");



    }
}
