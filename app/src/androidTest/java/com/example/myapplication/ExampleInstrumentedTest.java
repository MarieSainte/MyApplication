package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.TypeTextAction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.endsWithIgnoringCase;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.Model.ActiveDirectory;
import com.example.myapplication.Model.Reunion;
import com.example.myapplication.Model.ReunionList;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);


    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myapplication", appContext.getPackageName());
    }

    @Test
    public void StartCreationReunion() {

        // Click on the Add button in order to create a meeting
        onView(withId(R.id.addReunion)).perform(click());

        //check if the creation layout has been displayed
        onView(allOf(withId(R.id.titre), isDisplayed()));

        //Fill the editText "sujet"
        onView(allOf(withId(R.id.sujet)))
                .perform(click(), typeText("Test"));

        //Set a date
        onView(allOf(withId(R.id.spinner)))
                .perform(click());

        onView(allOf(withText(equalToIgnoringCase("London"))))
                .perform(click());

        //Set a date
        onView(allOf(withId(R.id.datePickerButton)))
                .perform(click());

        onView(allOf(withText(equalToIgnoringCase("Ok"))))
                .perform(click());

        //Set a time
        onView(allOf(withId(R.id.timePickerButton)))
                .perform(click());
        onView(allOf(withText(equalToIgnoringCase("Ok"))))
                .perform(click());

        // Click on the add button to create a meeting
        onView(withId(R.id.create)).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));

        testFilterRoom();
    }

    @Test
    public void testFilterRoom(){
        //TEST Filter
        onView(allOf(withId(R.id.action_filter)))
                .perform(click());
        onView(allOf(withText(equalToIgnoringCase("Room"))))
                .perform(click());
        onView(allOf(withText(equalToIgnoringCase("London"))))
                .perform(click());
        onView(allOf(withText(equalToIgnoringCase("Ok"))))
                .perform(click());

        onView(allOf(withText("Test - 10h30 - London"), isDisplayed()));
    }
}