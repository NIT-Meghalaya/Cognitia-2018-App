package in.cognitia.cognitia18;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.WindowManager;


public class TeamGalleryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_gallery);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.team_gallery_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        drawerLayout = findViewById(R.id.team_drawer_layout);

        NavigationView navigationView = findViewById(R.id.team_nav_view);
        navigationView.setCheckedItem(R.id.team_technical);
        navigationView.setItemIconTintList(null);

        RecyclerView recyclerView = findViewById(R.id.rv_team_images);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        int activityId = NavigationViewHelper.TEAM_GALLERY_ACTIVITY;

        if (getIntent().getExtras().getInt(NavigationViewHelper.SPONSORS_INTENT) == NavigationViewHelper.SPONSORS_GALLERY) {
            TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(
                    TeamMembersArrayInitializer.getTeamMembers(CognitiaTeamMember.SPONSORS), this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            actionBar.setTitle(CognitiaTeamMember.SPONSORS);
            //Required so as to open team gallery from sponsors view
            activityId = NavigationViewHelper.SPONSORS_GALLERY;
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_sponsors);
        } else {
            TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(
                    TeamMembersArrayInitializer.getTeamMembers(CognitiaTeamMember.TECHNICAL), this);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new TeamGalleryRecyclerViewAdapter.GridSpacingItemDecoration());
            actionBar.setTitle(CognitiaTeamMember.TECHNICAL);
        }

        NavigationViewHelper navViewHelper = new NavigationViewHelper(activityId,
                this, navigationView, drawerLayout, actionBar, recyclerView);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        return true;
    }*/
}
