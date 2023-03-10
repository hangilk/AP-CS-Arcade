package org.sherwoodhs.CardsOfFate.Combos;

import org.sherwoodhs.CardsOfFate.Battle;
import org.sherwoodhs.CardsOfFate.Cards.Fool;
import org.sherwoodhs.CardsOfFate.Cards.Swords;
import org.sherwoodhs.CardsOfFate.Cards.Card;
import org.sherwoodhs.CardsOfFate.Entities.Deck;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import java.util.Random;

public class FoolSword extends Combo{
    private static FoolSword combo = new FoolSword(Fool.getInstance(),new Swords(1));
    private FoolSword(Card... cost){
        super(cost);
        name = "A Fool With a Sword.";
    }
    public static FoolSword getInstance(){
        return(combo);
    }
    public void use(Deck deck, Battle battle){
        useCost(deck, battle);
        Random r = new Random();
        int f = r.nextInt(4);
        if (f == 0){
            Player.getInstance().changeHealth(-1);
            battle.updateText("You hurt yourself for 1 damage");
        } else {
            battle.changePlayerAtk(5);
            battle.updateText("You will attack for 5.");
        }
    }
    public String effectDescription(){
        return("Has a 25% chance of self-harm for 1 damage.");
    }
    public String effectDescription2(){
        return("Has a 75% chance of attacking for 5 damage.");
    }
}