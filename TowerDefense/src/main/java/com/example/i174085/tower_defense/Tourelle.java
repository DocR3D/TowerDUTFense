package com.example.i174085.tower_defense;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import static com.example.i174085.tower_defense.MainActivity.joueur;

public class Tourelle {
    int damage=2;
    int range=300;
    int niveau=1;
    RelativeLayout laMap;
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 100);
    ImageButton uneCase;
    Context leContext;
    int X;
    int Y;


    public Tourelle(int X, int Y, RelativeLayout laMap, Context leContext){
        this.uneCase = new ImageButton(leContext);
        this.leContext = leContext;
        this.X = X*100;
        this.Y = Y*100;

        uneCase.setBackgroundResource(R.drawable.tourellelvlun);
        uneCase.setLayoutParams(params);
        uneCase.setX(X*100);
        uneCase.setY(Y*100);
        laMap.addView(uneCase);

        uneCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                améliorer();
            }
        });


    }

    private void améliorer() {
        if(joueur.getMoney()>=50) {
            joueur.looseMoney(50);

            damage = damage + 4 ;
            Log.e("Nouveau dégats : ", "" + damage);

            range=range+100;
            Log.e("Nouvelle Range : ", "" + range);
            if (niveau == 1) uneCase.setBackgroundResource(R.drawable.tourellelvldeux);
            if (niveau == 2) uneCase.setBackgroundResource(R.drawable.tourellelvltrois);
            niveau++;
        }
    }

    public void Attack(Ennemy theEnnemy){
        if(checkEnnemyInRange(theEnnemy)){
            Level.allTheProjectile.add(new Projectile(X,Y,damage,theEnnemy));
        }

    }
    private boolean checkEnnemyInRange(Ennemy theEnnemy){
        if(Math.sqrt((X- theEnnemy.getX())*(X - theEnnemy.getX()) + (Y - theEnnemy.getY())*(Y - theEnnemy.getY())) <= this.getRange()){
            return true;
        }

        return false;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
