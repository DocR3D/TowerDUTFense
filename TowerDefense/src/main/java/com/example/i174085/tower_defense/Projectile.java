package com.example.i174085.tower_defense;

import android.util.Log;

public class Projectile {


    float positionX;
    float positionY;


    float positionFinaleX;
    float positionFinaleY;

    int damage;

    Ennemy target;

    public Projectile(float positionX, float positionY, int damage, Ennemy target) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionFinaleX = target.getX();
        this.positionFinaleY = target.getY();
        this.damage = damage;
        this.target = target;
    }

    public void goToNextPosition(){
        if(positionX-positionFinaleX>0) {
            positionY = functionXtoY(positionX + 1);
            positionX = positionX + 1;
        }else{
            positionY = functionXtoY(positionX - 1);
            positionX = positionX - 1;
        }
        if(distanceFromAtoB() < 0.05){
        }
    }

    public float functionXtoY(float x){
       float a = (positionX - positionY)/(positionFinaleX-positionFinaleY);
       float b = positionY - positionX * a;
       float y = a*x + b;
       return y;
    }
    public float functionYtoX(float y){
        float a = (positionX - positionY)/(positionFinaleX-positionFinaleY);
        float b = positionY - positionX * a;
        float x = (y-b)/a;
        return x;
    }

    public double distanceFromAtoB(){
        return Math.sqrt(Math.pow(positionFinaleX- positionX,2) + Math.pow(positionFinaleY - positionFinaleX,2));
    }



}
