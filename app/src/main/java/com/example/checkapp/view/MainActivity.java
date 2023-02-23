package com.example.checkapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.checkapp.R;
import com.example.checkapp.adapter.Adapter_Checks;
import com.example.checkapp.helper.CheckDAO;
import com.example.checkapp.helper.RecyclerItemClickListener;
import com.example.checkapp.model.Checks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView recyclerViewChecks;
    private Adapter_Checks adapterChecks;
    private ArrayList<Checks> listChecks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        ids();
        listItens();


        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerViewChecks);

        //getSupportActionBar().hide();

        //Evento de clique
        recyclerViewChecks.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerViewChecks,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Checks checkSelecionado = listChecks.get(position);

                        Intent intent = new Intent(MainActivity.this, AddCheckActivity.class);
                        intent.putExtra("checkSelecionada", checkSelecionado);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {


                        alertDelete(position);
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


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {


            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {


           /* if (listChecks.size() == 4) {
                position = position - 4;
                alertDelete(position);
            } else {
                Toast.makeText(getApplicationContext(), "Deu ruim", Toast.LENGTH_SHORT).show();
            }*/

           // alertDelete(position);

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.deleteColor))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftLabel("Deletar")
                    .addCornerRadius(1, 18)
                    .setSwipeLeftLabelColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                    .create()
                    .decorate();


            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    public void alertDelete(int position) {


        AlertDialog.Builder alertFinalizar = new AlertDialog.Builder(MainActivity.this);

        alertFinalizar.setTitle("Finalizar  tarefa");
        alertFinalizar.setMessage("Deseja finalizar a tarefa?");
        alertFinalizar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                CheckDAO checkDAO = new CheckDAO(getApplicationContext());

                Checks checkSelecionado = listChecks.get(position);
                checkDAO.delete(checkSelecionado);

                listItens();


            }
        });

        alertFinalizar.setNegativeButton("Não", (dialogInterface, i) -> listItens());
        alertFinalizar.setCancelable(false);
        alertFinalizar.create().show();


    }


    public void ids() {

        recyclerViewChecks = findViewById(R.id.recyclerChecks);

    }

    public void listItens() {
        CheckDAO checkDAO = new CheckDAO(getApplicationContext());
        listChecks = (ArrayList<Checks>) checkDAO.list();

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