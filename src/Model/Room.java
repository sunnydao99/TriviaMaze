package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Room {

    private static ArrayList<Integer> myIdList;
    private static ArrayList<String> myCategoryList;
    private ArrayList<String> myRooms;

    public Room() {
        myIdList = new ArrayList<Integer>();
        myCategoryList = new ArrayList<String>();
        QA bankQA = new QA();
        bankQA.connection();


    }

    public static int randomID() {
        Random rand = new Random();
        int max = 9;
        int min = 1;
        int id = rand.nextInt(max + 1 - min) + min;
        //myId = id;
        return id;

    }

    public static String randomCategory() {
        Random rand = new Random();
        int temp = rand.nextInt(3);
        String cate = "";
        if (temp == 0) {
            cate = "MC";
        } else if (temp == 1) {
            cate = "TF";
        } else {
            cate = "SA";
        }
        //myCate = cate;
        return cate;
    }

    public static void randomIDCategory() {
        int tempId;
        String tempCate;

        if(myIdList == null || myCategoryList == null) {
            myIdList = new ArrayList<Integer>();
            myCategoryList = new ArrayList<String>();
        }

        do {
            tempCate = randomCategory();
            tempId = randomID();

        } while (checkIDCate(tempCate, tempId));

        myCategoryList.add(tempCate);
        myIdList.add(tempId);
    }

    public static int getLastID() {
        return myIdList.get(myIdList.size() - 1);
    }

    public static String getLastCategory() {
        return myCategoryList.get(myCategoryList.size() - 1);
    }

    public static boolean checkIDCate(String cate, int id) {
        int myid;
        String mycate;


        for (int i = 0; i < myCategoryList.size(); i++) {
            mycate = myCategoryList.get(i);
            myid = myIdList.get(i);
            if (mycate.equals(cate) && myid == id) {
                return true;
            }
        }
        return false;
    }

    public static void randomQA() {
        QA bank;
        String ques;
        String ans;
        ArrayList<String> choiceList = new ArrayList<String>();
        ArrayList<String> redChoiceList = new ArrayList<String>();
        ArrayList<String> room = new ArrayList<String>();

        for (int i = 0; i < myCategoryList.size(); i++) {

            String cate = myCategoryList.get(i);
            int id = myIdList.get(i);
            System.out.println("Room: " + i);
            if (cate.equals("MC")) {
                bank = new QAMC(cate, id);
                ques = bank.getQuestion(cate, id);
                choiceList = bank.getChoices(cate, id);
                /*  ((QAMC) bank).printChoicesMC();*/
                ans = bank.getAnswer(cate, id);
                redChoiceList = ((QAMC) bank).getArrRedChoiceMC(cate, id);
                /* ((QAMC) bank).printRedChoiceMC();*/
                room.add(ques);
                room.add(String.valueOf(choiceList));
                room.add(ans);
                room.add(String.valueOf(redChoiceList));
            } else if (myCategoryList.get(i).equals("TF")) {
                bank = new QATF(cate, id);
                bank.getQuestion(cate, id);
                bank.getChoices(cate, id);
                ((QATF) bank).printChoicesTF();
                bank.getAnswer(cate, id);
            } else {
                bank = new QASA(cate, id);
                bank.getQuestion(cate, id);
                bank.getAnswer(cate, id);
                ((QASA) bank).getHintSA(cate, id);
            }

        }

    }

    public static void printIDlist() {
        System.out.print("[");
        System.out.println("size: " + myIdList.size());
        for (int i = 0; i < myIdList.size(); i++) {

            if (i == (myIdList.size() - 1)) {
                System.out.print(myIdList.get(myIdList.size() - 1));
            } else {
                System.out.print(myIdList.get(i) + ",  ");
            }
        }
        System.out.println("]");
    }

    public void printCatelist() {
        System.out.print("[");
        for (int i = 0; i < myCategoryList.size(); i++) {

            if (i == (myCategoryList.size() - 1)) {
                System.out.print(myCategoryList.get(myCategoryList.size() - 1));
            } else {
                System.out.print(myCategoryList.get(i) + ", ");
            }
        }
        System.out.println("]");
    }


}
