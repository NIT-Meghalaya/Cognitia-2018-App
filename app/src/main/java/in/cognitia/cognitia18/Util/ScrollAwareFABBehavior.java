package in.cognitia.cognitia18.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {


    //Because we are defining this behavior statically within the XML,
    //we must also implement a constructor to enable layout inflation to work correctly.
    //Not implementing this gives 'Could not inflate Behavior subclass' error message

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);

        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
            Log.v("dy consumed: ", "up: " + dyConsumed);
        } else if (dyConsumed <= 0 && child.getVisibility() == View.GONE) {
            child.show();
            Log.v("dy consumed: ", "down: " + dyConsumed);
        }
    }
}
