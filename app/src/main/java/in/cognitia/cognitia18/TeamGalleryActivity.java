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

import java.util.ArrayList;

public class TeamGalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_gallery);

        recyclerView = findViewById(R.id.rv_team_images);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        ArrayList<CognitiaTeamMember> teamMembers = TeamMembersArrayInitializer.getTeamMembers();

        TeamGalleryRecyclerViewAdapter adapter = new TeamGalleryRecyclerViewAdapter(teamMembers);
        recyclerView.setAdapter(adapter);
    }

    public class TeamGalleryRecyclerViewAdapter extends RecyclerView.Adapter<
            TeamGalleryRecyclerViewAdapter.MyTeamMemberImageHolder> {

        private ArrayList<CognitiaTeamMember> teamMembers;

        public class MyTeamMemberImageHolder extends RecyclerView.ViewHolder{

            public ImageView imageView;

            public MyTeamMemberImageHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.team_photo);
            }
        }

        public TeamGalleryRecyclerViewAdapter(ArrayList<CognitiaTeamMember> teamMembers) {
            this.teamMembers = teamMembers;
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

            int imageResId = teamMembers.get(position).getImageId();
            holder.imageView.setImageResource(imageResId);
        }

        @Override
        public int getItemCount() {
            return teamMembers == null ? 0 : teamMembers.size();
        }
    }
}
