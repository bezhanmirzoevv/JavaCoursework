package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    public Player player;
    private stepListener stepListener;
    private GameWorld world;

    public Controller(Player player, GameWorld world){
        this.player = player;
        this.world = world;
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
            System.out.println(player.getPosition().y);
            if (player.getPosition().y < -8f) {
                player.jump(27);
            }else{
                player.jump(20);
            }
        } else if (key == KeyEvent.VK_S){
            player.sliding = true;
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

