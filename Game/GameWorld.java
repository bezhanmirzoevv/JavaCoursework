package Game;//package CourseworkGame;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class GameWorld extends World {

    private Player player;
    private Vec2 platform = new Vec2(-8f, -4f);
    public GameWorld(){
        super();

        //make a ground platform
        Shape floor = new BoxShape(30, 1f);
        StaticBody ground = new StaticBody(this, floor);
        ground.setPosition(new Vec2(0f, -11.5f));
        ground.setFillColor(Color.CYAN);

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(platform);

        //initialising player
        player = new Player(this);
        player.setPosition(new Vec2(-22f, -8.5f));

        //making obstacle
        Obstacle obstacle1 = new Obstacle(this);
        obstacle1.setPosition(obstacle1.getPostition());
        obstacle1.setFillColor(Color.MAGENTA);
    }

    public Player getPlayer(){
        return this.player;
    }
    public void movePlatform(){
        this.platform.x -= 1f;
    }
}
