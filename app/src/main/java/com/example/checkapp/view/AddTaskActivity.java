package com.example.checkapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkapp.R;
import com.example.checkapp.helper.DbHelper;
import com.example.checkapp.helper.TaskDAO;
import com.example.checkapp.model.Checks;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editAddTask, editDescriptionTask;
    private Button btnSaveTask;
    private String addedTask, taskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ids();


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
                Toast.makeText(AddTaskActivity.this, "Tarefa Salva", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveTask(){

        TaskDAO taskDAO = new TaskDAO(getApplicationContext());

        Checks checks = new Checks();
        checks.setTextDescriptionChecks("Teste");
        checks.setTextTitleChecks("Tarefa Teste");

        taskDAO.save(checks);

    }

    public void ids() {

        editAddTask = findViewById(R.id.editAddTask);
        editDescriptionTask = findViewById(R.id.editDescriptionTask);
        btnSaveTask = findViewById(R.id.buttonSaveTask);

    }
}