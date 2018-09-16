package in.cognitia.cognitia18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class EventDetailActivity extends AppCompatActivity {

    public static final String IMAGE_ID_EXTRA = "image_id_extra";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();

        int imageResId = intent.getIntExtra(IMAGE_ID_EXTRA, 0);
        imageView = findViewById(R.id.description_image);
        imageView.setImageResource(imageResId);
    }
}
