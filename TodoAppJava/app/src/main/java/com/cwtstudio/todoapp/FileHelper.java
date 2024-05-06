package com.cwtstudio.todoapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * CodeWithTamim
 *
 * @developer Tamim Hossain
 * @mail tamimh.dev@gmail.com
 */
public class FileHelper {
    private static final String FILE_NAME = "listInfo.dat";

    public static void writeFile(ArrayList<String> itemList, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream objOutPutStream = new ObjectOutputStream(fos);
            objOutPutStream.writeObject(itemList);
            objOutPutStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> readFile(Context context) {
        try {

            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            return (ArrayList<String>) inputStream.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


}
