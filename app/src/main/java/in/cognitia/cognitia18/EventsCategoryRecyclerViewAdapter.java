package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;
import java.util.Map;

import in.cognitia.cognitia18.Util.EventsImagesAssociator;

/**
 * Created by devansh on 7/10/18.
 */

public class EventsCategoryRecyclerViewAdapter extends FirebaseRecyclerAdapter<CognitiaEvent, EventViewHolder> {

    private Context context;
    private static Map<String, CognitiaEvent> eventMap = new HashMap<>();

    EventsCategoryRecyclerViewAdapter(FirebaseRecyclerOptions<CognitiaEvent> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder,
                                    int position, @NonNull CognitiaEvent event) {
        eventMap.put(event.getName(), event);

        holder.name.setText(event.getName());
        holder.description.setText(event.getDescription());

        int imageResId = EventsImagesAssociator.getEventImageId(event.getName());
        //Loading event image using Glide library
        Glide.with(context).load(imageResId).into(holder.image);
        //Doing this to get the id of the drawable later
        holder.image.setTag(R.string.image_tag, imageResId);
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
