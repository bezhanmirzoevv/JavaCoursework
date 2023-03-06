package Game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Walker implements ActionListener {
    private int score;
    public boolean gameover = false;

    private Timer timer = new Timer(500, this);
    public boolean gamerunning = false;
    private static final Shape player = new BoxShape(1,2);

    //private static final BodyImage playerImage = new BodyImage("data/student.png", 4);

    public Player(World world) {
        super(world, player);
        //addImage(playerImage);
        this.score = 0;
        this.setGravityScale(3);
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
