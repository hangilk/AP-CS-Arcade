package org.sherwoodhs.Chesscapades;

import org.sherwoodhs.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ChessGame implements Game {
    public static void main(String args[]) {

    }

    @Override
    public void start() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)size.getHeight();

        JFrame frame = new org.sherwoodhs.Chesscapades.Game.ChessGame(height-200);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/org/sherwoodhs/Chesscapades/resources/wKnight.png"));
        frame.pack();
        frame.setTitle("Chesscapades");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void stop() {
    }

    @Override
    public BufferedImage getThumbnail() {
        final String path = "/Chesscapades.png";
        return checkThumbnail(path);
    }

    @Override
    public String getName() {
        return "Chesscapades";
    }

    @Override
    public String getDescription() {
        return(convertToMultiline("A Java chess implementation with stockfish integration. Press space to make stockfish play the best move. Note that stockfish ONLY works on machines with Windows and it needs a second to think; so spamming the spacebar will break it. \n\nBy: Abigail Ard, Conner McKeone, and Dean Leon"));
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}
