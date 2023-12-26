package com.example.lababd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bazaepta.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE Odnogruppniki (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FullName VARCHAR," +
                "AddingTime TIME);";

        db.execSQL(createTableQuery);

        // Добавляем несколько записей для проверки
        insertSampleData(db);
    }

    public void insertSampleData() {
        SQLiteDatabase db = this.getWritableDatabase();
        insertSampleData(db);
        db.close();
    }

    private void insertSampleData(SQLiteDatabase db) {
        String insertQuery = "INSERT INTO Odnogruppniki (FullName, AddingTime) VALUES " +
                "('Ivan Zhikharev', '12:00:00'), " +
                "('Viper Lipstick', '13:30:00'), " +
                "('Lionel Messi', '15:45:00');";

        try {
            db.execSQL(insertQuery);
            Log.d("DatabaseHelper", "Sample data inserted successfully");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting sample data: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Здесь вы можете обновлять структуру базы данных, если это необходимо
    }

    public List<Odnogruppnik> getAllOdnogruppniki() {
        List<Odnogruppnik> odnogruppnikiList = new ArrayList<>();

        // Выборка всех записей из таблицы Odnogruppniki
        String selectQuery = "SELECT * FROM Odnogruppniki";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Выводим количество записей в Log
        Log.d("DatabaseHelper", "Number of records: " + cursor.getCount());

        // Перебор результатов запроса и добавление их в список
        while (cursor.moveToNext()) {
            Odnogruppnik odnogruppnik = new Odnogruppnik();

            // Получение индексов столбцов
            int idColumnIndex = cursor.getColumnIndex("id");
            int fullNameColumnIndex = cursor.getColumnIndex("FullName");
            int addingTimeColumnIndex = cursor.getColumnIndex("AddingTime");

            // Убедимся, что индексы столбцов существуют
            if (idColumnIndex != -1) {
                odnogruppnik.setId(cursor.getInt(idColumnIndex));
            }

            if (fullNameColumnIndex != -1) {
                odnogruppnik.setFullName(cursor.getString(fullNameColumnIndex));
            }

            if (addingTimeColumnIndex != -1) {
                odnogruppnik.setAddingTime(cursor.getString(addingTimeColumnIndex));
            }

            odnogruppnikiList.add(odnogruppnik);
        }

        // Выводим количество записей, полученных из базы данных, в лог
        Log.d("DatabaseHelper", "Number of records in getAllOdnogruppniki: " + odnogruppnikiList.size());

        // Закрываем курсор и базу данных
        cursor.close();
        db.close();

        return odnogruppnikiList;
    }

    public void deleteAllOdnogruppniki() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Odnogruppniki", null, null);
        db.close();
    }
}