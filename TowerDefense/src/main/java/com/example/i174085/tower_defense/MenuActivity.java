package com.example.i174085.tower_defense;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button jouer;
    Button credit;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context leContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musicmenu);

        mediaPlayer.start();

        jouer = findViewById(R.id.button);
        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent =  new Intent(leContext, MainActivity.class);
                leContext.startActivity(myIntent);

            }
        });

        credit = findViewById(R.id.Credit);
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent =  new Intent(leContext, CreditActivity.class);
                leContext.startActivity(myIntent);
                mediaPlayer.stop();

            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }
}
