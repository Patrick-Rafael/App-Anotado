package com.example.checkapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.checkapp.model.Checks;
import com.example.checkapp.view.ITaskDAO;

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
        cv.put("name", checks.getTextDescriptionChecks());

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
        return null;
    }
}
