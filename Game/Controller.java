package Game;

import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    public Player player;
    private GameLevel world;
    private powerupTimer doublescoreTimer;

    public Controller(Player player, GameLevel world){
        this.player = player;
        this.world = world;
        doublescoreTimer = new powerupTimer(player, "doublescore");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent keys) {
        int key = keys.getKeyCode();
        switch(key){
            case KeyEvent.VK_D:
                if (player.getPosition().x < -15){
                    player.startWalking(5);
                    player.timer.start();
                }
                break;
            case KeyEvent.VK_SPACE:
                player.jump = true;
                player.jump(20);
                if (player.getLinearVelocity().y !=0 && player.getBodiesInContact().size() == 0 && player.doublejump){
                    player.setLinearVelocity(new Vec2(player.getLinearVelocity().x, 20));
                    player.doublejump = false;
                }
                break;
            case KeyEvent.VK_S:
                /*player.sliding = true;
                player.rotate((float)Math.toRadians(-90));
                player.applyForce(new Vec2(10, 0));*/
            case KeyEvent.VK_1:
                if (player.getCredits()>=5) {
                    player.scoremultiplier = 2;
                    doublescoreTimer.starttimer();
                    player.setCredits(5);
                }
                break;
            case KeyEvent.VK_R:
                if (player.gameover) {
                    world.reset();
                }
            case KeyEvent.VK_ENTER:
                if (player.getScore()>50){
                    //have player run off screen then start next level
                }
                break;
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

