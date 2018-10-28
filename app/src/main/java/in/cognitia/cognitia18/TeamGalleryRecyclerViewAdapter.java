package in.cognitia.cognitia18;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by devansh on 5/10/18.
 */

public class TeamGalleryRecyclerViewAdapter extends RecyclerView.Adapter<
        TeamGalleryRecyclerViewAdapter.MyTeamMemberImageHolder> {

    private ArrayList<CognitiaTeamMember> teamMembers;
    private boolean[] randomPositionChecker;
    private int[] randomPositions;
    private Context context;

    TeamGalleryRecyclerViewAdapter(ArrayList<CognitiaTeamMember> teamMembers, Context context) {
        this.teamMembers = teamMembers;
        this.context = context;
        randomPositionChecker = new boolean[this.teamMembers.size()];
        randomPositions = new int[this.teamMembers.size()];

        generateRandomPositions();
    }

    @NonNull
    @Override
    public MyTeamMemberImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_image_layout, parent, false);

        return new MyTeamMemberImageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyTeamMemberImageHolder holder, int position) {



        final CognitiaTeamMember member = teamMembers.get(randomPositions[position]);
        int imageResId = member.getImageId();
        //Adding to get member details later in the profile activity
        holder.imageView.setContentDescription(member.getName());
        //Loading images using glide
        Glide.with(context).load(imageResId).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MemberProfileActivity.class);
                intent.putExtra(MemberProfileActivity.MEMBER_NAME, member);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, null);
                view.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamMembers == null ? 0 : teamMembers.size();
    }

    private void generateRandomPositions() {
        Random random = new Random(); //Returns an integer between 0 to arrayLength - 1
        boolean matchFound = false;   //Used to make match between two persons
        String ashutosh = context.getResources().getString(R.string.ashutosh);
        String allu = context.getResources().getString(R.string.allu);

        int maxSize = teamMembers.size();
        int position;

        for (int i = 0; i < maxSize; i++) {
            //Check that a unique number is obtained every time
            do {
                position = random.nextInt(maxSize);
            } while (randomPositionChecker[position]);
            randomPositionChecker[position] = true;
            randomPositions[i] = position;

            //Match making logic
            if (!matchFound && teamMembers.get(position).getName().equals(ashutosh)) {
                for (int j = 0; j < maxSize; j++) {
                    if (teamMembers.get(j).getName().equals(allu)) {
                        randomPositionChecker[j] = true;
                        // If first person is at an even position,
                        // keep the second person to its previous position
                        if (i%2 != 0) {
                            int temp = randomPositions[i-1];
                            randomPositions[i-1] = j;
                            randomPositions[++i] = temp;
                        } else
                            //If first person is at odd position, place second person after it
                            randomPositions[++i] = j;
                        matchFound = true;
                    }
                }
            } else if (!matchFound && teamMembers.get(position).getName().equals(allu)) {
                for (int j = 0; j < maxSize; j++) {
                    if (teamMembers.get(j).getName().equals(ashutosh)) {
                        randomPositionChecker[j] = true;
                        //If first person is at an even position,
                        // keep the second person to its previous position
                        if (i%2 != 0) {
                            int temp = randomPositions[i-1];
                            randomPositions[i-1] = j;
                            randomPositions[++i] = temp;
                        } else
                            //If first person is at odd position, place second person after it
                            randomPositions[++i] = j;
                        matchFound = true;
                    }
                }
            }
        }
    }

    class MyTeamMemberImageHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        MyTeamMemberImageHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.team_photo);
        }
    }

    //Removing margins between recycler view items
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = 0;
            outRect.top = 0;
            outRect.right = 0;
            outRect.bottom = 0;
        }
    }
}