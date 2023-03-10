package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class powerupTimer implements ActionListener {
    private int duration = 10000;
    public Player player;
    private String powerup;
    private Timer timer = new Timer(duration, this);
    public powerupTimer(Player p, String s){
        this.player = p;
        this.powerup = s;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (powerup == "doublescore"){
            player.scoremultiplier = 1;
        }else{

        }
    }
    public void starttimer(){
        timer.start();
        timer.setRepeats(false);
    }
}
