package in.cognitia.cognitia18;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import in.cognitia.cognitia18.Util.EventsImagesAssociator;
import in.cognitia.cognitia18.EventsCategoryRecyclerViewAdapter.GridSpacingItemDecoration;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    //This will help to prevent the call to setPersistenceEnabled more than once
    private static boolean initialCall = true;
    private PreferenceManager preferenceManager;

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
            initialCall = false;
            //setPersistenceEnabled() needs to be called before any other use of DB
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        database = FirebaseDatabase.getInstance();

        //Hides the status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = findViewById(R.id.event_toolbar);
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

        preferenceManager = new PreferenceManager(this);

        /*if (preferenceManager.isFirstTimeLaunch()) {
            createTapTargetSequence();
        }*/

        Button scheduleButton = findViewById(R.id.schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Started", "Download started");
                checkPermissions();
                if (checkPermissions()) {
                    Toast.makeText(MainActivity.this, "Downloading...", Toast.LENGTH_SHORT).show();
                    startDownload();
                }
                Log.v("Stopped", "Download stopped");
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permission granted", " ");
            }
        }
    }

    /**
    * Start Download
    */
    public void startDownload() {
        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse("http://cognitia.nitmeghalaya.in/img/SCHEDULE.pdf"));
        mRqRequest.setDescription("Schedule");
        mRqRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Cognitia 2018 Schedule.pdf");
        long idDownLoad=mManager.enqueue(mRqRequest);
    }

    private boolean checkPermissions () {
        int result;
        String[] writePermission = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        result = ContextCompat.checkSelfPermission(MainActivity.this, writePermission[0]);
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, writePermission, 100);
            return false;
        }
        return true;
    }

    private void setUpRecyclerView(RecyclerView recyclerView, EventsCategoryRecyclerViewAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //Adding a snap effect, that keeps a particular item to centre
        //SnapHelper snapHelper = new LinearSnapHelper();
        //snapHelper.attachToRecyclerView(recyclerView);
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

    private void createTapTargetSequence() {
        //preferenceManager.setIsFirstTimeLaunch(false);
        TapTargetSequence sequence = new TapTargetSequence(this)
            .targets(
                    // This tap target will target the back button, we just need to pass its containing toolbar
                    TapTarget.forToolbarNavigationIcon(toolbar, "This is the back button").id(1)
            );
        sequence.start();
    }
}
