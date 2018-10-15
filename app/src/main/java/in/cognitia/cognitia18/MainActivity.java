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
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import in.cognitia.cognitia18.Util.EventsImagesAssociator;
import in.cognitia.cognitia18.EventsCategoryRecyclerViewAdapter.GridSpacingItemDecoration;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    //This will help to prevent the call to setPersistenceEnabled more than once
    private static boolean initialCall = true;

    private FirebaseDatabase database;

    //Different names for adapters is required, otherwise there is a problem in fetching data
    private EventsCategoryRecyclerViewAdapter adapterTechnical, adapterOthers;
    private EventsCategoryRecyclerViewAdapter adapterCEDepartmental, adapterCSEDepartmental;
    private EventsCategoryRecyclerViewAdapter adapterEEDepartmental, adapterECEDepartmental, adapterMEDepartmental;

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

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        navigationView.setCheckedItem(R.id.events_technical);
        navigationView.setItemIconTintList(null);
        navigationView.inflateMenu(R.menu.menu_nav_extra);

        //The recycler views are displayed and hidden on the basis of the option selected
        RecyclerView technicalEventsRV = findViewById(R.id.event_technical_rv);
        adapterTechnical = new EventsCategoryRecyclerViewAdapter(getTechnicalEventsData(), this);
        setUpRecyclerView(technicalEventsRV, adapterTechnical);

        RecyclerView CEDepartmentalEventsRV = findViewById(R.id.event_ce_departmental_rv);
        adapterCEDepartmental = new EventsCategoryRecyclerViewAdapter(getCEDepartmentalEventsData(), this);
        setUpRecyclerView(CEDepartmentalEventsRV, adapterCEDepartmental);

        RecyclerView CSEDepartmentalEventsRV = findViewById(R.id.event_cse_departmental_rv);
        adapterCSEDepartmental = new EventsCategoryRecyclerViewAdapter(getCSEDepartmentalEventsData(), this);
        setUpRecyclerView(CSEDepartmentalEventsRV, adapterCSEDepartmental);

        RecyclerView EEDepartmentalEventsRV = findViewById(R.id.event_eee_departmental_rv);
        adapterEEDepartmental = new EventsCategoryRecyclerViewAdapter(getEEEDepartmentalEventsData(), this);
        setUpRecyclerView(EEDepartmentalEventsRV, adapterEEDepartmental);

        RecyclerView ECEDepartmentalEventsRV = findViewById(R.id.event_ece_departmental_rv);
        adapterECEDepartmental = new EventsCategoryRecyclerViewAdapter(getECEDepartmentalEventsData(), this);
        setUpRecyclerView(ECEDepartmentalEventsRV, adapterECEDepartmental);

        RecyclerView MEDepartmentalEventsRV = findViewById(R.id.event_me_departmental_rv);
        adapterMEDepartmental = new EventsCategoryRecyclerViewAdapter(getMEDepartmentalEventsData(), this);
        setUpRecyclerView(MEDepartmentalEventsRV, adapterMEDepartmental);

        RecyclerView gamingEventsRV = findViewById(R.id.event_gaming);
        adapterOthers = new EventsCategoryRecyclerViewAdapter(getGamingEventsData(), this);
        setUpRecyclerView(gamingEventsRV, adapterOthers);

        ImageView imageView = findViewById(R.id.event_image);

        drawerLayout = findViewById(R.id.event_drawer_layout);
        NavigationViewHelper navHelper = new NavigationViewHelper(NavigationViewHelper.MAIN_ACTIVITY,
                this, navigationView, drawerLayout, actionBar, technicalEventsRV, CEDepartmentalEventsRV,
                CSEDepartmentalEventsRV, EEDepartmentalEventsRV, ECEDepartmentalEventsRV, MEDepartmentalEventsRV, gamingEventsRV);
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
        adapterCEDepartmental.startListening();
        adapterCSEDepartmental.startListening();
        adapterEEDepartmental.startListening();
        adapterECEDepartmental.startListening();
        adapterMEDepartmental.startListening();
        adapterOthers.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterTechnical.stopListening();
        adapterCEDepartmental.stopListening();
        adapterCSEDepartmental.stopListening();
        adapterEEDepartmental.stopListening();
        adapterECEDepartmental.stopListening();
        adapterMEDepartmental.stopListening();
        adapterOthers.stopListening();
    }

    private void setUpRecyclerView(RecyclerView recyclerView, EventsCategoryRecyclerViewAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //Adding a snap effect, that keeps a particular item to centre
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getTechnicalEventsData() {
        Query query = database.getReference().child("events").child("robotics");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                        setQuery(query, CognitiaEvent.class).
                        build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getCEDepartmentalEventsData() {
        Query query = database.getReference().child("events").child("civil");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                        setQuery(query, CognitiaEvent.class).
                        build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getCSEDepartmentalEventsData() {
        Query query = database.getReference().child("events").child("cse");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                setQuery(query, CognitiaEvent.class).
                build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getEEEDepartmentalEventsData() {
        Query query = database.getReference().child("events").child("eee");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                setQuery(query, CognitiaEvent.class).
                build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getECEDepartmentalEventsData() {
        Query query = database.getReference().child("events").child("ece");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                setQuery(query, CognitiaEvent.class).
                build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getMEDepartmentalEventsData() {
        Query query = database.getReference().child("events").child("me");
        query.keepSynced(true);

        return new FirebaseRecyclerOptions.Builder<CognitiaEvent>().
                setQuery(query, CognitiaEvent.class).
                build();
    }

    private FirebaseRecyclerOptions<CognitiaEvent> getGamingEventsData() {
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
