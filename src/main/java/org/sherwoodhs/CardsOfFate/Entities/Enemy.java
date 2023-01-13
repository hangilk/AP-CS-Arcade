package org.sherwoodhs.CardsOfFate.Entities;

import java.util.Random;

public abstract class Enemy extends Person{
    protected int minAtk;
    protected int atkRange;

    protected int minDfn;
    protected int dfnRange;
    Random r = new Random();

    public Enemy(String name, int hp, int minAtk, int atkRange){
        super(name,hp);
        this.atkRange = atkRange;
        this.minAtk = minAtk;
    }

    public int getAtk(){
        return (r.nextInt(atkRange) + minAtk);
    }

    public int getDfn(){
        return (r.nextInt(dfnRange) + minDfn);
    }
}