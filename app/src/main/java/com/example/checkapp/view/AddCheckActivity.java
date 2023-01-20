package com.example.checkapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkapp.R;
import com.example.checkapp.helper.CheckDAO;
import com.example.checkapp.model.Checks;

public class AddCheckActivity extends AppCompatActivity {

    private EditText editAddCheck;
    private Button btnSaveCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_check);
        ids();


        btnSaveCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCheck();
                Toast.makeText(AddCheckActivity.this, "Tarefa Salva", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void saveCheck() {

        String checkName = editAddCheck.getText().toString();

        CheckDAO checkDAO = new CheckDAO(getApplicationContext());


        if (!checkName.isEmpty()) {
            Checks checks = new Checks();
            checks.setTextTitleChecks(checkName);
            checkDAO.save(checks);
            finish();
        }


    }

    public void ids() {

        editAddCheck = findViewById(R.id.editAddCheck);
        btnSaveCheck = findViewById(R.id.buttonSaveCheck);

    }
}