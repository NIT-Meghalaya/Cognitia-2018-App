package in.cognitia.cognitia18;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by devansh on 18/9/18.
 */

public class CognitiaEventPagerAdapter extends PagerAdapter {

    private Context context;
    private Bundle eventBundle;

    public CognitiaEventPagerAdapter(Context context, Bundle eventBundle) {
        this.context = context;
        this.eventBundle = eventBundle;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_event_detail, container, false);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.cognitia_event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
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
        return 2;
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
            }
        return super.getPageTitle(position);
    }

    //RecyclerView adapter
    private class CognitiaEventDetailsAdapter extends RecyclerView.Adapter<
            CognitiaEventDetailsAdapter.MyEventDetailsHolder> {

        private Bundle eventBundle;
        private int position;

        public class MyEventDetailsHolder extends RecyclerView.ViewHolder{

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
                    holder.body.setText(eventBundle.getString(EventDetailActivity.RULES));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
