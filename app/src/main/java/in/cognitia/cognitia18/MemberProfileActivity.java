package in.cognitia.cognitia18;

import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import developer.shivam.crescento.CrescentoImageView;

import static android.net.Uri.fromParts;


public class MemberProfileActivity extends AppCompatActivity {

    public static final String MEMBER_NAME = "member_name";

    Intent intent;
    TextView name;
    TextView post;
    TextView team;
    TextView email;
    CrescentoImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

        intent = getIntent();
        final CognitiaTeamMember member = (CognitiaTeamMember) intent.getExtras().getSerializable(MEMBER_NAME);

        imageView = findViewById(R.id.member_image);
        Glide.with(this).load(member.getImageId()).into(imageView);

        name = findViewById(R.id.member_name);
        name.setText(member.getName());

        post = findViewById(R.id.member_post);
        post.setText(member.getPost());

        team = findViewById(R.id.member_team);
        team.setText(member.getTeam());

        email = findViewById(R.id.member_email);
        email.setText("Email: " + member.getEmail());
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", member.getEmail(), null));
                startActivity(Intent.createChooser(intent, "Send an email to " + member.getName() + " ..."));
            }
        });
    }

    //Gives a feel of going back to the previous activity on clicking at empty space
    public void goBack(View view) {
        onBackPressed();
    }
}
