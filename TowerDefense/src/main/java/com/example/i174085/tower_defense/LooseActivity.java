package com.example.i174085.tower_defense;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.i174085.tower_defense.MainActivity.joueur;

public class LooseActivity extends AppCompatActivity {

    TextView yourScore ;
    TextView bestScore ;
    int oldBestScore = 0;
    Button Start;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context leContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loose);

        Start = findViewById(R.id.button3);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent =  new Intent(leContext, MenuActivity.class);
                leContext.startActivity(myIntent);
            }
        });


        SharedPreferences myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        oldBestScore = myPrefs.getInt("highscore", 0);

        if( oldBestScore <= joueur.getVague()) {
                editor.putInt("highscore", joueur.getVague());
                editor.commit();
                oldBestScore = myPrefs.getInt("highscore", 0);


        }
        yourScore = findViewById(R.id.yourScoreID);
        yourScore.setText("Votre score : \t" + "\t " + joueur.getVague());

        bestScore = findViewById(R.id.bestScoreID);
        bestScore.setText("Le Meilleur score  : \t" + oldBestScore);


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
}
