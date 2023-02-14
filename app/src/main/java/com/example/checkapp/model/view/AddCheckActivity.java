package com.example.checkapp.model.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.checkapp.R;
import com.example.checkapp.helper.CheckDAO;
import com.example.checkapp.model.Checks;
import com.google.android.material.internal.CheckableGroup;

import java.util.Calendar;

public class AddCheckActivity extends AppCompatActivity {

    private EditText editAddCheck, editDate, editDescrition;
    private ImageView imageDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_check);
        ids();
        date();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_check, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemSalvar:

                saveCheck();
                Toast.makeText(AddCheckActivity.this, "Tarefa Salva", Toast.LENGTH_SHORT).show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void saveCheck() {

        String checkName = editAddCheck.getText().toString();
        String checkDescription = editDescrition.getText().toString();
        String checkData = editDate.getText().toString();

        CheckDAO checkDAO = new CheckDAO(getApplicationContext());


        if (!checkName.isEmpty()) {
            if (!checkDescription.isEmpty()) {
                if (checkData.isEmpty()) {
                    Checks checks = new Checks();
                    checks.setTextTitle(checkName);
                    checks.setTextDescription(checkDescription);
                    checks.setTextDate(checkData);
                    checkDAO.save(checks);
                    finish();

                } else {
                    Toast.makeText(AddCheckActivity.this, "Selecione a data", Toast.LENGTH_SHORT).show();
                }

            } else {

                Toast.makeText(AddCheckActivity.this, "Digite a Descrição", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(AddCheckActivity.this, "Digite o nome!", Toast.LENGTH_SHORT).show();
        }


    }

    public void date() {

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        imageDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddCheckActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        editDate.setText(date);


                    }
                }, year, month, day);
                dialog.show();
            }
        });


    }

    public void ids() {

        editAddCheck = findViewById(R.id.editAddCheck);
        editDate = findViewById(R.id.editDate);
        editDescrition = findViewById(R.id.editDescription);
        imageDate = findViewById(R.id.imageDate);


    }
}