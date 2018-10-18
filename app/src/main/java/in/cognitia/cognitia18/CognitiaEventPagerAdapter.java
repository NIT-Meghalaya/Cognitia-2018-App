package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by devansh on 18/9/18.
 */

public class CognitiaEventPagerAdapter extends PagerAdapter {

    public static int EVENT_TECHNICAL = 4;
    public static int EVENT_NON_TECHNICAL = 3;

    private Context context;
    private Bundle eventBundle;
    private int lastCount;

    public CognitiaEventPagerAdapter(Context context, Bundle eventBundle, int eventCategory) {
        this.context = context;
        this.eventBundle = eventBundle;
        this.lastCount = eventCategory;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_event_detail, container, false);

        TextView heading = layout.findViewById(R.id.heading);
        TextView body = layout.findViewById(R.id.body);

        RecyclerView recyclerView = layout.findViewById(R.id.cognitia_event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        if ((lastCount == EVENT_TECHNICAL && position == EVENT_TECHNICAL - 1) ||
                (lastCount == EVENT_NON_TECHNICAL && position == EVENT_NON_TECHNICAL - 1)) {

            NestedScrollView scrollView = layout.findViewById(R.id.event_description_nested_scroll_view);
            scrollView.setVisibility(View.GONE);

            recyclerView.setAdapter(new CognitiaEventDetailsTeamAdapter(context, eventBundle));
            recyclerView.addItemDecoration(new EventsCategoryRecyclerViewAdapter.GridSpacingItemDecoration(1, dpToPx(20), true));
        } else {
            switch (position) {
                case 0:
                    heading.setText(CognitiaEvent.DESCRIPTION);
                    body.setText(fromHtml(eventBundle.getString(EventDetailActivity.DESCRIPTION)));
                    break;
                case 1:
                    heading.setText(CognitiaEvent.RULES);
                    body.setText(fromHtml(eventBundle.getString(EventDetailActivity.RULES)));
                    break;
                case 2:
                    if (lastCount == EVENT_TECHNICAL) {
                        heading.setText(CognitiaEvent.ROBOT_SPECIFICATIONS);
                        body.setText(fromHtml(eventBundle.getString(EventDetailActivity.ROBOT_SPECS)));
                    }
            }
        }

        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return lastCount;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return CognitiaEvent.DESCRIPTION;
            case 1:
                return CognitiaEvent.RULES;
            case 2:
                if (lastCount == EVENT_TECHNICAL) {
                    return CognitiaEvent.ROBOT_SPECS;
                } else
                    return CognitiaEvent.TEAM;
            case 3:
                return CognitiaEvent.TEAM;
        }
        return super.getPageTitle(position);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}