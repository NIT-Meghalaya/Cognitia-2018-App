package in.cognitia.cognitia18;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by devansh on 17/10/18.
 */

public class CognitiaEventDetailsTeamAdapter extends RecyclerView.Adapter<
        CognitiaEventDetailsTeamAdapter.MyEventDetailsTeamHolder> {

    //Context is used for loading images using glide
    private Context context;
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

    public CognitiaEventDetailsTeamAdapter(Context context, Bundle cognitiaEventBundle) {
        this.context = context;
        eventBundle = cognitiaEventBundle;

        String eventName;
        if (eventBundle.getString(EventDetailActivity.PARENT) == null)
            eventName = eventBundle.getString(EventDetailActivity.EVENT_NAME);
        else
            eventName = eventBundle.getString(EventDetailActivity.PARENT);
        members = TeamMembersArrayInitializer.getTeamMembersSortedArray(eventName);
    }

    public CognitiaEventDetailsTeamAdapter(Context context, ArrayList<CognitiaTeamMember> membersArrayList) {
        this.context = context;
        members = membersArrayList.toArray(new CognitiaTeamMember[membersArrayList.size()]);
    }

    @NonNull
    @Override
    public CognitiaEventDetailsTeamAdapter.MyEventDetailsTeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_details_team_members, parent, false);

        return new CognitiaEventDetailsTeamAdapter.MyEventDetailsTeamHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CognitiaEventDetailsTeamAdapter.MyEventDetailsTeamHolder holder, int pos) {
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