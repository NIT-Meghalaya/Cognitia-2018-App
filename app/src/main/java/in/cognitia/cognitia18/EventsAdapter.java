package in.cognitia.cognitia18;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by devansh on 9/9/18.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder>{

    private Context context;
    private List<CognitiaEvent> events;

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView image;
        public Context mContext;

        public EventViewHolder(View view) {
            super(view);

            mContext = view.getContext();

            name = view.findViewById(R.id.card_name);
            description = view.findViewById(R.id.card_description);
            image = view.findViewById(R.id.card_image);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, EventDetailActivity.class);
                    intent.putExtra(EventDetailActivity.IMAGE_ID_EXTRA, getDrawableId(image));
                    intent.putExtra(EventDetailActivity.EVENT_NAME_EXTRA, name.getText());

                    //Implementing multiple transitions
                    Pair<View, String> p1 = Pair.create((View)image, mContext.getString(R.string.event_image_trans));

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) mContext, p1);
                    view.getContext().startActivity(intent, options.toBundle());
                }
            });
        }

        //Implementing this because it is not possible to get image ID from an ImageView
        private int getDrawableId(ImageView imageView) {
            return (Integer) imageView.getTag(R.string.image_tag);
        }
    }

    public EventsAdapter(Context context, List<CognitiaEvent> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        CognitiaEvent event = events.get(position);
        holder.name.setText(event.getName());
        holder.description.setText(event.getShortDescription());
        holder.image.setImageResource(event.getImageResId());

        //Loading event image using Glide library
        Glide.with(context).load(event.getImageResId()).into(holder.image);
        //Doing this to get the id of the drawable later
        holder.image.setTag(R.string.image_tag, event.getImageResId());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
