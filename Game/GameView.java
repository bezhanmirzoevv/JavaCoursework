package Game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView implements StepListener {

    private final Image fullbackground = new ImageIcon("data/background/fullbackground.png").getImage();
    private final Image background = new ImageIcon("data/background/background.png").getImage();
    public GameView(GameWorld world){
        super(world, 1000, 500);
        world.addStepListener(this);
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(fullbackground, 0, 0,1000,500, this);

    }
    @Override
    protected void paintForeground(Graphics2D g){

    }

    @Override
    public void preStep(StepEvent stepEvent) {}

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}

