package com.cwtstudio.todokotlin

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var btnAdd: AppCompatButton
    private lateinit var edtTask: EditText
    private var itemList: ArrayList<String> = ArrayList()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.listView)
        btnAdd = findViewById(R.id.btnAddTask)
        edtTask = findViewById(R.id.edtTask)


        itemList = FileHelper.readItem(this@MainActivity)

        arrayAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itemList
        )

        listView.adapter = arrayAdapter

        btnAdd.setOnClickListener {
            if (edtTask.text.toString().isEmpty()) return@setOnClickListener
            val item = edtTask.text.toString()
            itemList.add(item)
            FileHelper.writeItem(itemList, this@MainActivity)
            arrayAdapter.notifyDataSetChanged()
        }


        listView.setOnItemClickListener { parent, view, position, id ->
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Delete?")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton(
                    "Yes"
                ) { dialog, which ->
                    itemList.remove(parent.getItemAtPosition(position).toString())
                    FileHelper.writeItem(itemList, this@MainActivity)
                    arrayAdapter.notifyDataSetChanged()
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }


    }
}