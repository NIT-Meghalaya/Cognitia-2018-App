package in.cognitia.cognitia18;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class TeamGalleryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_gallery);

        Toolbar toolbar = findViewById(R.id.team_gallery_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        actionBar.setTitle(CognitiaTeamMember.TECHNICAL);

        drawerLayout = findViewById(R.id.team_drawer_layout);

        NavigationView navigationView = findViewById(R.id.team_nav_view);
        navigationView.setCheckedItem(R.id.team_technical);

        RecyclerView recyclerView = findViewById(R.id.rv_team_images);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        int activityId = NavigationViewHelper.TEAM_GALLERY_ACTIVITY;

        if (getIntent().getExtras().getInt(NavigationViewHelper.SPONSORS_INTENT) == NavigationViewHelper.SPONSORS_GALLERY) {
            TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(
                    TeamMembersArrayInitializer.getTeamMembers(CognitiaTeamMember.SPONSORS), this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            //Required so as to open team gallery from sponsors view
            activityId = NavigationViewHelper.SPONSORS_GALLERY;
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_sponsors);
        } else {
            TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(
                    TeamMembersArrayInitializer.getTeamMembers(CognitiaTeamMember.TECHNICAL), this);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new TeamGalleryRecyclerViewAdapter.GridSpacingItemDecoration());
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
}
