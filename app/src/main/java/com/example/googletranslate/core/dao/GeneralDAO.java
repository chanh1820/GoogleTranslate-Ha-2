package com.example.googletranslate.core.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.googletranslate.core.DBHelper;
import com.example.googletranslate.core.dto.VocabularyDTO;

import java.util.ArrayList;
import java.util.List;


public class GeneralDAO {

    DBHelper dbHelper;

    public GeneralDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<VocabularyDTO> findVocabularyByUnit(Integer unitId) {
        List<VocabularyDTO> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM vocabulary_tbl WHERE unit_id = '"+ unitId +"' LIMIT 40";
        Log.e("sql", sql);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        } else {
            return null;
        }
        if (cursor.getCount() != 0) {
            do {
                VocabularyDTO item = new VocabularyDTO();
                item.setId(cursor.getInt(0));
                item.setVocabularyViet(cursor.getString(1));
                item.setVocabularyEng(cursor.getString(2));
                item.setSpell(cursor.getString(3));
                item.setWordType(cursor.getString(4));
                item.setExampleViet(cursor.getString(5));
                item.setExampleEng(cursor.getString(6));
                item.setImageFile(cursor.getString(7));
                item.setSoundFile(cursor.getString(8));
                item.setUnitId(cursor.getInt(9));
                result.add(item);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<VocabularyDTO> findALlVocabulary() {
        List<VocabularyDTO> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM vocabulary_tbl where vocabulary_viet not null";
        Log.e("sql", sql);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        } else {
            return null;
        }
        if (cursor.getCount() != 0) {
            do {
                VocabularyDTO item = new VocabularyDTO();
                item.setId(cursor.getInt(0));
                item.setVocabularyViet(cursor.getString(1));
                item.setVocabularyEng(cursor.getString(2));
                item.setSpell(cursor.getString(3));
                item.setWordType(cursor.getString(4));
                item.setExampleViet(cursor.getString(5));
                item.setExampleEng(cursor.getString(6));
                item.setImageFile(cursor.getString(7));
                item.setSoundFile(cursor.getString(8));
                item.setUnitId(cursor.getInt(9));
                result.add(item);
            } while (cursor.moveToNext());
        }
        return result;
    }
}

