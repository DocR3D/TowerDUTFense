package com.example.i174085.tower_defense;

import android.os.Debug;
import android.util.Log;

public class Projectile {


    float positionX;
    float positionY;

    float positionFinaleX;
    float positionFinaleY;

    int damage;

    Ennemy target;

    boolean isDestroyed = false;

    public Projectile(float positionX, float positionY, int damage, Ennemy target) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionFinaleX = target.getX();
        this.positionFinaleY = target.getY();
        this.damage = damage;
        this.target = target;
    }

    public void goToNextPosition(){
        if (Level.ennemies.contains(target)) {
            positionFinaleX = target.getX();
            positionFinaleY = target.getY();

        if(positionX-positionFinaleX<0) {
            positionY = functionXtoY(positionX + 50);
            positionX = positionX + 50;
        }else{
            positionY = functionXtoY(positionX - 50);
            positionX = positionX - 50;
        }
        if(distanceFromAtoB() < 50){
            this.inflictDamage();
        }}else{
            isDestroyed = true;
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
