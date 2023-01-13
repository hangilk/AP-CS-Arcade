package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.Card;
import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import java.util.Random;

public class Battle extends Menu{
    private Player player = Player.getInstance();
    private Encyclopedia encyclopedia = Encyclopedia.getInstance();
    private Enemy enemy;

    private Random r = new Random();

    // Atk / Dfn per turn
    private int[] enemyMove = {0,0};
    private int[] playerMove = {0,0};

    public Battle (Enemy enemy){
        this.enemy = enemy;
    }

    public void combat() {
        while (!player.isDead() && !enemy.isDead()){
            enemyMove();

            // Player move
            player.drawCard(3);
        }
    }
    
    private void enemyMove() {
        // Enemy moveset
        int a = r.nextInt();
        if (a == 0){ //Attacks
           enemyMove[0] = enemy.getAtk();
        } else { //Defends
            enemyMove[1] = enemy.getDfn();
        }
    }

    private void checkDiscard(){
        System.out.println("Discard Pile:\n" + player.getDiscard());
    }
    private void checkCombos(){

    }
    private void checkHand(){
        
    }
    private void useCard(Card card){

    }
}