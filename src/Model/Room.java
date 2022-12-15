package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Room class is built to create random for ID and the category
 * @author: An Nguyen, Satinder Singh
 * @version: 11.02.22
 *
 */

public class Room implements Serializable {

    private static ArrayList<Integer> myIdList;
    private static ArrayList<String> myCategoryList;
    private ArrayList<String> myRooms;

    /**
     * The default constructor
     */
    public Room() {
        myIdList = new ArrayList<Integer>();
        myCategoryList = new ArrayList<String>();
        QA bankQA = new QA();
        bankQA.connectionDB();

    }

    /**
     * This method is built to create a random ID, and return it
     * @return int An integer value of ID
     */
    public static int randomID() {
        Random rand = new Random();
        int max = 12;
        int min = 1;
        int id = rand.nextInt(max + 1 - min) + min;
        return id;

    }

    /**
     * This method  is built to create a random category, and return it
     * @return String A Category
     */
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
        return cate;
    }

    /**
     * This method generates random ID and category to display data for Room
     * and store the value in a ArrayList
     */
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

    /**
     * This method is built to get the last ID  and return it
     * after generate randomly
     * @return int The last ID
     */
    public static int getLastID() {
        return myIdList.get(myIdList.size() - 1);
    }

    /**
     * This method is created to get and return last category
     * after generate randomly
     * @return String The last category
     */
    public static String getLastCategory() {
        return myCategoryList.get(myCategoryList.size() - 1);
    }

    /**
     * This method is built to check the availability of ID and category,
     * if they are correct and return true, otherwise return false
     * @param cate The category
     * @param id Id
     * @return boolean The value of checking the ID and category
     */
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


}
