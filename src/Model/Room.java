package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Room {
    private int myId;
    private String myCate;
    private  ArrayList<Integer> myIdList;
    private  ArrayList<String> myCategoryList;
    private  ArrayList<String> myRooms;

    public Room(){
        QA bankQA = new QA();
        bankQA.connection();
    }

    public int randomID(){
        Random rand = new Random();
        int max = 9;
        int min = 1;
        int id = rand.nextInt(max + 1 - min) + min;
        myId = id;
        return id;

    }

    public String randomCategory(){
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
        myCate = cate;
        return cate;
    }


    public void randomIDCategory(){
        myIdList = new ArrayList<Integer>();
        myCategoryList = new ArrayList<String>();
        String tempCate;
        int tempId;
        for(int i = 0; i < 16; i++){
            do {
                tempCate = randomCategory();
                tempId = randomID();

            } while (checkIDCate(tempCate, tempId));
            myCategoryList.add(tempCate);
            myIdList.add(tempId);

        }
        System.out.print("[");
        for (int i = 0; i < myCategoryList.size(); i++) {

            if (i == (myCategoryList.size() - 1)) {
                System.out.print(myCategoryList.get(myCategoryList.size() - 1));
            } else {
                System.out.print(myCategoryList.get(i) + ", ");
            }
        }
        System.out.println("]");
        System.out.print("[");
        for (int i = 0; i < myIdList.size(); i++) {

            if (i == (myIdList.size() - 1)) {
                System.out.print(myIdList.get(myIdList.size() - 1));
            } else {
                System.out.print(myIdList.get(i) + ",  ");
            }
        }
        System.out.println("]");

    }
    public boolean checkIDCate(String cate, int id){
        String mycate;
        int myid;
        for(int i = 0; i < myCategoryList.size(); i++){
            mycate = myCategoryList.get(i);
            myid = myIdList.get(i);
           if(mycate.equals(cate) && myid == id) {
               return true;
           }
        }
        return  false;
    }

    public void printIDlist(){
        System.out.print("[");
        for (int i = 0; i < myIdList.size(); i++) {

            if (i == (myIdList.size() - 1)) {
                System.out.print(myIdList.get(myIdList.size() - 1));
            } else {
                System.out.print(myIdList.get(i) + ", ");
            }
        }
        System.out.println("]");
    }

    public void printCatelist(){
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

    public void randomQA(){
        printIDlist();
        QA bank;
        String ques;
        String ans;
        ArrayList<String> choiceList = new ArrayList<String>();
        ArrayList<String> redChoiceList = new ArrayList<String>();
        ArrayList<String> room = new ArrayList<String>();
        myRooms = new ArrayList<String>();
        for(int i = 0; i < myCategoryList.size(); i++){

            String cate = myCategoryList.get(i);
            int id = myIdList.get(i);
            System.out.println("Room: "+ i);
            if(cate.equals("MC")){
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
            }
            else if(myCategoryList.get(i).equals("TF")){
                bank = new QATF(cate, id);
                bank.getQuestion(cate, id);
                bank.getChoices(cate, id);
                ((QATF) bank).printChoicesTF();
                bank.getAnswer(cate, id);
            }
            else {
                bank = new QASA(cate, id);
                bank.getQuestion(cate, id);
                bank.getAnswer(cate, id);
                ((QASA) bank).getHintSA(cate, id);
            }

        }
        System.out.print("[");
        for (int i = 0; i < room.size(); i++) {

            if (i == (room.size() - 1)) {
                System.out.print(room.get(room.size() - 1));
            } else {
                System.out.print(room.get(i) + ", ");
            }
        }
        System.out.println("]");

    }


}
