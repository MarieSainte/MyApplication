package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.Model.Reunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Calendar startMeeting = Calendar.getInstance();
    Reunion reunion = new Reunion("Sujet", "Paris", startMeeting,startMeeting,"guestList");

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void setMainListWithSuccess() {
        assertFalse(Repository.getMainList().contains(reunion));
        Repository.setMainList(reunion);
        assertTrue(Repository.getMainList().contains(reunion));
    }
    @Test
    public void getMainListWithSuccess() {
        List<Reunion> list= new ArrayList<>();
        Repository.setMainList(reunion);
        list = Repository.getMainList();
        assertEquals(list, Repository.getMainList());
    }
    @Test
    public void deleteReunionWithSuccess() {
        Repository.setMainList(reunion);
        assertTrue(Repository.getMainList().contains(reunion));
        Repository.deleteReunion(reunion);
        assertFalse(Repository.getMainList().contains(reunion));
    }
    @Test
    public void getGuestArrayWithSuccess() {
        String[] list;
        list = Repository.getGuestArray();
        assertEquals(list[0],Repository.getGuestArray()[0]);
    }
    @Test
    public void getFilterListWithSuccess(){
        Repository.setMainList(reunion);

    }
    @Test
    public void setFilterListWithRoom(){
        Repository.setMainList(reunion);
        assertFalse(Repository.getFilterList().contains(reunion));
        Repository.setFilterList("Paris");
        assertTrue(Repository.getFilterList().contains(reunion));
        Repository.setFilterList("London");
        assertFalse(Repository.getFilterList().contains(reunion));
    }
    @Test
    public void setFilterListWithDate(){
        Repository.setMainList(reunion);
        Repository.setFilterList(25,11,2021);
        assertTrue(Repository.getFilterList().contains(reunion));
        Repository.setFilterList(4,4,2020);
        assertFalse(Repository.getFilterList().contains(reunion));
    }
    @Test
    public void getRoomsWithSuccess() {
        String[] list;
        list = Repository.getRooms();
        assertEquals(list[0],"Paris");
    }

    @Test
    public void getStringGuestListWithSuccess() {
        ArrayList<Integer> GuestIndexTest = new ArrayList<>();
        GuestIndexTest.add(0);
        assertTrue(Repository.getStringGuestList(GuestIndexTest).contains("John@gmail.com"));
    }



}