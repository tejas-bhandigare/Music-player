package com.example.song;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class songplay extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageView playButton;
    private ImageView pauseButton;
    private ImageView stopButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songplay);

        // Initialize MediaPlayer with your song
        mediaPlayer = MediaPlayer.create(this, R.raw.tm);

        // Initialize ImageViews
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
       stopButton = findViewById(R.id.stopButton);
    }

    // Method to toggle playback when play/pause buttons are clicked
    public void togglePlayback(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
        } else {
            mediaPlayer.start();
            playButton.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);
            stopButton.setVisibility(View.VISIBLE); // Make the stop button visible when playback starts
        }
    }

    // Method to stop playback when stop button is clicked
    public void stopPlayback(View view) {
        mediaPlayer.stop();
        mediaPlayer.prepareAsync(); // Prepare the MediaPlayer for another playback
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.GONE); // Hide the stop button after playback stops
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
