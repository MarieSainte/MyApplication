package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;

import com.example.myapplication.Model.Reunion;
import com.example.myapplication.Controler.Repository;
import com.example.myapplication.UI.CreationReunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.addReunion) View addReunion;


    MyAdapter myAdapter;
    DatePickerDialog datePickerDialog;
    String lieu = new String();

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

    @OnClick(R.id.addReunion)
    void create(){
        Intent intent = new Intent(this, CreationReunion.class);
        startActivity(intent);
    }

    public void reset(MenuItem item) {
        init(Repository.getMainList());
        Toast.makeText(MainActivity.this, Repository.getMainList().get(0).getGuest(),Toast.LENGTH_LONG).show();
    }

    public void roomFilter(MenuItem item) {
        String [] rooms = Repository.getRooms();

        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this
        );

        builder.setTitle("Room filter");
        builder.setCancelable(false);

        builder.setSingleChoiceItems(rooms, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                lieu = rooms[i];
                Repository.setFilterList(lieu);

            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Repository.setFilterList(lieu);

                init(Repository.getFilterList());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    public void dateFilter(MenuItem item) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                int jour = day;
                int mois = month ;
                int annee = year ;
                Repository.setFilterList(jour,mois,annee);
                init(Repository.getFilterList());
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month,day+1);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}