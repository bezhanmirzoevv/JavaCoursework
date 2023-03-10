package Game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class Collision implements CollisionListener {
    private Player player;

    public Collision(Player p){
        player = p;
    }

    public void collide(CollisionEvent collisionEvent) {
        //System.out.println("Collision with " + collisionEvent.getOtherBody());
        if (collisionEvent.getOtherBody() instanceof Platform || collisionEvent.getOtherBody().getName() == "ground"){
            player.jump = false;
        }
        if(collisionEvent.getOtherBody() instanceof coin){
            collisionEvent.getOtherBody().destroy();
            player.increaseCredits();
        }
        if (collisionEvent.getOtherBody() instanceof Obstacle){
            player.gamerunning = false;
            player.stopWalking();
            player.gameover = true;
            collisionEvent.getOtherBody().setPosition(new Vec2(29f, -9.5f));
        }else{
            //System.out.println("fml");
        }
    }

}
