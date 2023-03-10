package Game;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Timer;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    public Player player;
    private GameWorld world;
    private powerupTimer doublescoreTimer;

    public Controller(Player player, GameWorld world){
        this.player = player;
        this.world = world;
        doublescoreTimer = new powerupTimer(player, "doublescore");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent keys) {
        int key = keys.getKeyCode();
        if (key == KeyEvent.VK_D) {
            //System.out.println(player.getPosition().x);
            if (player.getPosition().x < -15){
                player.startWalking(5);
                player.timer.start();
            }
        } else if (key == KeyEvent.VK_SPACE){
            player.jump = true;
            player.jump(20);
            /*if (player.getLinearVelocity().y !=0 && player.getPosition().y >-7 && player.doublejump &&
                    player.getLinearVelocity().y !=-1.1920928E-8 && player.getLinearVelocity().y !=-1.7881394E-8){*/
            if (player.getLinearVelocity().y !=0 && player.getBodiesInContact().size() == 0 && player.doublejump){
                player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, 20));
                player.doublejump = false;
            }
        } else if (key == KeyEvent.VK_S){
            /*player.sliding = true;
            player.rotate((float)Math.toRadians(-90));
            player.applyForce(new Vec2(10, 0));*/
        } else if (key == KeyEvent.VK_1){
            if (player.getCredits()>=5) {
                player.scoremultiplier = 2;
                doublescoreTimer.starttimer();
                player.setCredits(5);
            }
        }else if (key == KeyEvent.VK_R) {
            if (player.gameover) {
                world.reset();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keys){
        int key = keys.getKeyCode();
        //System.out.println("Key released " + keys.getKeyChar());
        if (key == KeyEvent.VK_D) {
            player.stopWalking();
        } else if (key == KeyEvent.VK_A){
            player.stopWalking();
        } else if (key == KeyEvent.VK_SPACE){
            //player.jump = false;
        } else if (key == KeyEvent.VK_S){
            player.sliding = false;
        }
    }
}

