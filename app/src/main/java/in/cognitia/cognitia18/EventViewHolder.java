package in.cognitia.cognitia18;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by devansh on 9/9/18.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView name, description;
    public ImageView image;
    public Context mContext;
    private Button shareButton;
    private Button exploreButton;

    public EventViewHolder(View view) {
        super(view);

        mContext = view.getContext();

        name = view.findViewById(R.id.card_name);
        description = view.findViewById(R.id.card_description);
        image = view.findViewById(R.id.card_image);
        shareButton = view.findViewById(R.id.share_button);
        exploreButton = view.findViewById(R.id.explore_button);

        shareButton.setOnClickListener(new ShareOnClickListener());

        CardOnClickListener cardOnClickListener = new CardOnClickListener();
        exploreButton.setOnClickListener(cardOnClickListener);
        name.setOnClickListener(cardOnClickListener);
        description.setOnClickListener(cardOnClickListener);
        image.setOnClickListener(cardOnClickListener);
    }

    //Adding this because it is not possible to get image ID from an ImageView
    private int getDrawableId(ImageView imageView) {
        return (Integer) imageView.getTag(R.string.image_tag);
    }

    private class CardOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, EventDetailActivity.class);
            intent.putExtra(EventDetailActivity.IMAGE_ID, getDrawableId(image));

            CognitiaEvent event = EventsCategoryRecyclerViewAdapter.getEventsMap().get(name.getText());

            Bundle eventBundle = new Bundle();
            eventBundle.putString(EventDetailActivity.EVENT_NAME, event.getName());
            Log.v("Event name", event.getName());
            eventBundle.putString(EventDetailActivity.DESCRIPTION, event.getDescription());
            eventBundle.putString(EventDetailActivity.OBJECTIVE, event.getShortDescription());
            eventBundle.putString(EventDetailActivity.RULES, event.getRules());
            eventBundle.putString(EventDetailActivity.AIM, event.getAbout());
            eventBundle.putString(EventDetailActivity.PARENT, event.getParent());
            if (event.getRobotSpecs() != null)
                eventBundle.putString(EventDetailActivity.ROBOT_SPECS, event.getRobotSpecs());
            eventBundle.putInt(EventDetailActivity.IMAGE_ID, getDrawableId(image));

            intent.putExtras(eventBundle);

            /*//Implementing multiple transitions
            Pair<View, String> p1 = Pair.create((View)image, mContext.getString(R.string.event_image_trans));

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext, p1);
            view.getContext().startActivity(intent, options.toBundle());*/
            view.getContext().startActivity(intent);
        }
    }

    private class ShareOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            CognitiaEvent event = EventsCategoryRecyclerViewAdapter.getEventsMap().get(name.getText());

            String text = "Check out _" + event.getName() + "_ and other awesome events.\n\n" +
                    "*NIT Meghalaya* welcomes you to its annual technical festival:\n\n" +
                    mContext.getResources().getString(R.string.cognitia_emoji);
            String appLink  = "\n\nInstall the official app now:\n" +
                    "https://play.google.com/store/apps/details?id=in.cognitia.cognitia18";
            shareIntent.putExtra(Intent.EXTRA_TEXT, text + appLink);

            shareIntent.setType("text/html");

            mContext.startActivity(Intent.createChooser(shareIntent, "Share..."));
        }
    }
}