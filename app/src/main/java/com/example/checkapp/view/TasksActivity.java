package com.example.checkapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.checkapp.R;
import com.example.checkapp.adapter.Adapter_Checks;
import com.example.checkapp.adapter.Adapter_tasks;
import com.example.checkapp.model.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private FloatingActionButton btnAddTasks;
    private Adapter_tasks adapter_tasks;
    private RecyclerView recyclerViewTasks;
    private ArrayList<Tasks> listTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ids();

        //RecyclerView
        adapter_tasks = new Adapter_tasks(this, listTasks);
        LinearLayoutManager lmg = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTasks.setLayoutManager(lmg);
        recyclerViewTasks.setAdapter(adapter_tasks);
        recyclerViewTasks.setHasFixedSize(true);


    }

    public void ids(){

        btnAddTasks = findViewById(R.id.floatingButtonAddTask);
        recyclerViewTasks = findViewById(R.id.recyclerTasks);

    }

    public void clickEvent(View view) {
        Intent i = new Intent(this, AddTaskActivity.class);
        startActivity(i);
    }
}