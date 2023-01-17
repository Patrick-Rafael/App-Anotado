package com.example.checkapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.checkapp.R;
import com.example.checkapp.adapter.Adapter_Checks;
import com.example.checkapp.helper.RecyclerItemClickListener;
import com.example.checkapp.helper.TaskDAO;
import com.example.checkapp.model.Checks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChecks;
    private Adapter_Checks adapterChecks;
    private ArrayList<Checks> listChecks = new ArrayList<>();
    private FloatingActionButton btnAddChecks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ids();
        listItens();

        //Evento de clique
        recyclerViewChecks.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerViewChecks,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getApplicationContext(), TasksActivity.class);
                        startActivity(i);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));

    }

    @Override
    protected void onStart() {
        super.onStart();
        listItens();
    }

    public void ids() {

        recyclerViewChecks = findViewById(R.id.recyclerChecks);
        btnAddChecks = findViewById(R.id.floatingButtonAddTask);

    }

    public void listItens() {
        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        listChecks = (ArrayList<Checks>) taskDAO.list();

        //RecyclerView
        adapterChecks = new Adapter_Checks(this, listChecks);
        LinearLayoutManager lmg = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewChecks.setLayoutManager(lmg);
        recyclerViewChecks.setAdapter(adapterChecks);
        recyclerViewChecks.setHasFixedSize(true);

    }

    public void clickEvent(View view) {
        Intent i = new Intent(this, AddCheckActivity.class);
        startActivity(i);
    }

}