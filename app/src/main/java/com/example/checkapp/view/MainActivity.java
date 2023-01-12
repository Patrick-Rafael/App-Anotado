package com.example.checkapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.checkapp.R;
import com.example.checkapp.adapter.Adapter_Checks;
import com.example.checkapp.helper.DbHelper;
import com.example.checkapp.model.Checks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChecks;
    private Adapter_Checks adapterChecks;
    private ArrayList<Checks> listChecks = new ArrayList<>();
    private FloatingActionButton btnAddTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ids();
        additens();

        //RecyclerView
        adapterChecks = new Adapter_Checks(this, listChecks);
        LinearLayoutManager lmg = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewChecks.setLayoutManager(lmg);
        recyclerViewChecks.setAdapter(adapterChecks);
        recyclerViewChecks.setHasFixedSize(true);


        

    }

    public void ids() {

        recyclerViewChecks = findViewById(R.id.recyclerChecks);
        btnAddTasks = findViewById(R.id.floatingButtonAddTask);

    }

    public void additens() {

        listChecks = new ArrayList<Checks>();

        listChecks.add(new Checks("Teste Titulo", "testeDescrição"));
        listChecks.add(new Checks("Teste Titulo", "testeDescrição"));
        listChecks.add(new Checks("Teste Titulo", "testeDescrição"));

    }

    public void clickEvent(View view) {
        Intent i = new Intent(this, AddTaskActivity.class);
        startActivity(i);
    }

}