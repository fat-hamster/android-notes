package com.dmgpersonal.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
    Класс добавлен как заготовка для хранения заметок в БД
    Пока для меня сложно разобраться в работе с БД на Android
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "notes.db";
    public static final String TABLE_NOTES = "tNotes";
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_CREATE_DATE = "date";
    public static final String KEY_MODIFY_DATE = "modify_date";
    public static final String KEY_STATUS = "status";
    public static final String KEY_COLOR = "color";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + " (" + KEY_ID + "INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT, " + KEY_BODY + " TEXT, " + KEY_CREATE_DATE + " TEXT,"
                + KEY_MODIFY_DATE + " TEXT," + KEY_STATUS + " TEXT," + KEY_COLOR + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}
