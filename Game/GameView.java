package Game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends UserView implements StepListener {

    private GameWorld world;
    private final Image fullbackground = new ImageIcon("data/background/fullbackground.png").getImage();
    private double cloudx, cloud2x, tree1x, tree2x, mountain1x, mountain2x;
    private Graphics2D g = new draw();
    private final Image [] doublejumpImages = {new ImageIcon("data/powerups/doublejump.png").getImage(),
            new ImageIcon("data/powerups/doublejump_black.png").getImage()};

    private static Image[] doublescoreImages = {new ImageIcon("data/powerups/doublescore0.png").getImage(),
            new ImageIcon("data/powerups/doublescore1.png").getImage(),
            new ImageIcon("data/powerups/doublescore2.png").getImage()};
    private final Image scorecounter = new ImageIcon("data/scorecounter.png").getImage();
    private final Image clouds = new ImageIcon("data/background/clouds.png").getImage();
    private final Image trees1 = new ImageIcon("data/background/trees1.png").getImage();
    private final Image trees2 = new ImageIcon("data/background/trees2.png").getImage();
    private final Image background = new ImageIcon("data/background/background.png").getImage();
    public GameView(GameWorld w){
        super(w, 1000, 500);
        world = w;
        world.addStepListener(this);
        cloudx = 0;
        cloud2x = 1000;
        tree1x = 0;
        tree2x = 1000;
        mountain1x = 0;
        mountain2x = 1000;
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, (int) mountain1x, 0,1000,500, this);
        g.drawImage(background, (int) mountain2x, 0, 1000, 500, this);
        g.drawImage(clouds, (int) cloudx, 0, 1000, 500, this);
        g.drawImage(clouds, (int) cloud2x, 0, 1000, 500, this);
        g.drawImage(trees1, (int) tree1x, 0, 1000, 500, this);
        g.drawImage(trees2, (int) tree2x, 0, 1000, 500, this);
        //g.drawImage(fullbackground, 0, 0, 1000, 500, this);

    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        if (world.getPlayer().doublejump) {
            g.drawImage(doublejumpImages[0], 900, 10, 90, 90, this);
        }else{
            g.drawImage(doublejumpImages[1], 900, 10, 90, 90, this);
        }

        //scorecounter
        g.drawImage(scorecounter, 425, 10, 150, 100, this);
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
            g.setFont(new Font("Arial", Font.PLAIN, 100));
            g.drawString("GameOver", 250, 250);
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            g.drawString("R to restart", 300, 300);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (world.getPlayer().reset){
            cloudx = 0;
            cloud2x = 1000;
            tree1x = 0;
            tree2x = 1000;
            mountain1x = 0;
            mountain2x = 1000;
        }
        //System.out.println(getMousePosition());
        if (world.getPlayer().gamerunning || world.getPlayer().getLinearVelocity().x > 0) {
            if (tree1x == -1000.0) {
                tree1x = 1000;
            }
            if (tree2x == -1000.0) {
                tree2x = 1000;
            }
            if (mountain1x < -1000.0) {
                mountain1x = 1000;
            }
            if (mountain2x < -1000.0) {
                mountain2x = 1000;
            }
            if (cloudx < -1000.0) {
                cloudx = 1000;
            }
            if (cloud2x < -1000.0) {
                cloud2x = 1000;
            }
            mountain1x -= 0.1;
            mountain2x -= 0.1;
            tree1x -= 0.5;
            tree2x -= 0.5;
            cloudx -= 0.2;
            cloud2x -= 0.2;
            paintBackground(g);
            paintForeground(g);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}

