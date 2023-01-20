package com.example.checkapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.checkapp.model.Checks;

import java.util.ArrayList;
import java.util.List;

public class CheckDAO implements ICheckDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public CheckDAO(Context context) {

        DbHelper_Check db = new DbHelper_Check(context);

        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public Boolean save(Checks checks) {

        ContentValues cv = new ContentValues();
        cv.put("name", checks.getTextTitleChecks());


        try {

            write.insert(DbHelper_Check.TABLE, null, cv);
            Log.i("INFO", "Check salva com sucesso");

        } catch (Exception e) {
            Log.i("INFO", "Erro ao salvar Check" + e.getMessage());
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

        List<Checks> check = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper_Check.TABLE + ";";

        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {

            Checks checks = new Checks();


            Long id = c.getLong(c.getColumnIndex("id"));
            String CheckName = c.getString(c.getColumnIndex("name"));


            checks.setId(id);
            checks.setTextTitleChecks(CheckName);

            check.add(checks);
        }

        return check;
    }
}