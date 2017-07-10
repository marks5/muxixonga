package br.com.gabriel.muxixi.androidTestMock;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.KeyEvent;
import android.view.View;

import org.hamcrest.Matcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
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
        onView(isRoot()).perform(waitFor(3000));
        onView(withId(R.id.rv_fruits))
                .check(matches(isDisplayed()));
    }

    @Test
    public void fruitsActivitySwap(){
        onView(isRoot()).perform(waitFor(5000));
        onView(allOf(withId(R.id.cv_item),hasDescendant(withText("BANANA")))).check(matches(isDisplayed()));
    }

    //util
    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }

}
