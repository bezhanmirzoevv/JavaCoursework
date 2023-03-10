package Game;;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class GameWorld extends World implements StepListener{
    private static BodyImage floorimage = new BodyImage("data/floor.png", 2);
    private static Shape floor = new BoxShape(30, 1f);
    public StaticBody ground = new StaticBody(this, floor);
    public StaticBody ground2 = new StaticBody(this, floor);

    private Player player;
    public GameWorld(){
        super();
        this.addStepListener(this);

        //make a ground platform
        ground.addImage(floorimage);
        ground2.addImage(floorimage);
        ground.setName("ground");
        ground2.setName("ground");
        ground.setPosition(new Vec2(0f, -11.5f));
        ground2.setPosition(new Vec2(50f, -11.5f));


        //initialising player
        player = new Player(this);
        player.setPosition(new Vec2(-22f, -8.5f));

        Collision collisionListener = new Collision(player);
        player.addCollisionListener(collisionListener);
    }

    public Player getPlayer(){
        return this.player;
    }

    @Override
    public void preStep(StepEvent stepEvent) {}

    @Override
    public void postStep(StepEvent stepEvent) {
            if (player.gamerunning){
                if (ground.getPosition().x < -54f){
                    ground.setPosition(new Vec2(50f, ground.getPosition().y));
                }
                if (ground2.getPosition().x < -54f){
                    ground2.setPosition(new Vec2(50f, ground2.getPosition().y));
                }
                ground.setPosition(new Vec2(ground.getPosition().x+player.gameSpeed, ground.getPosition().y));
                ground2.setPosition(new Vec2(ground2.getPosition().x+player.gameSpeed, ground2.getPosition().y));
            }
    }
    public void reset(){
        player.reset = true;
        player.playerReset();
        player.setPosition(new Vec2(-22f, -8.5f));
        ground.setPosition(new Vec2(0f, -11.5f));
        ground2.setPosition(new Vec2(50f, -11.5f));
        this.getDynamicBodies().get(0).destroy();
    }
}
