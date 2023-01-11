package org.sherwoodhs.CardsOfFate;

import java.util.Scanner;

import static org.sherwoodhs.CardsOfFate.Encyclopedia.encyclopedia;

public class Menu {
    Scanner in = new Scanner(System.in);
    public void menu() {
        System.out.println("Game Paused.\n\n(1)Encyclopedia\n\n(0)Quit?\n\nEnter the Corresponding Number");
        boolean loop = true;
        int number = 0;
        while (loop){
            try {
                number = Integer.parseInt(in.nextLine());
                if(selectOption(number)) {
                    loop = false;
                }
            } catch (Exception e) {

            }
        }
    }
    public boolean selectOption(int number){
        switch (number){
            case 0: encyclopedia(); return(true);
            case 1: quit(); return(true);
            default: return(false);
        }

    }
    private void encyclopedia(){
        encyclopedia.openEncyclopedia();
    }
    private void quit(){
        //Accesses CardsOfFate.java and its EndGame(); method
    }
}
