package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Reunion;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreationReunion extends AppCompatActivity {

    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.datePickerButton) Button dateButton;
    @BindView(R.id.timePickerButton) Button timeButton;
    @BindView(R.id.create) Button create;
    @BindView(R.id.sujet) EditText sujet;
    @BindView(R.id.participants) TextView participants;

    private DatePickerDialog datePickerDialog;
    int hour, minute, jour, mois, annee;
    boolean[] selectedGuest;
    private String GuestList = new String();
    final String[] GuestArray = Repository.getGuestArray();;
    private ArrayList<Integer> GuestListIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_reunion);
        ButterKnife.bind(this);

        create.setEnabled(false);
        GuestListIndex = new ArrayList<>();

        sujet.addTextChangedListener(watcher);
        timeButton.addTextChangedListener(watcher);
        dateButton.addTextChangedListener(watcher);
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}

        @Override
        public void afterTextChanged(Editable s) {
            if (sujet.getText().toString().length() > 0 && mois > 0 && hour != 0) {
                create.setEnabled(true);
            } else {
                create.setEnabled(false);
            }
        }
    };

    public void PickGuest(View view) {
        selectedGuest = new boolean[GuestArray.length];
        participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                  CreationReunion.this
                );

                builder.setCancelable(false);

                builder.setMultiChoiceItems(GuestArray, selectedGuest, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            GuestListIndex.add(i);
                            Collections.sort(GuestListIndex);

                        }else{
                            for(int x=0 ; x < GuestListIndex.size(); x++){
                                if(GuestListIndex.get(x) == i){
                                    GuestListIndex.remove(x);
                                }
                            }
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GuestList = Repository.getStringGuestList(GuestListIndex);
                        participants.setText(GuestList);
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
        });
    }

    public void openDatePicker(View view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                jour = day ;
                mois = month ;
                annee = year ;
                dateButton.setText(date);
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

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        return "JAN";
    }

    public void openTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,minute,true);
        timePickerDialog = new TimePickerDialog(this,onTimeSetListener,10,30,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    @OnClick(R.id.create)
    void create(){
        Reunion reunion = new Reunion(sujet.getText().toString(),spinner.getSelectedItem().toString(),jour,mois,annee,hour,minute,GuestList);
        Repository.setMainList(reunion);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}