package com.example.myapplication.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Reunion;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreationReunion extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spinner) Spinner spinner;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.datePickerButton) Button dateButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.timePickerButton) Button timeButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.create) Button create;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sujet) EditText sujet;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.participants) TextView participants;

    boolean[] selectedGuest;
    private String GuestList = "";
    final String[] GuestArray = Repository.getGuestArray();
    private ArrayList<Integer> GuestListIndex;

    private final Calendar startMeeting = Calendar.getInstance();
    private final Calendar endMeeting = Calendar.getInstance();
    private final Calendar today = Calendar.getInstance();

    boolean timeWasSelected , dateWasSelected = false;

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
        {create.setEnabled(sujet.getText().toString().length() > 0 && timeWasSelected && dateWasSelected);}

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void PickGuest(View view) {
        selectedGuest = new boolean[GuestArray.length];
        participants.setOnClickListener(view1 -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(
              CreationReunion.this
            );

            builder.setCancelable(false);

            builder.setMultiChoiceItems(GuestArray, selectedGuest, (dialogInterface, i, b) -> {
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
            });

            builder.setPositiveButton("Ok", (dialogInterface, i) -> {
                GuestList = Repository.getStringGuestList(GuestListIndex);
                participants.setText(GuestList);
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

            builder.show();
        });
    }

    public void openDatePicker(View view) {
        @SuppressLint("SetTextI18n") DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            dateWasSelected = true ;
            startMeeting.set(Calendar.DATE, day) ;
            startMeeting.set(Calendar.MONTH, month) ;
            startMeeting.set(Calendar.YEAR, year) ;

            dateButton.setText(day + " " + month + " " + year);
        };

        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day + 1);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void openTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            startMeeting.set(Calendar.HOUR_OF_DAY, selectedHour) ;
            startMeeting.set(Calendar.MINUTE, selectedMinute) ;
            startMeeting.set(Calendar.SECOND, 0) ;
            timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,10,30,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.create)
    void create(){
        endMeeting.set(startMeeting.get(Calendar.YEAR),startMeeting.get(Calendar.MONTH),startMeeting.get(Calendar.DATE),startMeeting.get(Calendar.HOUR_OF_DAY),startMeeting.get(Calendar.MINUTE));
        if(Repository.checkRoomAvailability(spinner.getSelectedItem().toString(),startMeeting,endMeeting)){
            Reunion reunion = new Reunion(sujet.getText().toString(),spinner.getSelectedItem().toString(),startMeeting, endMeeting,GuestList);
            Repository.setMainList(reunion);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "The room is not available at this time, Please select an another room or a different time.", Toast.LENGTH_LONG).show();
        }
    }

}