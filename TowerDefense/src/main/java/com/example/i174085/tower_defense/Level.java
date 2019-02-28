package com.example.i174085.tower_defense;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import static com.example.i174085.tower_defense.MainActivity.joueur;

public class Level {

    int[][] map;
    RelativeLayout laMap;

    static Context leContext;
    int nbTick=0;
    static ArrayList<Ennemy> ennemies = new ArrayList();
    static ArrayList<Tourelle> Tourelles = new ArrayList<>();
    Handler handler = new Handler();
    int nbEnnemy = 7;
    int vieEnnemy = 20;
    boolean finRound = false;
    int nbEnnemyOnBoard = 0;
    int NbEnnemyMort =0;
    boolean perdu = false;
    Level thisLevel;
    static MediaPlayer mediaPlayer;

    public static ArrayList<Projectile> allTheProjectile = new ArrayList<Projectile>();





    public Level(Context pContext, RelativeLayout laMap) {
    this.laMap=laMap;
    this.leContext = pContext;
    thisLevel = this;

    }

    public void afficherCarte(){
        map = new int[20][20];
        map[0][6] = 1;
        map[1][6] = 2;
        map[2][6] = 3;
        map[3][6] = 4;

        for(int i=0;i<=3;i++){
            for(int j=0;j<=3;j++){
                map[i][j] = -1;
            }
        }


        map[3][5] = 5;
        map[3][4] = 6;
        map[3][3] = 7;
        map[4][3] = 8;
        map[5][3] = 9;
        map[6][3] = 10;
        map[7][3] = 11;
        map[7][4] = 12;
        map[7][5] = 13;
        map[7][6] = 14;
        map[8][6] = 15;
        map[9][6] = 16;
        map[9][7] = 17;
        map[9][8] = 18;
        map[9][9] = 19;
        map[10][9] = 20;
        map[11][9] = 21;
        map[12][9] = 22;
        map[13][9] = 23;
        map[13][8] = 24;
        map[13][7] = 25;
        map[13][6] = 26;
        map[14][6] = 27;
        map[15][6] = 28;
        map[16][6] = 29;
        map[17][6] = 30;
        map[18][6] = 3000;
        mediaPlayer = MediaPlayer.create(leContext, R.raw.musiclevel);

        mediaPlayer.start();




        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 100);
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){

                if(map[i][j] > 0 && map[i][j]!=3000){

                    ImageView uneCase = new ImageView(leContext);
                    uneCase.setBackgroundResource(R.drawable.blanc);


                    uneCase.setLayoutParams(params);
                    uneCase.setX(i*100);
                    uneCase.setY(j*100);
                    laMap.addView(uneCase);

                }else if(map[i][j] == 0){

                    final ImageButton uneCase = new ImageButton(leContext);
                    uneCase.setBackgroundResource(R.drawable.noir);


                    uneCase.setLayoutParams(params);
                    uneCase.setX(i*100);
                    uneCase.setY(j*100);
                    laMap.addView(uneCase);
                    final int finalI = i;
                    final int finalJ = j;
                    uneCase.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(joueur.getMoney()>= 25){
                                joueur.looseMoney(25);
                                joueur.updateText();
                                Tourelles.add(new Tourelle(finalI, finalJ, laMap, leContext));
                            }
                        }
                    });
                }else if(map[i][j] == 3000){
                    ImageView uneCase = new ImageView(leContext);
                    uneCase.setBackgroundResource(R.drawable.rouge);


                    uneCase.setLayoutParams(params);
                    uneCase.setX(i*100);
                    uneCase.setY(j*100);
                    laMap.addView(uneCase);

                }


            }
        }

    }

    public void start(){
        ajouterEnnemy();
        finRound = false;
        handler.postDelayed(runnableCode, 50);
    }
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            nbTick++;
            if(nbTick % 8 == 0 &&  nbEnnemyOnBoard < nbEnnemy) {
                ajouterEnnemy();
                nbEnnemyOnBoard++;
            }
            if(nbEnnemyOnBoard == nbEnnemy) finRound= true;

            for(Ennemy unGenre : new ArrayList<Ennemy>(ennemies)){
               if(nbTick%4 == 0) unGenre.deplacer(map);
                unGenre.isDead(1, thisLevel);
                isOut(unGenre);
            }
            for(Tourelle uneTourelle : Tourelles){
                boolean isTakingDommage=false;
                for(Ennemy unEnnemy : new ArrayList<Ennemy>(ennemies)){
                    if(nbTick%3 == 0){
                        isTakingDommage = unEnnemy.takeDamage(uneTourelle);
                        if(isTakingDommage) break;
                    }
                }


            }
            if(finRound && ennemies.size()==0) {
                if(joueur.getVague() < 15) {
                    nbEnnemy = nbEnnemy + 1 ;
                    Log.e("Nombre ennemy", " " + nbEnnemy);
                    vieEnnemy = vieEnnemy + 8;
                }else {
                    nbEnnemy = nbEnnemy + 2 ;
                    Log.e("Nombre ennemy", " " + nbEnnemy);
                    vieEnnemy = vieEnnemy + 16;
                }
                joueur.addVague();
                nbEnnemyOnBoard = 0;
                NbEnnemyMort = 0;
                MainActivity.startButton.setEnabled(true);


            }else{
                handler.postDelayed(runnableCode, 50);
            }


        }
    };

    public void ajouterEnnemy(){
        ennemies.add(new Ennemy(vieEnnemy,leContext, laMap, 0, 6*100));
    }

    public void isOut(Ennemy unEnnemy) {
        int j = (int) unEnnemy.getX()/100;
        int i = (int) unEnnemy.getY()/100;
        if (map[j][i] == 3000) {
            MainActivity.joueur.looseLive();
            unEnnemy.isDead(0, this);
            if(MainActivity.joueur.getLive() == 0 && perdu == false) {
                perdu = true;
                handler.removeCallbacks(runnableCode);
                Intent myIntent =  new Intent(leContext, LooseActivity.class);
                mediaPlayer.stop();
                leContext.startActivity(myIntent);
            }

        }


    }
    public static void sonMort(){
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(MainActivity.leContext, R.raw.explosion);
        mediaPlayer2.start();
        mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer2.release();

            };
        });
    }




}
