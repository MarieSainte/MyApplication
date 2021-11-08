package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.Model.Reunion;
import com.example.myapplication.UI.CreationReunion;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.addReunion) View addReunion;


    MyAdapter myAdapter;
    DatePickerDialog datePickerDialog;
    String lieu = "Paris";
    private final Calendar today = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        init(Repository.getMainList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void init(List<Reunion> list){
        myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.addReunion)
    void create(){
        Intent intent = new Intent(this, CreationReunion.class);
        startActivity(intent);
    }

    public void reset(MenuItem item) {
        init(Repository.getMainList());
    }

    public void roomFilter(MenuItem item) {
        String [] rooms = Repository.getRooms();

        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this
        );

        builder.setTitle("Room filter");
        builder.setCancelable(false);

        builder.setSingleChoiceItems(rooms, 0, (dialogInterface, i) -> lieu = rooms[i]);

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            Repository.setFilterList(lieu);

            init(Repository.getFilterList());
        });

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        builder.show();
    }

    public void dateFilter(MenuItem item) {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {

            Repository.setFilterList(day,month + 1,year);
            init(Repository.getFilterList());
        };

        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month,day+1);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}