package in.cognitia.cognitia18;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;

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
    private DrawerLayout drawerLayout;

    //This will help to prevent the call to setPersistenceEnabled more than once
    private static boolean initialCall = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (initialCall) {
            //setPersistenceEnabled() needs to be called before any other use of DB
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            initialCall = false;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        drawerLayout = findViewById(R.id.drawer_layout);

        eventCategories = new ArrayList<>();
        eventCategories.add(new EventCategory(getString(R.string.robotics), R.drawable.robotics, android.R.color.holo_blue_bright));
        eventCategories.add(new EventCategory(getString(R.string.departmental), R.drawable.departmental, android.R.color.holo_purple));
        eventCategories.add(new EventCategory(getString(R.string.others), R.drawable.ic_movie, android.R.color.holo_red_light));

        nameArray = createNameArray(eventCategories);
        imageArray = createImageArray(eventCategories);
        colorArray = createColorArray(eventCategories);

        initFragments();
        initViewPager();

        coordinatorTabLayout = findViewById(R.id.coordinatortablayout);
        coordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle(getString(R.string.app_name))
                .setImageArray(imageArray, colorArray)
                .setupWithViewPager(viewPager);

        TabLayout tabLayout = coordinatorTabLayout.getTabLayout();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //Context is needed to convert from resource id to string
        CognitiaTeamMember.context = this;
        CognitiaTeamMember.initializeTeamNameStrings();

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TeamMembersArrayInitializer.addTeamMembers();
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_events);

        NavigationViewHelper navHelper = new NavigationViewHelper(NavigationViewHelper.MAIN_ACTIVITY,
                this, navigationView, drawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setViewPagerPage(final int position) {
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(position, true);
            }
        });
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
