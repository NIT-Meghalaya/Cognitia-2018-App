package in.cognitia.cognitia18;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;

import java.util.HashMap;
import java.util.Map;

import in.cognitia.cognitia18.Util.EventsImagesAssociator;

/**
 * Created by devansh on 7/10/18.
 */

public class EventsCategoryRecyclerViewAdapter extends FirebaseRecyclerAdapter<CognitiaEvent, EventViewHolder> {

    private Context context;
    private static Map<String, CognitiaEvent> eventMap = new HashMap<>();
    private PreferenceManager preferenceManager;
    private Resources resources;

    //Two, one for nav tap target, and other for buttons
    private static boolean initialCall1 = true;
    private static boolean initialCall2 = true;

    EventsCategoryRecyclerViewAdapter(FirebaseRecyclerOptions<CognitiaEvent> options, Context context) {
        super(options);
        this.context = context;

        resources = context.getResources();

        if (initialCall1) {
            initialCall1 = false;
            preferenceManager = new PreferenceManager(context);
            if (preferenceManager.isFirstTimeLaunch()) {
                TapTargetSequence sequence = new TapTargetSequence((Activity)context)
                        .targets(
                                // This tap target will target the back button, we just need to pass its containing toolbar
                                TapTarget.forToolbarNavigationIcon((Toolbar) ((Activity) context).findViewById(R.id.event_toolbar),
                                        resources.getString(R.string.nav_tap_target)).id(1)
                        );
                sequence.start();
            }
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder,
                                    int position, @NonNull CognitiaEvent event) {
        eventMap.put(event.getName(), event);

        holder.name.setText(event.getName());
        holder.description.setText(event.getAbout());

        int imageResId = EventsImagesAssociator.getEventImageId(event.getName());
        //Loading event image using Glide library
        Glide.with(context).load(imageResId).into(holder.image);
        //Doing this to get the id of the drawable later
        holder.image.setTag(R.string.image_tag, imageResId);

        if (event.getName().equals(CognitiaTeamMember.GOAL_AGAINST_TIME) && initialCall2) {
            initialCall2 = false;
            if (preferenceManager.isFirstTimeLaunch())
            createTapTargetSequence();
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new EventViewHolder(itemView);
    }

    public static Map<String, CognitiaEvent> getEventsMap() {
        return eventMap;
    }


    private void createTapTargetSequence() {
        preferenceManager.setIsFirstTimeLaunch(false);

        TapTargetView.showFor((Activity) context, TapTarget.forView(((Activity) context).findViewById(R.id.share_button),
                resources.getString(R.string.share_tap_target)), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                TapTargetView.showFor((Activity) context, TapTarget.forView(((Activity) context).findViewById(R.id.explore_button),
                        resources.getString(R.string.explore_tap_target)));
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                super.onTargetDismissed(view, userInitiated);
                TapTargetView.showFor((Activity) context, TapTarget.forView(((Activity) context).findViewById(R.id.explore_button),
                        resources.getString(R.string.explore_tap_target)));
            }
        });

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
