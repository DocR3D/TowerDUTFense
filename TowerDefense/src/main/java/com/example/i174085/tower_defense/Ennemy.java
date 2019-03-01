package com.example.i174085.tower_defense;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.example.i174085.tower_defense.MainActivity.joueur;

public class Ennemy {

    int vie;
    int vieMax;
    ImageView unEnnemy;
    int X;
    int Y;
    MediaPlayer mediaPlayer;
    RelativeLayout laMap;

    public Ennemy(int vie, Context pContext, RelativeLayout laMap, int X, int Y) {
        this.vie = vie;
        vieMax = vie;
        this.X = X;
        this.Y = Y;
        this.laMap = laMap;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 100);

        unEnnemy = new ImageView(pContext);
        unEnnemy.setBackgroundResource(R.drawable.ennemy1);
        unEnnemy.setLayoutParams(params);

        unEnnemy.setRotation(90);


        unEnnemy.setX(X);
        unEnnemy.setY(Y);
        this.laMap.addView(unEnnemy);
    }

    public void deplacer(int[][] map){

        int j = (int) unEnnemy.getX()/100;
        int i = (int) unEnnemy.getY()/100;
        if(map[j][i+1] > map[j][i]){
            i++;

        }else if(map[j+1][i]> map[j][i]){
            j++;

        }else if((j != 0) && (map[j][i-1] > map[j][i]) ){
            i--;

        }
        unEnnemy.setX(j*100);

        X = j*100;
        unEnnemy.setY(i*100);
        Y = i*100;
    }


    public float getX()
    {
        return unEnnemy.getX();
    }
    public float getY()
    {
        return unEnnemy.getY();
    }

    public void setX(int i)
    {
        unEnnemy.setX(i);
    }
    public void setY(int i)
    {
        unEnnemy.setY(i);
    }
    public int getVie() {
        return vie;
    }
    public ImageView getUnEnnemy() {
        return unEnnemy;
    }

    public void isDead(int rMort, Level level){
        if(getVie() <= 0 || rMort==0){
            Level.sonMort();
            level.laMap.removeView(getUnEnnemy());
            Level.ennemies.remove(this);
            level.NbEnnemyMort++;
            if(rMort == 1) joueur.addMoney(3);

        }


    }
    public void takeDamage(int damage){
        vie = vie - damage;
        if(vie<vieMax/1.25) unEnnemy.setBackgroundResource(R.drawable.ennemy2);
        if(vie<vieMax/2) unEnnemy.setBackgroundResource(R.drawable.ennemy3);
    }

}
