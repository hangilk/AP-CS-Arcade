package org.sherwoodhs.CardsOfFate.Cards;

import org.sherwoodhs.CardsOfFate.Entities.Deck;

public class World extends Card{
    public World(){
        super("The World");
    }
    public void effect(Deck deck, Battle battle){
    }
    public String entry(){
        return(" ");
    }
    public String used(){
        return(" ");
    }
}
