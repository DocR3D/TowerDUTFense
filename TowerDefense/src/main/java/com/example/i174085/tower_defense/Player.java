package com.example.i174085.tower_defense;

import android.content.Context;
import android.widget.TextView;

public class Player {
    int vague = 0;
    int live = 5;
    int money = 100;
    TextView nbVie;
    TextView nbArgent;
    TextView nbVague;

    Context leContext;
    Player(Context unContexte, TextView Vie, TextView Argent,TextView unevague){
        leContext = unContexte;
        nbArgent = Argent;
        nbVie = Vie;
        nbVague = unevague;
    }

    public int getVague()
    {
        return vague;
    }

    public void addVague() {
        this.vague++;
        updateText();
    }

    public int getLive() {
        return live;
    }


    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money+= money;
        updateText();
    }

    public void looseLive(){
        if(live>0) live--;
        updateText();

    }

    public void looseMoney(int i) {
        money-=i;
        updateText();
    }

    public void updateText() {
        nbArgent.setText("Argent :" + this.money);
        nbVie.setText("Vie : " + live);
        nbVague.setText("Vague " + this.vague);
    }
}
