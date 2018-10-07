package in.cognitia.cognitia18;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;

import static in.cognitia.cognitia18.R.id.*;
import static in.cognitia.cognitia18.CognitiaTeamMember.*;

/**
 * Created by devansh on 2/10/18.
 */

public class NavigationViewHelper {

    //These will used to prevent launching the same activity
    public static final int MAIN_ACTIVITY = 1;
    public static final int TEAM_GALLERY_ACTIVITY = 2;

    private Context context;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private int activity;
    private RecyclerView recyclerView;
    private ActionBar actionBar;

    NavigationViewHelper(int activityId, Context context, NavigationView navigationView, DrawerLayout drawerLayout,
                         ActionBar actionBar, RecyclerView... rv) {
        this.context = context;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.activity = activityId;
        if (rv.length > 0)
            this.recyclerView = rv[0];
        this.actionBar = actionBar;

        selectNavigationOptions();
    }

    private void selectNavigationOptions() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String teamName = null;
                Intent intent = null;

                switch (item.getItemId()) {
                    case R.id.nav_events:
                        if (activity != MAIN_ACTIVITY)
                            intent = new Intent(context, MainActivity.class);
                        break;
                    case R.id.nav_team:
                        if (activity != TEAM_GALLERY_ACTIVITY)
                                intent = new Intent(context, TeamGalleryActivity.class);
                        break;
                    case team_departmental:
                        teamName = DEPARTMENTAL;
                        break;
                    case team_designing:
                        teamName = DESIGNING;
                        break;
                    case team_ecell:
                        teamName = ECELL;
                        break;
                    case team_event_management:
                        teamName = EVENT_MAMANGEMET;
                        break;
                    case team_fun_events:
                        teamName = FUN_EVENTS;
                        break;
                    case team_gaming:
                        teamName = GAMING;
                        break;
                    case team_hospitality:
                        teamName = HOSPITALITY;
                        break;
                    case team_photowalk:
                        teamName = PHOTOWALK;
                        break;
                    case team_publicity:
                        teamName = PUBLICITY;
                        break;
                    case team_quiz_debate:
                        teamName = QUIZ_DEBATE;
                        break;
                    case team_secretaries:
                        teamName = SECRETARIES_MEMBERS;
                        break;
                    case team_shimmer:
                        teamName = SHIMMER_ARPEGGIO;
                        break;
                    case team_stage_management:
                        teamName = STAGE_MANAGEMENT;
                        break;
                    case team_technical:
                        teamName = TECHNICAL;
                        break;
                    case team_web_development:
                        teamName = WEB_DEVELOPMENT;
                        break;
                    case team_app_development:
                        teamName = APP_DEVELOPMENT;
                        break;
                }

                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawer(Gravity.START);

                actionBar.setTitle(teamName);

                if (intent == null) {
                    recyclerView.setAdapter(new TeamGalleryRecyclerViewAdapter(
                            TeamMembersArrayInitializer.getTeamMembers(teamName), context));
                } else {
                    context.startActivity(intent);
                }

                return true;
            }
        });
    }

}
