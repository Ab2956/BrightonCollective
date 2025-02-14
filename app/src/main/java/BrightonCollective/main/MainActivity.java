package BrightonCollective.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override                              // Splash activity for the welcome page when loading into app
            public void run() {
                Intent intentSplash = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentSplash);
                finish();
            }
        },1000);
        Log.d(TAG, "onCreate: ");
    }
    @Override
    protected void onStart(){
        super.onStart();

        Log.d(TAG, "onStart: ");
    }

        //TODO splash screen for the app when first loading in
    }
