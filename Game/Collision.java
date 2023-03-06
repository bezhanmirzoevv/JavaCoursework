package Game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collision implements CollisionListener {
    private Player player;

    public Collision(Player p){
        player = p;
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Obstacle){
            player.gameover = true;
        }
    }

}
