package in.cognitia.cognitia18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import developer.shivam.crescento.CrescentoImageView;

public class MemberProfileActivity extends AppCompatActivity {

    public static final String MEMBER_NAME = "member_name";

    Intent intent;
    TextView name;
    TextView post;
    TextView team;
    CrescentoImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

        intent = getIntent();
        String memberKey = intent.getExtras().getString(MEMBER_NAME);
        CognitiaTeamMember member = TeamMembersArrayInitializer.getMemberByName(memberKey);

        imageView = findViewById(R.id.member_image);
        Glide.with(this).load(member.getImageId()).into(imageView);

        name = findViewById(R.id.member_name);
        name.setText(member.getName());

        post = findViewById(R.id.member_post);
        post.setText(member.getPost());

        team = findViewById(R.id.member_team);
        team.setText(member.getTeam());
    }

    //Gives a feel of going back to the previous activity on clicking at empty space
    public void goBack(View view) {
        onBackPressed();
    }
}
