package in.cognitia.cognitia18;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static in.cognitia.cognitia18.R.id.*;
import static in.cognitia.cognitia18.CognitiaTeamMember.*;

/**
 * Created by devansh on 2/10/18.
 */

public class NavigationViewHelper {

    //These will used to prevent launching the same activity
    public static final int MAIN_ACTIVITY = 1;
    public static final int TEAM_GALLERY_ACTIVITY = 2;
    public static final int SPONSORS_GALLERY = 3;
    public static final String SPONSORS_INTENT = "Sponsors";

    private Context context;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private int activity;
    private RecyclerView[] recyclerViewArray;
    private ActionBar actionBar;
    private ImageView imageView;

    NavigationViewHelper(int activityId, Context context, NavigationView navigationView, DrawerLayout drawerLayout,
                         ActionBar actionBar, RecyclerView... rv) {
        this.context = context;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.activity = activityId;
        this.actionBar = actionBar;
        if (rv.length > 0) {
            recyclerViewArray = rv;
        }

        imageView = (ImageView) ((Activity) context).findViewById(R.id.event_image);

        selectNavigationOptions();
    }

    private void selectNavigationOptions() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String teamName = null;
                String eventName = null;
                Intent intent = null;

                switch (item.getItemId()) {
                    case R.id.nav_events:
                        if (activity != MAIN_ACTIVITY)
                            intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.id.nav_team:
                        if (activity != TEAM_GALLERY_ACTIVITY) {
                            intent = new Intent(context, TeamGalleryActivity.class);
                            intent.putExtra(SPONSORS_INTENT, 0);
                        }
                        context.startActivity(intent);
                        break;
                    case R.id.events_technical:
                        actionBar.setTitle(TECHNICAL);
                        navigationView.getMenu().clear();
                        navigationView.inflateMenu(R.menu.menu_nav);
                        navigationView.inflateMenu(R.menu.menu_nav_extra);
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[0].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.robotics).into(imageView);
                        break;
                    case R.id.events_departmental:
                        navigationView.getMenu().clear();
                        navigationView.inflateMenu(R.menu.menu_nav_departmental_events);
                        navigationView.inflateMenu(R.menu.menu_nav_extra);
                        navigationView.setCheckedItem(R.id.events_ce_departmental);
                    case R.id.events_ce_departmental:
                        actionBar.setTitle(CE_DEPARTMENTAL);
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[1].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.civil).into(imageView);
                        break;
                    case R.id.events_cse_departmental:
                        actionBar.setTitle(CSE_DEPARTMENTAL);
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[2].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.cse).into(imageView);
                        imageView.setImageResource(R.drawable.cse);
                        break;
                    case R.id.events_eee_departmental:
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[3].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.electrical).into(imageView);
                        break;
                    case R.id.events_ece_departmental:
                        actionBar.setTitle(ECE_DEPARTMENTAL);
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[4].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.ece).into(imageView);
                        break;
                    case R.id.events_me_departmental:
                        actionBar.setTitle(ME_DEPARTMENTAL);
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[5].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.mechanical).into(imageView);
                        break;
                    case R.id.events_others:
                        actionBar.setTitle(GAMING);
                        navigationView.getMenu().clear();
                        navigationView.inflateMenu(R.menu.menu_nav_other_events);
                        navigationView.inflateMenu(R.menu.menu_nav_extra);
                        navigationView.setCheckedItem(R.id.events_gaming);
                    case R.id.events_gaming:
                        for (RecyclerView rv : recyclerViewArray) {
                            rv.setVisibility(View.GONE);
                        }
                        recyclerViewArray[6].setVisibility(View.VISIBLE);
                        Glide.with(context).load(R.drawable.gaming).into(imageView);
                        imageView.setImageResource(R.drawable.gaming);
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
                    case team_sponsorship:
                        teamName = SPONSORSHIP;
                        break;
                    case sponsors:
                        if ((activity != SPONSORS_GALLERY)) {
                            intent = new Intent(context, TeamGalleryActivity.class);
                            intent.putExtra(SPONSORS_INTENT, SPONSORS_GALLERY);
                            context.startActivity(intent);
                        }
                        break;
                    case sponsorship_email:
                        String emailId = context.getResources().getString(R.string.sponsorship_email);
                        intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailId));
                        context.startActivity(intent);
                        break;
                    case sponsorship_phone:
                        String phone = context.getResources().getString(R.string.sponsorship_phone);
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                        context.startActivity(intent);
                        break;
                    case link_facebook:
                        String urlFB = context.getResources().getString(R.string.link_facebook);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlFB));
                        context.startActivity(intent);
                        break;
                    case link_instagram:
                        String urlInsta = context.getResources().getString(R.string.link_instagram);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlInsta));
                        context.startActivity(intent);
                        break;
                    case link_youtube:
                        String urlYT = context.getResources().getString(R.string.link_youtube);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlYT));
                        context.startActivity(intent);
                        break;
                    case link_website:
                        String website = context.getResources().getString(R.string.link_website);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                        context.startActivity(intent);
                        break;
                    case nav_feedback:
                        String feedbackEmail = context.getResources().getString(R.string.email_devansh);
                        intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + feedbackEmail));
                        context.startActivity(intent);
                        break;
                    case link_github:
                        String repoLink = context.getResources().getString(R.string.link_github);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repoLink));
                        context.startActivity(intent);
                        break;

                }

                navigationView.setCheckedItem(item.getItemId());
                drawerLayout.closeDrawer(Gravity.START);

                if (teamName != null) {
                    actionBar.setTitle(teamName);
                    recyclerViewArray[0].setAdapter(new TeamGalleryRecyclerViewAdapter(
                            TeamMembersArrayInitializer.getTeamMembers(teamName), context));
                }

                return true;
            }
        });
    }
}