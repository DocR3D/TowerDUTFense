package com.example.i174085.tower_defense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    int[][] map = new int[15][9];
    LinearLayout mapLayout;
    int height= 20;
    int width= 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapLayout = findViewById(R.id.mapLayout2);





        for(int i=0; i<15; i++){
            for(int j=0; j<9; j++){

            }
        }

    }
}
