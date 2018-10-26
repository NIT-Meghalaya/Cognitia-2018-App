package in.cognitia.cognitia18;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.palette.graphics.Palette;
import androidx.appcompat.widget.Toolbar;

import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class EventDetailActivity extends AppCompatActivity {

    public static final String IMAGE_ID = "image_id_extra";
    public static final String EVENT_NAME = "event_name_extra";
    public static final String DESCRIPTION = "event_description";
    public static final String AIM = "aim";
    public static final String OBJECTIVE = "objective";
    public static final String RULES = "rules";
    public static final String ROBOT_SPECS = "robotSpecs";
    public static final String PARENT = "parent";

    private ImageView imageView;
    private Bundle eventBundle;
    private ViewPager viewPager;
    private int imageResId;
    private CognitiaEventPagerAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        eventBundle = intent.getExtras();

        CharSequence eventName = eventBundle.getString(EVENT_NAME);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(eventName);

        imageResId = intent.getIntExtra(IMAGE_ID, 0);
        imageView = findViewById(R.id.description_image);
        Glide.with(this).load(imageResId).into(imageView);

        setAccentColors();

        viewPager = findViewById(R.id.event_viewpager);
        if (eventBundle.getString(ROBOT_SPECS) != null)
            adapter = new CognitiaEventPagerAdapter(this, eventBundle, CognitiaEventPagerAdapter.EVENT_TECHNICAL);
        else
            adapter = new CognitiaEventPagerAdapter(this, eventBundle, CognitiaEventPagerAdapter.EVENT_NON_TECHNICAL);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.event_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    //Using palette API to extract colors from the image
    private void setAccentColors() {
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResId);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {

                    int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimary);
                    collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                    collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
                }
            });

        } catch (Exception e) {
            // if Bitmap fetch fails, fallback to primary colors
            collapsingToolbarLayout.setContentScrimColor(
                    ContextCompat.getColor(this, R.color.colorPrimary)
            );
            collapsingToolbarLayout.setStatusBarScrimColor(
                    ContextCompat.getColor(this, R.color.colorPrimary)
            );
        }
    }
}