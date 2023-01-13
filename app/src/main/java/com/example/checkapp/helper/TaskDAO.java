package com.example.checkapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.checkapp.model.Checks;
import com.example.checkapp.view.ITaskDAO;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TaskDAO(Context context) {

        DbHelper db = new DbHelper(context);

        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public Boolean save(Checks checks) {

        ContentValues cv = new ContentValues();
        cv.put("name", checks.getTextTitleChecks());


        try {

            write.insert(DbHelper.TASK_TABLE, null, cv);
            Log.i("INFO", "Tarefa salva com sucesso" );

        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Boolean update(Checks checks) {
        return null;
    }

    @Override
    public Boolean delete(Checks checks) {
        return null;
    }

    @Override
    public List<Checks> list() {

        List<Checks> tasks = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TASK_TABLE + ";";

        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()){

            Checks checks = new Checks();


            Long id = c.getLong(c.getColumnIndex("id"));
            String taskName = c.getString(c.getColumnIndex("name"));


            checks.setId(id);
            checks.setTextTitleChecks(taskName);

            tasks.add(checks);
        }

        return tasks;
    }
}
