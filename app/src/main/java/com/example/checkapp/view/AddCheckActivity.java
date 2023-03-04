package com.example.checkapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.checkapp.R;
import com.example.checkapp.helper.CheckDAO;


import com.example.checkapp.helper.broadcast_receiver;
import com.example.checkapp.model.Checks;

import java.util.Calendar;

public class AddCheckActivity extends AppCompatActivity {

    private EditText editAddCheck, editDate, editDescrition, editTime;
    private ImageView imageDate, imageTime;
    private Checks checkAtual;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_check);
        ids();
        date();
        time();

        notification_cannel();
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(this, broadcast_receiver.class),PendingIntent.FLAG_IMMUTABLE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        //Recuperar tarefa
        checkAtual = (Checks) getIntent().getSerializableExtra("checkSelecionada");

        if (checkAtual != null) {

            editAddCheck.setText(checkAtual.getTextTitle());
            editDescrition.setText(checkAtual.getTextDescription());
            editDate.setText(checkAtual.getTextDate());
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_check, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemFinalizar:
                saveCheck();
                set_notification_alarm( 60 * 60 * 1000);
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    public void set_notification_alarm(long interval){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);

        if (Build.VERSION.SDK_INT >= 23){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),interval,pendingIntent);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, interval,pendingIntent);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, interval,pendingIntent);
        }else{
            alarmManager.set(AlarmManager.RTC_WAKEUP, interval,pendingIntent);
        }
    }

    public void cancel_notification_alarm(){
        alarmManager.cancel(pendingIntent);
    }

    private void notification_cannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Reminder";
            String description = "Reminder Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    public void saveCheck() {

        String checkName = editAddCheck.getText().toString();
        String checkDescription = editDescrition.getText().toString();
        String checkData = editDate.getText().toString();

        CheckDAO checkDAO = new CheckDAO(getApplicationContext());


        if (checkAtual != null) {

            if (!checkName.isEmpty()) {
                if (!checkDescription.isEmpty()) {
                    if (!checkData.isEmpty()) {
                        Checks checks = new Checks();
                        checks.setTextTitle(checkName);
                        checks.setTextDescription(checkDescription);
                        checks.setTextDate(checkData);
                        checks.setId(checkAtual.getId());

                        if (checkDAO.update(checks)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao Atualizar Tarefa", Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                            Toast.makeText(getApplicationContext(), "Erro ao Atualizar Tarefa", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }


        } else {

            if (!checkName.isEmpty()) {
                if (!checkDescription.isEmpty()) {
                    if (!checkData.isEmpty()) {
                        Checks checks = new Checks();
                        checks.setTextTitle(checkName);
                        checks.setTextDescription(checkDescription);
                        checks.setTextDate(checkData);
                        if (checkDAO.save(checks)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar Tarefa", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar Tarefa", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(AddCheckActivity.this, "Selecione a data!", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(AddCheckActivity.this, "Digite a Descrição!", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(AddCheckActivity.this, "Digite o Titulo da tarefa!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void time() {


        imageTime.setOnClickListener(view -> {


            TimePickerDialog dialog = new TimePickerDialog(AddCheckActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                    editTime.setText(hour + ":" + minute);


                }
            }, 15, 0, true);
            dialog.show();
        });


    }

    public void date() {

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        imageDate.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(AddCheckActivity.this, (datePicker, year1, month1, dayOfMonth) -> {

                month1 = month1 + 1;
                String date = dayOfMonth + "/" + month1 + "/" + year1;
                editDate.setText(date);


            }, year, month, day);
            dialog.show();
        });


    }

    public void ids() {

        editAddCheck = findViewById(R.id.editAddCheck);
        editDate = findViewById(R.id.editDate);
        editDescrition = findViewById(R.id.editDescription);
        imageDate = findViewById(R.id.imageDate);
        imageTime = findViewById(R.id.imageTime);
        editTime = findViewById(R.id.editTime);


    }


}