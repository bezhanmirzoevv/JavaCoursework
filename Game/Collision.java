package Game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collision implements CollisionListener {
    private Player player;

    public Collision(Player p){
        player = p;
    }

    public void collide(CollisionEvent collisionEvent) {
        System.out.println("Collision with " + collisionEvent.getOtherBody());
        if (collisionEvent.getOtherBody() instanceof Platform || collisionEvent.getOtherBody().getName() == "ground"){
            player.jump = false;
        }
        if (collisionEvent.getOtherBody() instanceof Obstacle){
            player.gamerunning = false;
        }else{
            System.out.println("fml");
        }
    }

}
