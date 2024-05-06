package com.cwtstudio.todoapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText edtTask;
    private AppCompatButton btnAddTask;
    private ArrayList<String> itemList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        edtTask = findViewById(R.id.edtTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        itemList = FileHelper.readFile(this);
        arrayAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);
        listView.setAdapter(arrayAdapter);


        btnAddTask.setOnClickListener(v -> {
            if (edtTask.getText().toString().isEmpty()) return;
            itemList.add(edtTask.getText().toString());
            FileHelper.writeFile(itemList, this);
            arrayAdapter.notifyDataSetChanged();
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete?")
                    .setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        itemList.remove(parent.getItemAtPosition(position).toString());
                        FileHelper.writeFile(itemList, this);
                        arrayAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        });


    }
}