package Game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView implements StepListener {

    private GameLevel world;
    private final Image pausebackground = new ImageIcon("data/Screens/PauseScreen.png").getImage();
    private double background2x, background2_2x, background3x, background3_2x, background1x, background1_2x;
    private double gamespeed = 1;
    private Graphics2D g = new draw();
    private final Image [] doublejumpImages = {new ImageIcon("data/powerups/doublejump.png").getImage(),
            new ImageIcon("data/powerups/doublejump_black.png").getImage()};

    private static Image[] doublescoreImages = {new ImageIcon("data/powerups/doublescore0.png").getImage(),
            new ImageIcon("data/powerups/doublescore1.png").getImage(),
            new ImageIcon("data/powerups/doublescore2.png").getImage()};
    private final Image scorecounter = new ImageIcon("data/scorecounter.png").getImage();
    public GameView(GameLevel w){
        super(w, 1000, 500);
        world = w;
        world.addStepListener(this);
        background2x = 0;
        background2_2x = 1000;
        background3x = 0;
        background3_2x = 1000;
        background1x = 0;
        background1_2x = 1000;
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(world.getBackground()[0], (int) background1x, 0,1000,500, this);
        g.drawImage(world.getBackground()[0], (int) background1_2x, 0, 1000, 500, this);
        g.drawImage(world.getBackground()[1], (int) background2x, 0, 1000, 500, this);
        g.drawImage(world.getBackground()[1], (int) background2_2x, 0, 1000, 500, this);
        g.drawImage(world.getBackground()[2], (int) background3x, 0, 1000, 500, this);
        g.drawImage(world.getBackground()[3], (int) background3_2x, 0, 1000, 500, this);
        //g.drawImage(fullbackground, 0, 0, 1000, 500, this);

    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.setFont(new Font("Ninjastrike", Font.PLAIN, 30));
        if (world instanceof Level2 || world instanceof Level3) {
            g.setPaint(new Color(255, 255, 255));
        }
        if (world.getPlayer().doublejump) {
            g.drawImage(doublejumpImages[0], 900, 10, 90, 90, this);
        }else{
            g.drawImage(doublejumpImages[1], 900, 10, 90, 90, this);
        }

        //scorecounter
        g.drawImage(scorecounter, 425, 10, 150, 100, this);
        g.drawString("Score:", 455, 65);
        if (world.getPlayer().getScore()<10) {
            g.drawString(Integer.toString(world.getPlayer().getScore()), 490, 90);
        }else if (world.getPlayer().getScore() <100){
            g.drawString(Integer.toString(world.getPlayer().getScore()), 480, 90);
        }else if (world.getPlayer().getScore() > 99){
            g.drawString(Integer.toString(world.getPlayer().getScore()), 470, 90);
        }

        //credits counter
        g.drawString(Integer.toString(world.getPlayer().getCredits()), 65, 50);
        g.drawImage(new ImageIcon("data/powerups/coin_rotate0.png").getImage(), 10, 15, 50, 50, this);

        //powerup1
        if (world.getPlayer().scoremultiplier == 2) {
            g.drawImage(doublescoreImages[2], 800, 10, 90, 90, this);
        }else if (world.getPlayer().getCredits()<5){
            g.drawImage(doublescoreImages[0], 800, 10, 90, 90, this);
        }else if (world.getPlayer().getCredits()>=5) {
            g.drawImage(doublescoreImages[1], 800, 10, 90, 90, this);
        }
        if (world.getPlayer().gameover){
            g.setFont(new Font("Ninjastrike", Font.PLAIN, 100));
            g.drawString("GameOver", 300, 250);
            g.setFont(new Font("Ninjastrike", Font.PLAIN, 50));
            g.drawString("R to respawn", 350, 300);
        }
        if (world.getPlayer().paused){
            g.drawImage(pausebackground,0, 0,1000,500, this);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        //System.out.println(getMousePosition());
        if ((world.getPlayer().gamerunning || world.getPlayer().getLinearVelocity().x > 0) && !world.levelcomplete) {
            if (background3x == -1000.0) {
                background3x = 1000;
            }
            if (background3_2x == -1000.0) {
                background3_2x = 1000;
            }
            if (background1x < -1000.0) {
                background1x = 1000;
            }
            if (background1_2x < -1000.0) {
                background1_2x = 1000;
            }
            if (background2x < -1000.0) {
                background2x = 1000;
            }
            if (background2_2x < -1000.0) {
                background2_2x = 1000;
            }

            background1x -= 0.1;
            background1_2x -= 0.1;
            background3x -= 1;
            background3_2x -= 1;
            background2x -= 0.2;
            background2_2x -= 0.2;
            paintBackground(g);
            paintForeground(g);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (world.getPlayer().getgamespeed() == -1f){
            this.gamespeed = 1.7;
        }else if (world.getPlayer().getgamespeed() == -1.5f){
            this.gamespeed = 2;
        }else if (world.getPlayer().getgamespeed() == -2.5f) {
            this.gamespeed = 3;
        }
    }

    public void backgroundreset(){
        background2x = 0;
        background2_2x = 1000;
        background3x = 0;
        background3_2x = 1000;
        background1x = 0;
        background1_2x = 1000;
    }
}

