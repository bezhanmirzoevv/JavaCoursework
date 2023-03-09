package Game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView implements StepListener {

    private GameWorld world;
    private final Image fullbackground = new ImageIcon("data/background/fullbackground.png").getImage();
    private double cloudx, cloud2x, tree1x, tree2x, mountain1x, mountain2x;
    private Graphics2D g = new draw();
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

    }

    @Override
    public void preStep(StepEvent stepEvent) {
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
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}

