package in.cognitia.cognitia18;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class TeamGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_gallery);

        RecyclerView recyclerView = findViewById(R.id.rv_team_images);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        ArrayList<CognitiaTeamMember> teamMembers = TeamMembersArrayInitializer.getTeamMembers();

        TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(teamMembers);
        recyclerView.setAdapter(adapter);
    }

    public class TeamGalleryRecyclerViewAdapter extends RecyclerView.Adapter<
            TeamGalleryRecyclerViewAdapter.MyTeamMemberImageHolder> {

        private ArrayList<CognitiaTeamMember> teamMembers;
        private boolean[] randomPositionChecker;

        class MyTeamMemberImageHolder extends RecyclerView.ViewHolder{

            ImageView imageView;

            MyTeamMemberImageHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.team_photo);
            }
        }

        TeamGalleryRecyclerViewAdapter(ArrayList<CognitiaTeamMember> teamMembers) {
            this.teamMembers = teamMembers;
            randomPositionChecker = new boolean[teamMembers.size()];
        }

        @NonNull
        @Override
        public MyTeamMemberImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery_image_layout, parent, false);

            return new MyTeamMemberImageHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyTeamMemberImageHolder holder, int position) {

            int imageResId = teamMembers.get(getRandomPosition()).getImageId();
            //Loading images using glide
            Glide.with(TeamGalleryActivity.this).load(imageResId).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return teamMembers == null ? 0 : teamMembers.size();
        }

        private int getRandomPosition() {
            Random random = new Random();
            //Returns an integer between 0 to arrayLength - 1

            int position;
            //Check that a unique number is obtained every time
            do {
                position = random.nextInt(randomPositionChecker.length);
            } while (randomPositionChecker[position]);
            randomPositionChecker[position] = true;

            return position;
        }
    }
}
