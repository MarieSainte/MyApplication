package com.example.myapplication.Model;

import static com.example.myapplication.Model.ActiveDirectory.GuestArray;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.UI.CreationReunion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reunion {

    /**
     * Declaration
     * */
    private String sujet;
    private String Lieu;
    private String Guests;

    private int day ;
    private int month ;
    private int year ;
    private int hour ;
    private int minute ;

    /**
     * Constructeur
     * */
    public Reunion(String sujet, String lieu,int day,int month, int year , int hour, int minute,String GuestList) {
        this.sujet = sujet;
        this.Lieu = lieu;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.Guests = GuestList;
    }

    public String getSujet() {
        return sujet;
    }

    public String getLieu() { return Lieu; }

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }

    public int getHour() { return hour; }

    public int getMinute() { return minute; }

    public String getGuest() { return Guests; }

    public static String getStringGuestList(ArrayList<Integer> GuestListIndex){
        StringBuilder stringBuilder = new StringBuilder();

        for (int j=0; j<GuestListIndex.size();j++){
            stringBuilder.append(GuestArray[GuestListIndex.get(j)]);
            if(j!=GuestListIndex.size()-1){
                stringBuilder.append("; ");
            }
        }

        return stringBuilder.toString();
    }

}
