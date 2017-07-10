package br.com.gabriel.muxixi.androidTestMock;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;

import br.com.gabriel.muxixi.R;
import br.com.gabriel.muxixi.data.remote.MockFruitsRestServiceImpl;
import br.com.gabriel.muxixi.presentation.fruits.ListFruitsActivity;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Gabriel on 10/07/2017.
 */

public class ListFruitsActivityTest {

    @Rule
    public ActivityTestRule<ListFruitsActivity> testRule = new ActivityTestRule<>(ListFruitsActivity.class);

    @Test
    public void fruitsActivity_onLaunch_ListItemsDisplayed(){
        onView(withId(R.id.rv_fruits))
                .check(matches(isDisplayed()));
    }

    @Test
    public void fruitsActivitySwap(){
        onView(allOf(withId(R.id.cv_item),withText("BANANA"))).check(matches(isClickable()));
    }

    @Test
    public void searchText_ServiceCallFails_DisplayError(){
        String errorMsg = "Server Error";
        MockFruitsRestServiceImpl.setListFruitsResult(Observable.error(new Exception(errorMsg)));

//        onView(withId(R.id.error_msg))
//                .check(matches(isDisplayed()));

        onView(withText(errorMsg))
                .check(matches(isDisplayed()));

    }

}
