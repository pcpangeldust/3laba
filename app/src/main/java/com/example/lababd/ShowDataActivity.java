package com.example.lababd;
import java.util.List;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowDataActivity extends AppCompatActivity {

    private TextView dataTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        dataTextView = findViewById(R.id.dataTextView);
        databaseHelper = new DatabaseHelper(this);

        // Получаем данные из базы данных
        List<Odnogruppnik> odnogruppnikiList = databaseHelper.getAllOdnogruppniki();

        // Отображаем количество записей после получения данных из базы
        Log.d("ShowDataActivity", "Number of records after getAllOdnogruppniki(): " + odnogruppnikiList.size());

        // Выводим количество записей в Log
        Log.d("ShowDataActivity", "Number of records to display: " + odnogruppnikiList.size());

        // Отображаем данные
        displayData(odnogruppnikiList);

        Button btnAddData = findViewById(R.id.btnAddData);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызываем метод добавления данных в базу (по аналогии с deleteAllData())
                addData();
            }
        });
    }

    private void displayData(List<Odnogruppnik> odnogruppnikiList) {
        if (odnogruppnikiList.isEmpty()) {
            Log.d("ShowDataActivity", "No data in the list.");
            dataTextView.setText("No data available");
        } else {
            // Если у вас есть способ отображения данных (например, через RecyclerView),
            // добавьте соответствующий код здесь

            // Пример вывода данных в TextView
            StringBuilder dataStringBuilder = new StringBuilder();
            for (Odnogruppnik odnogruppnik : odnogruppnikiList) {
                dataStringBuilder.append("ID: ").append(odnogruppnik.getId())
                        .append(", FullName: ").append(odnogruppnik.getFullName())
                        .append(", AddingTime: ").append(odnogruppnik.getAddingTime())
                        .append("\n");
            }
            dataTextView.setText(dataStringBuilder.toString());
        }
    }

    private void addData() {
        // Вызываем метод добавления данных в базу
        databaseHelper.insertSampleData();

        // После добавления данных вызываем метод отображения
        List<Odnogruppnik> odnogruppnikiList = databaseHelper.getAllOdnogruppniki();
        displayData(odnogruppnikiList);
    }
}