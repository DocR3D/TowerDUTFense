package com.example.i174085.tower_defense;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout laMap;
    public static Button startButton;
    public static Player joueur;
    Level unNiveau;
    private TextView nbArgent;
    TextView nbVague;
    TextView nbVie;
    static Context leContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leContext = this;
        laMap = findViewById(R.id.laCarte);
        startButton = findViewById(R.id.start);
        nbArgent = findViewById(R.id.Argent);
        nbVague = findViewById(R.id.Vague);
        nbVie = findViewById(R.id.Vie);
        unNiveau = new Level(this, laMap);
        joueur = new Player(this, nbVie, nbArgent, nbVague);
        unNiveau.afficherCarte();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                unNiveau.start();
            }
        });


    }


    // Define the code block to be executed




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

    @Override
    protected void onPause() {
        super.onPause();
        Level.mediaPlayer.stop();
        Level.mediaPlayer.release();

    }
}
