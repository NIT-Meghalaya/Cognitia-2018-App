package in.cognitia.cognitia18;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

        RecyclerView recyclerView = layout.findViewById(R.id.cognitia_event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        Log.v("Position", position + "");
        if ((lastCount == EVENT_TECHNICAL && position == EVENT_TECHNICAL - 1) ||
                (lastCount == EVENT_NON_TECHNICAL && position == EVENT_NON_TECHNICAL - 1)) {
            recyclerView.setAdapter(new CognitiaEventDetailsTeamAdapter(eventBundle));
            recyclerView.addItemDecoration(new EventsCategoryRecyclerViewAdapter.GridSpacingItemDecoration(1, dpToPx(20), true));
            Log.v("Ppsition", position + "");
        } else
            recyclerView.setAdapter(new CognitiaEventDetailsAdapter(eventBundle, position));

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
                    return CognitiaEvent.ROBOT_SPECIFICATIONS;
                } else
                    return CognitiaEvent.TEAM;
            case 3:
                return CognitiaEvent.TEAM;
        }
        return super.getPageTitle(position);
    }


    //RecyclerView adapter
    private class CognitiaEventDetailsAdapter extends RecyclerView.Adapter<
            CognitiaEventDetailsAdapter.MyEventDetailsHolder> {

        private Bundle eventBundle;
        private int position;

        public class MyEventDetailsHolder extends RecyclerView.ViewHolder {

            public TextView heading, body;

            public MyEventDetailsHolder(View view) {
                super(view);
                heading = (TextView) view.findViewById(R.id.heading);
                body = (TextView) view.findViewById(R.id.body);
            }
        }

        public CognitiaEventDetailsAdapter(Bundle cognitiaEventBundle, int position) {
            eventBundle = cognitiaEventBundle;
            this.position = position;
        }

        @NonNull
        @Override
        public MyEventDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_description, parent, false);

            return new MyEventDetailsHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyEventDetailsHolder holder, int pos) {
            switch (position) {
                case 0:
                    holder.heading.setText(CognitiaEvent.DESCRIPTION);
                    holder.body.setText(eventBundle.getString(EventDetailActivity.DESCRIPTION));
                    break;
                case 1:
                    holder.heading.setText(CognitiaEvent.RULES);
                    holder.body.setText(fromHtml(eventBundle.getString(EventDetailActivity.RULES)));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }


    private class CognitiaEventDetailsTeamAdapter extends RecyclerView.Adapter<
            CognitiaEventDetailsTeamAdapter.MyEventDetailsTeamHolder> {

        private Bundle eventBundle;
        private CognitiaTeamMember[] members;

        class MyEventDetailsTeamHolder extends RecyclerView.ViewHolder {

            TextView memberName, memberPost;
            ImageView memberImage;

            MyEventDetailsTeamHolder(View view) {
                super(view);
                memberName = view.findViewById(R.id.member_name);
                memberPost = view.findViewById(R.id.member_post);
                memberImage = view.findViewById(R.id.member_image);
            }
        }

        public CognitiaEventDetailsTeamAdapter(Bundle cognitiaEventBundle) {
            eventBundle = cognitiaEventBundle;

            String eventName;
            if (eventBundle.getString(EventDetailActivity.PARENT) == null)
                eventName = eventBundle.getString(EventDetailActivity.EVENT_NAME);
            else
                eventName = eventBundle.getString(EventDetailActivity.PARENT);
            members = TeamMembersArrayInitializer.getTeamMembersSortedArray(eventName);
        }

        @NonNull
        @Override
        public MyEventDetailsTeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_details_team_members, parent, false);

            return new MyEventDetailsTeamHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyEventDetailsTeamHolder holder, int pos) {
            CognitiaTeamMember member = members[pos];

            holder.memberName.setText(members[pos].getName());
            holder.memberPost.setText(members[pos].getPost());
            Glide.with(context)
                    .load(member.getImageId())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.memberImage);
        }

        @Override
        public int getItemCount() {
            return members.length;
        }
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