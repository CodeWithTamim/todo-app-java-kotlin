package com.cwtstudio.todokotlin

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * CodeWithTamim
 *
 * @developer Tamim Hossain
 * @mail tamimh.dev@gmail.com
 */
object FileHelper {
    private const val FILE_NAME: String = "listInfo.dat"

    fun writeItem(itemList: ArrayList<String>, context: Context) {
        try {
            val outputStream: FileOutputStream =
                context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            val objectOutPutStream: ObjectOutputStream = ObjectOutputStream(outputStream)
            objectOutPutStream.writeObject(itemList)
            objectOutPutStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun readItem(context: Context): ArrayList<String> {
        try {
            val inputStream: FileInputStream = context.openFileInput(FILE_NAME)
            val objectInputStream = ObjectInputStream(inputStream)
            return objectInputStream.readObject() as ArrayList<String>

        } catch (e: Exception) {
            return ArrayList<String>()
            e.printStackTrace()
        }

    }


}