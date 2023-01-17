package com.example.checkapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkapp.R;
import com.example.checkapp.helper.TaskDAO;
import com.example.checkapp.model.Checks;

public class AddCheckActivity extends AppCompatActivity {

    private EditText editAddTask, editDescriptionTask;
    private Button btnSaveTask;
    private String addedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_check);
        ids();


        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
                Toast.makeText(AddCheckActivity.this, "Tarefa Salva", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void saveTask() {

        String taskName = editAddTask.getText().toString();

        TaskDAO taskDAO = new TaskDAO(getApplicationContext());


        if (!taskName.isEmpty()) {
            Checks checks = new Checks();
            checks.setTextTitleChecks(taskName);
            taskDAO.save(checks);
            finish();
        }


    }

    public void ids() {

        editAddTask = findViewById(R.id.editAddCheck);
        btnSaveTask = findViewById(R.id.buttonSaveCheck);

    }
}