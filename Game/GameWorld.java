package Game;;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class GameWorld extends World {

    private Player player;
    private Obstacle obstacle1 = new Obstacle(this);;
    private StaticBody platform1;
    public GameWorld(){
        super();

        //make a ground platform
        Shape floor = new BoxShape(30, 1f);
        StaticBody ground = new StaticBody(this, floor);
        ground.setPosition(new Vec2(0f, -11.5f));
        ground.setFillColor(Color.CYAN);

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8f, -4f));

        //initialising player
        player = new Player(this);
        player.setPosition(new Vec2(-22f, -8.5f));

        //making obstacle

        obstacle1.setPosition(obstacle1.position);
        obstacle1.setFillColor(Color.MAGENTA);
    }

    public Player getPlayer(){
        return this.player;
    }

    public void gameRunning(){
        this.obstacle1.startWalking(-5);
        this.platform1.move(new Vec2(-0.5f, 0));
    }
}
