package com.foxfail;

import java.io.Serializable;
import java.util.Random;

public class Creature implements Serializable {
    public String Name;
    public int KD, STOY, REAK, VOLYA;
    public int maxHits, currentHits;
    public int mainAttakVS, mainAttakBonus, mainAttakDamageDice, mainAttakDamageBonus;
    public int initiate;

    public Creature (String name, int KD, int STOY, int REAK, int VOLYA, int maxHits, int mainAttakVS, int mainAttakBonus, int mainAttakDamageDice, int mainAttakDamageBonus, int initiate) {
        Name = name;
        this.KD = KD;
        this.STOY = STOY;
        this.REAK = REAK;
        this.VOLYA = VOLYA;
        this.maxHits = maxHits;
        this.currentHits = maxHits;
        this.mainAttakVS = mainAttakVS;
        this.mainAttakBonus = mainAttakBonus;
        this.mainAttakDamageDice = mainAttakDamageDice;
        this.mainAttakDamageBonus = mainAttakDamageBonus;
        this.initiate = initiate;
    }

    public Creature(){
        super();
    }

    public void figth(Creature enemy) {
        Random random = new Random();
        int d20 = random.nextInt((20) + 1);

        int ATK = d20 + mainAttakBonus;
        int DEF = 500;
        switch (mainAttakVS) {
            case 1:
                DEF = enemy.KD;
                break;
            case 2:
                DEF = enemy.STOY;
                break;
            case 3:
                DEF = enemy.REAK;
                break;
            case 4:
                DEF = enemy.VOLYA;
                break;
        }
        if (ATK >= DEF){
            int DMG = random.nextInt((mainAttakDamageDice - 1) + 1) + 1;
            DMG = DMG + mainAttakDamageBonus;
            enemy.currentHits = enemy.currentHits - DMG;
        }
    }

    public boolean isAlive(){
        if(currentHits >0) return true;
        else return false;
    }
}
