package in.cognitia.cognitia18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by devansh on 18/9/18.
 */

public class CognitiaEventPagerAdapter extends PagerAdapter {

    private Context context;
    private CognitiaEvent[] cognitiaEvent = new CognitiaEvent[2];
    FirebaseDatabase database;
    DatabaseReference eventDBReference;

    public CognitiaEventPagerAdapter(Context context) {
        this.context = context;
        this.cognitiaEvent = cognitiaEvent;

        database = FirebaseDatabase.getInstance();
        eventDBReference = database.getReference().child("events").child("robotics").child("goal against time");

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.event_description, container, false);

        eventDBReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cognitiaEvent[0] = dataSnapshot.getValue(CognitiaEvent.class);
                Log.v("DB Log", cognitiaEvent[0].getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.v("Read failed", "Database read error");
            }
        });

        TextView heading = (TextView) layout.findViewById(R.id.heading);
        TextView bodyText = (TextView) layout.findViewById(R.id.body);

        switch (position) {
            case 0:
                heading.setText(CognitiaEvent.DESCRIPTION);
                bodyText.setText(cognitiaEvent[0].getDescription());
                break;
            case 1:
                heading.setText(CognitiaEvent.SPECIFICATIONS);
                bodyText.setText(cognitiaEvent[0].getRobotSpecs());
                break;
            case 2:
                heading.setText(CognitiaEvent.RULES);
                bodyText.setText(cognitiaEvent[0].getRules());
                break;
            case 3:
                heading.setText(CognitiaEvent.COORDINATORS);
                bodyText.setText(cognitiaEvent[0].getCoordinators());
                break;
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
        return 4;
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
                return CognitiaEvent.SPECIFICATIONS;
            case 2:
                return CognitiaEvent.RULES;
            case 3:
                return CognitiaEvent.COORDINATORS;
        }
        return super.getPageTitle(position);
    }
}
