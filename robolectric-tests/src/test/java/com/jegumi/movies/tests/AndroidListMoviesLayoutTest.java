package com.jegumi.movies.tests;

import android.view.View;

import com.jegumi.RobolectricGradleTestRunner;
import com.jegumi.movies.R;
import com.jegumi.movies.ui.ListMoviesActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
public class AndroidListMoviesLayoutTest {

    @Test
    public void testTabletOrMobile() throws Exception {
        ListMoviesActivity activity = new ListMoviesActivity();

        ActivityController.of(activity).attach().create();

        boolean isTablet = activity.isTablet();
        View left = activity.findViewById(R.id.left_layout);
        assertTrue(isTablet ? left != null : left == null);
    }
}
