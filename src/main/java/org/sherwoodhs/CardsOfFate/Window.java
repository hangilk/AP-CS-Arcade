package org.sherwoodhs.CardsOfFate;

import org.sherwoodhs.CardsOfFate.Cards.Card;
import org.sherwoodhs.CardsOfFate.Entities.Enemy;
import org.sherwoodhs.CardsOfFate.Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements ActionListener, ItemListener{
    private static Battle battle;
    private Player player = Player.getInstance();
    private static JFrame frame = new JFrame("Cards Of Fate");
    private static CardLayout crd =  new CardLayout();
    private static JPanel superPanel = new JPanel(crd);
    //
    private JPanel text = new JPanel();
    private static JLabel words = new JLabel("You've reached an area with no new content.");
    //
    private JPanel battles = new JPanel();
    private static JLabel battleText = new JLabel("text");
    private static JLabel battleText2 = new JLabel("text");
    private static JLabel battleText3 = new JLabel("text");
    private static JLabel battleText4 = new JLabel("text");
    private static JLabel battleText5 = new JLabel("text");
    private static JLabel battleText6 = new JLabel("text");
    private static String[] choices = {"Use Card", "Use Combo", "End Turn"};
    private JComboBox battleChoices = new JComboBox(choices);
    private JComboBox options = new JComboBox(player.getHand2());
    private JButton use = new JButton("GO");

    private JList discard = new JList(player.getDiscard2());
    private JLabel discardTitle = new JLabel("Discard Pile");
    Card c = player.getHand().get(0);
    private JLabel description = new JLabel(c.entry());
    private JLabel description2 = new JLabel(" ");

    //
    private JPanel victory = new JPanel();
    private static JLabel vicLabel = new JLabel("");
    //
    private JPanel loss = new JPanel();
    private static JLabel lossLabel = new JLabel("You loss. Fool.");
    //
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem menuItem1 = new JMenuItem("Quit?");
    private JMenu pauseMenu = new JMenu("⚙");

    public Window() {
        //tutorial box
        int n = JOptionPane.showConfirmDialog(frame, "Do you want to skip the Tutorial?", " Starting Game", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION){
            Run.tutorial();
        } else{
            // Random new stuff
        }

        frame.getContentPane();
        frame.setDefaultLookAndFeelDecorated(true);

            text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

                    pauseMenu.add(menuItem1);
                    menuItem1.addActionListener(this);
                    pauseMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                menuBar.add(pauseMenu);
                menuBar.setBackground(new Color(213, 203, 203));
                menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

                text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
                text.setAlignmentX(Component.LEFT_ALIGNMENT);
                text.add(words);

                    battleChoices.setSelectedIndex(0);
                    battleChoices.addItemListener(this);
                    battleChoices.setEditable(false);
                    options.setSelectedIndex(0);
                    options.addItemListener(this);
                    options.setEditable(false);

                battles.setLayout(null);
                    battleText.setBounds(10,10,400,15);
                battles.add(battleText);
                    battleText2.setBounds(10,25,400,15);
                battles.add(battleText2);
                    battleText3.setBounds(10,40,400,15);
                battles.add(battleText3);
                    battleText4.setBounds(10,70,400,15);
                battles.add(battleText4);
                     battleText5.setBounds(10,85,400,15);
                battles.add(battleText5);
                    battleText6.setBounds(10,115,400,15);
                battles.add(battleText6);
                    battleChoices.setBounds(10,145,150,20);
                battles.add(battleChoices);
                    options.setBounds(180,145,150,20);
                battles.add(options);
                    use.setBounds(350, 145,60,20);
                    use.setFont(new Font("Arial", Font.BOLD,15));
                    use.setBackground(new Color(224, 135, 135));
                    use.addActionListener(this);
                battles.add(use);
                    description.setBounds(10,180,400,15);
                battles.add(description);
                    description2.setBounds(10,195,400,15);
                battles.add(description2);
                    discardTitle.setBounds(430,10,110,20);
                battles.add(discardTitle);
                    discard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    discard.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                    discard.setBounds(430,40,110,300);
                battles.add(discard);
                    //JScrollPane listScroller = new JScrollPane(discard);
                    //listScroller.setPreferredSize(new Dimension(250, 80));

                victory.add(vicLabel);

                loss.add(lossLabel);
                loss.setLayout(new BoxLayout(loss, BoxLayout.Y_AXIS));
                loss.setAlignmentX(Component.CENTER_ALIGNMENT);
            superPanel.add("TEXT",text);
            superPanel.add("BATTLE",battles);
            superPanel.add("VICTORY", victory);
            superPanel.add("LOSS", loss);

            Color back = new Color(220, 212, 191);
            text.setBackground(back);
            battles.setBackground(back);
            victory.setBackground(back);
            loss.setBackground(back);
        frame.add(superPanel);
        frame.addKeyListener(new enterKey());
        frame.setJMenuBar(menuBar);

        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setFocusable(true);
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == menuItem1){
            // Quit Game
        } else if (e.getSource() == use){
            String choice = (String) battleChoices.getSelectedItem();
            if (choice == "Use Card"){
                int a = options.getSelectedIndex();
                if (a != -1) {
                    Card cards = player.getHand().get(a);
                    player.useCard(cards, battle);
                    updateOptions();
                }
            } else if (choice == "End Turn"){
                System.out.println(2);
                battle.endTurn();
                battleChoices.setSelectedIndex(0);
                updateOptions();
                options.setSelectedIndex(0);
            }
            battle.checkDead();
        }

    }
    private void updateOptions(){
        options.removeAllItems();
        Card[] a = player.getHand2();
        for (Card element : a) {
            options.addItem(element);
        }

    }
    public void itemStateChanged (ItemEvent e){
        String choice = (String) battleChoices.getSelectedItem();
        if (choice == "Use Card"){
            options.setVisible(true);
            int a = options.getSelectedIndex();
            if(a != -1) {
                Card cards = player.getHand().get(a);
                description.setText(cards.entry());
                description2.setText(" ");
            } else {
                description.setText("You have no cards to use.");
                description2.setText(" ");
            }

        } else if (choice == "Use Combo"){
            options.setVisible(false);
        } else if (choice == "End Turn"){
            description.setText("");
            description.setText("");
        }
    }
    public static void setLabel(String string){
        words.setText(string);
    }
    public static void changeBattleText(String enemyName, int enemyHP, int enemyAtk, int enemyDfn, int playerHP, int playerAtk, int playerDfn, String otherText){
        battleText.setText("You are fighting a " + enemyName + ".");
        battleText2.setText("It has " + enemyHP + " hp remaining.");
        battleText3.setText("On it's next turn, it'll attack for " + enemyAtk + " and defend for " + enemyDfn + ".");
        battleText4.setText("You have " + playerHP + " hp remaining.");
        battleText5.setText("Next turn, you'll attack for " + playerAtk + " and defend for " + playerDfn + ".");
        battleText6.setText(otherText);
    }
    public static void changeCard(String string) {
        crd.show(superPanel, string);
    }
    public static void setBattle(Enemy enemy) {
        battle = new Battle(enemy);
        battle.start();
        changeCard("BATTLE");

    }
    public static void setVictory(Enemy enemy){
        changeCard("VICTORY");
        vicLabel.setText("You beat a " + enemy.getName() + ". Good job.");
    }
    public static void setLoss(Enemy enemy){
        changeCard("LOSS");
        lossLabel.setText("It's a very sad day. You lost to a " + enemy.getName() + ".");
    }
    class enterKey implements KeyListener{
        public void keyTyped(KeyEvent e){

        }
        public void keyPressed(KeyEvent e){

            if(e.getKeyCode() == KeyEvent.VK_ENTER){

                Dialouge.advanceText();
            }
        }
        public void keyReleased(KeyEvent e){

        }
    }
}
