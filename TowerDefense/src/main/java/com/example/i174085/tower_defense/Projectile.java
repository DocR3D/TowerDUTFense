package com.example.i174085.tower_defense;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Projectile {


    float positionX;
    float positionY;

    float positionFinaleX;
    float positionFinaleY;

    int damage;

    Ennemy target;

    boolean isDestroyed = false;
    RelativeLayout laMap;
    ImageView thePNG;

    public Projectile(float positionX, float positionY, int damage, Ennemy target, Context pContext, RelativeLayout laMap) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionFinaleX = target.getX();
        this.positionFinaleY = target.getY();
        this.damage = damage;
        this.target = target;
        this.laMap = laMap;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 100);

        thePNG = new ImageView(pContext);
        thePNG.setBackgroundResource(R.drawable.projectile);
        thePNG.setLayoutParams(params);

        thePNG.setRotation(90);


        thePNG.setX(positionX);
        thePNG.setY(positionY);
        this.laMap.addView(thePNG);
    }

    public void goToNextPosition(){
        if (Level.ennemies.contains(target)) {
            positionFinaleX = target.getX();
            positionFinaleY = target.getY();

        if(positionX-positionFinaleX < 0) {
            positionY = functionXtoY(positionX + 5);
            positionX = positionX + 5;
        }else{
            positionY = functionXtoY(positionX - 5);
            positionX = positionX - 5;
        }

            thePNG.setX(positionX);
            thePNG.setY(positionY);

        if(distanceFromAtoB() < 50){
            this.inflictDamage();
        }else{
            //thePNG.setVisibility(View.INVISIBLE);
            isDestroyed = true;
        }
        }


        Log.e("couou", "positionX : " + positionX + "PositionY : " + positionY);
        Log.e("couou", "positionFinalX : " + positionFinaleX + "PositionFinalY : " + positionFinaleY);

    }

    public float functionXtoY(float x){
       float a = (positionX - positionY)/(positionFinaleX-positionFinaleY);
       float b = positionY - positionX * a;
       float y = a*x + b;
       return y;
    }

    public double distanceFromAtoB(){
        return Math.sqrt(Math.pow(positionFinaleX- positionX,2) + Math.pow(positionFinaleY - positionFinaleX,2));
    }

    public void inflictDamage(){
        target.takeDamage(damage);
    }



}
