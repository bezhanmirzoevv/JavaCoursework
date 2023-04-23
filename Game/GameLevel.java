package Game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World {
    private Player player;
    private static Shape floor = new BoxShape(30, 1f);
    public StaticBody ground = new StaticBody(this, floor);
    public StaticBody ground2 = new StaticBody(this, floor);

    public GameLevel(){
        ground.setName("ground");
        ground2.setName("ground");
        ground.setPosition(new Vec2(0f, -11.5f));
        ground2.setPosition(new Vec2(50f, -11.5f));

        //initialising player
        player = new Player(this);
        player.setPosition(new Vec2(-22f, -8.5f));

        player.addCollisionListener(new Collision(player));
    }
    public Player getPlayer(){
        return this.player;
    }
    public abstract boolean isComplete();
}
