package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Room {
    private int myId;
    private String myCategory;
    private  ArrayList<Integer> myIdList;
    private  ArrayList<String> myCategoryList;

    public Room(){
        QA bankQA = new QA();
        bankQA.connection();
    }
    public int randomID(){
        Random rand = new Random();
        int id = rand.nextInt(10);
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
        myCategory = cate;
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








}
