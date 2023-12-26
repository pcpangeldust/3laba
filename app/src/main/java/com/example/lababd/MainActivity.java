package com.example.lababd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button showDataButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDataButton = findViewById(R.id.showDataButton);
        dbHelper = new DatabaseHelper(this);

        // Обработка нажатия на кнопку
        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // При нажатии кнопки открываем новое Activity
                Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
                startActivity(intent);
            }
        });

        Button btnDeleteAll = findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllData();
            }
        });
    }

    private void addData() {
        // Вызываем метод добавления данных в базу (по аналогии с deleteAllData())
        dbHelper.insertSampleData();
        // Обновляем интерфейс, если необходимо
    }

    public void onDeleteAllClick(View view) {
        deleteAllData();
    }

    private void deleteAllData() {
        dbHelper.deleteAllOdnogruppniki();
        Log.d("MainActivity", "All data deleted successfully");
        // Добавьте обновление интерфейса, если необходимо
    }

    private void addDataToDisplay() {
        // Вызываем метод добавления данных в базу
        dbHelper.insertSampleData();

        // После добавления данных вызываем метод отображения
        List<Odnogruppnik> odnogruppnikiList = dbHelper.getAllOdnogruppniki();
        displayData(odnogruppnikiList);
    }

    private void displayData(List<Odnogruppnik> odnogruppnikiList) {
        // Реализуйте отображение данных, например, в TextView
    }
}