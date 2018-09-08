package in.cognitia.cognitia18;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

    //Splash Screen Timer
    private static int SPLASH_TIME_OUT = 2990;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            //This method will be executed once the wait is over
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(intent);

                //Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
