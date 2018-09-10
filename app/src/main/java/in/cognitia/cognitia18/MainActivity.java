package in.cognitia.cognitia18;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class MainActivity extends AppCompatActivity {

    private CoordinatorTabLayout coordinatorTabLayout;
    private ViewPager viewPager;
    private String[] nameArray;
    private int[] imageArray;
    private int[] colorArray;
    private ArrayList<EventCategory> eventCategories;
    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventCategories = new ArrayList<>();
        eventCategories.add(new EventCategory(getString(R.string.robotics), R.drawable.ic_food, android.R.color.holo_blue_bright));
        eventCategories.add(new EventCategory(getString(R.string.departmental), R.drawable.ic_discount, android.R.color.holo_purple));
        eventCategories.add(new EventCategory(getString(R.string.others), R.drawable.ic_movie, android.R.color.holo_red_light));

        nameArray = createNameArray(eventCategories);
        imageArray = createImageArray(eventCategories);
        colorArray = createColorArray(eventCategories);

        initFragments();
        initViewPager();

        coordinatorTabLayout = findViewById(R.id.coordinatortablayout);
        coordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle(getString(R.string.app_name))
                .setBackEnable(true)
                .setImageArray(imageArray, colorArray)
                .setupWithViewPager(viewPager);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (String title : nameArray) {
            fragments.add(EventsCategoryFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.vp);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new EventsViewPagerAdapter(getSupportFragmentManager(), fragments, nameArray));
    }

    private String[] createNameArray(ArrayList<EventCategory> eventCategories) {
        String[] nameArray = new String[eventCategories.size()];

        for (int i = 0; i < eventCategories.size(); i++) {
            nameArray[i] = eventCategories.get(i).getName();
        }

        return nameArray;
    }

    private int[] createImageArray(ArrayList<EventCategory> eventCategories) {
        int[] imageArray = new int[eventCategories.size()];

        for (int i = 0; i < eventCategories.size(); i++) {
            imageArray[i] = eventCategories.get(i).getImageResId();
        }

        return imageArray;
    }

    private int[] createColorArray(ArrayList<EventCategory> eventCategories) {
        int[] colorArray = new int[eventCategories.size()];

        for (int i = 0; i < eventCategories.size(); i++) {
            colorArray[i] = eventCategories.get(i).getColorResId();
        }

        return colorArray;
    }
}
