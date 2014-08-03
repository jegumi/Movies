package com.discogs.tests;

import android.view.View;

import com.discogs.RobolectricGradleTestRunner;
import com.jegumi.movies.R;
import com.jegumi.movies.ui.ListMoviesActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
public class MyAndroidClassTest {

    @Test
    public void testWhenActivityCreatedHelloTextViewIsVisible() throws Exception {
        ListMoviesActivity activity = new ListMoviesActivity();

        ActivityController.of(activity).attach().create();

        boolean isTablet = activity.isTablet();
        View left = activity.findViewById(R.id.left_layout);
        assertTrue(isTablet ? left != null : left == null);
    }
}
