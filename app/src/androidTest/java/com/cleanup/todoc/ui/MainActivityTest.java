package com.cleanup.todoc.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.cleanup.todoc.R;
import com.cleanup.todoc.database.TodocDatabase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Before
    public void init() {
        TodocDatabase database = TodocDatabase.getInstance(ApplicationProvider.getApplicationContext());
        database.taskDao().deleteAllTasks();

    }

    @Test
    public void AddAndDeleteTaskWithSuccess() {

        ViewInteraction floatingActionButton = onView((withId(R.id.fab_add_task)));

        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.txt_task_name));
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText(R.string.add),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.lbl_task_name), withText("Test"),
                        isDisplayed()));

        textView.check(matches(withText("Test")));


        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.img_delete),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_tasks),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());


    }

}
