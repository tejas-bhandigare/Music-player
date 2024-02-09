package com.example.song;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 4000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the splash screen with a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity after the delay
                Intent intent = new Intent(MainActivity.this, sendotp.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        }, SPLASH_TIMEOUT);
    }
}
