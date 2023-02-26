package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    public Player player;
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
        System.out.println("Key pressed " + keys.getKeyChar());
        if (key == KeyEvent.VK_D) {
            //player.startWalking(5);
            world.gameRunning();
        } else if (key == KeyEvent.VK_A){
            player.startWalking(-5);
        } else if (key == KeyEvent.VK_SPACE){
            player.jump(15);
        }
    }

    @Override
    public void keyReleased(KeyEvent keys){
        int key = keys.getKeyCode();
        System.out.println("Key released " + keys.getKeyChar());
        if (key == KeyEvent.VK_D) {
            player.stopWalking();
        } else if (key == KeyEvent.VK_A){
            player.stopWalking();
        }
    }
}

