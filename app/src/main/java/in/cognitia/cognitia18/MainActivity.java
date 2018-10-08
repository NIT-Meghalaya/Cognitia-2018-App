package in.cognitia.cognitia18;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import in.cognitia.cognitia18.Util.EventsImagesAssociator;
import in.cognitia.cognitia18.EventsCategoryRecyclerViewAdapter.GridSpacingItemDecoration;

import static in.cognitia.cognitia18.CognitiaTeamMember.TECHNICAL;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    //This will help to prevent the call to setPersistenceEnabled more than once
    private static boolean initialCall = true;

    private FirebaseDatabase database;

    //Different names for adapters is required, otherwise there is a problem in fetching data
    private EventsCategoryRecyclerViewAdapter adapterTechnical, adapterDepartmental, adapterOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (initialCall) {
            //setPersistenceEnabled() needs to be called before any other use of DB
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            initialCall = false;
        }

        database = FirebaseDatabase.getInstance();

        Toolbar toolbar = findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        //Context is needed to convert from resource id to string
        CognitiaTeamMember.context = this;
        CognitiaTeamMember.initializeTeamNameStrings();

        EventsImagesAssociator.context = this;
        EventsImagesAssociator.initializeImageMap();

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TeamMembersArrayInitializer.addTeamMembers();
            }
        });

        NavigationView navigationView = findViewById(R.id.event_nav_view);
        navigationView.setCheckedItem(R.id.nav_events);

        //The recycler views are displayed and hidden on the basis of the option selected
        RecyclerView technicalEventsRV = findViewById(R.id.event_technical_rv);
        adapterTechnical = new EventsCategoryRecyclerViewAdapter(getTechnicalEventsData(), this);
        setUpRecyclerView(technicalEventsRV, adapterTechnical);

        RecyclerView departmentalEventsRV = findViewById(R.id.event_departmental_rv);
        adapterDepartmental = new EventsCategoryRecyclerViewAdapter(getDepartmentalEventsData(), this);
        setUpRecyclerView(departmentalEventsRV, adapterDepartmental);

        RecyclerView otherEventsRV = findViewById(R.id.event_others_rv);
        adapterOthers = new EventsCategoryRecyclerViewAdapter(getOtherEventsData(), this);
        setUpRecyclerView(otherEventsRV, adapterOthers);


        drawerLayout = findViewById(R.id.event_drawer_layout);
        NavigationViewHelper navHelper = new NavigationViewHelper(NavigationViewHelper.MAIN_ACTIVITY,
                this, navigationView, drawerLayout, actionBar, technicalEventsRV, departmentalEventsRV, otherEventsRV);
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

    @Override
    protected void onStart() {
        super.onStart();
        adapterTechnical.startListening();
        adapterDepartmental.startListening();
        adapterOthers.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterTechnical.stopListening();
        adapterDepartmental.stopListening();
        adapterOthers.stopListening();
    }

    private void setUpRecyclerView(RecyclerView recyclerView, EventsCategoryRecyclerViewAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getTechnicalEventsData() {
        Query query = database.getReference().child("events").child("robotics");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                        setQuery(query, CognitiaEvent.class).
                        build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getDepartmentalEventsData() {
        Query query = database.getReference().child("events").child("departmental");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                        setQuery(query, CognitiaEvent.class).
                        build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getOtherEventsData() {
        Query query = database.getReference().child("events").child("others");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                        setQuery(query, CognitiaEvent.class).
                        build();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
