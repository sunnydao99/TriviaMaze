
import Model.*;

import View.*;

public class Main {

    public static void main(String[] args) {
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



        //TestRoom
        /*Room room = new Room();
        room.randomIDCategory();
        room.randomQA();*/

        /*for(int i = 0; i < id_list.size(); i++){
            System.out.print( id_list.get(i) + ", ");
        }
        System.out.println();*/

       /* RoomMCView rwMC = new RoomMCView("MC", 8);
        rwMC.showEventDemo();*/



        /*RoomTFView rwTF = new RoomTFView("TF", 3);
        rwTF.showEventDemo();*/

        RoomSAView rwSA = new RoomSAView("SA", 3);
        rwSA.showEventDemo();

        /*MainView mview = new MainView("SA", 1);
        mview.showEventDemo();*/

    }
}
