package Game;;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class GameWorld extends World {

    private Player player;
    private Obstacle obstacle1, obstacle2, obstacle3;
    private StaticBody platform1;
    public GameWorld(){
        super();

        //make a ground platform
        Shape floor = new BoxShape(30, 1f);
        StaticBody ground = new StaticBody(this, floor);
        ground.setPosition(new Vec2(0f, -11.5f));
        ground.setFillColor(Color.CYAN);

        //initialising player
        player = new Player(this);
        player.setPosition(new Vec2(-22f, -8.5f));

        //making obstacle
        obstacle1 = new Obstacle(this);
        obstacle1.setPosition(obstacle1.position);
        obstacle1.setFillColor(Color.MAGENTA);

        Collision collisionListener = new Collision(player);
        player.addCollisionListener(collisionListener);
    }

    public Player getPlayer(){
        return this.player;
    }

}
