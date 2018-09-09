package in.cognitia.cognitia18;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class MainActivity extends Activity {

    private CoordinatorTabLayout coordinatorTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();

        coordinatorTabLayout = findViewById(
                R.id.coordinatortablayout);
        coordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle(getString(R.string.app_name))
                .setBackEnable(true);
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.vp);
        viewPager.setOffscreenPageLimit(3);
        //viewPager.setAdapter();
    }

    public class EventsViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
}
