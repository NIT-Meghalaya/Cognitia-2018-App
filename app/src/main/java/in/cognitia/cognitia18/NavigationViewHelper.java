package in.cognitia.cognitia18;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

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

    public NavigationViewHelper(int activity, Context context, NavigationView navigationView, DrawerLayout drawerLayout) {
        this.context = context;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.activity = activity;

        selectNavigationOptions();
    }

    public void selectNavigationOptions() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_events:
                        if (activity != MAIN_ACTIVITY) {
                            Intent mainActivityIntent = new Intent(context, MainActivity.class);
                            context.startActivity(mainActivityIntent);
                            drawerLayout.closeDrawer(Gravity.START);
                            return true;
                        } else
                            drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.nav_team:
                        if (activity != TEAM_GALLERY_ACTIVITY) {
                            Intent teamIntent = new Intent(context, TeamGalleryActivity.class);
                            context.startActivity(teamIntent);
                            drawerLayout.closeDrawer(Gravity.START);
                            return true;
                        } else {
                            drawerLayout.closeDrawer(Gravity.START);
                        }
                        break;
                }
                return false;
            }
        });
    }

}
