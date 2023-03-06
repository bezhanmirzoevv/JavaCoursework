package Game;

import city.cs.engine.StaticBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Obstacles implements StepListener, ActionListener {
    private World world;
    private Player player;
    private float speed = -0.1f;
    private Timer timer = new Timer(500, this);
    private int i = 0;
    private int j = 0;
    private Random random = new Random();
    private List<StaticBody> activeObstacles = new ArrayList<StaticBody>();
    private Obstacle obstacle1, obstacle2, obstacle3, obstacle4;
    private Platform platform1, platform2;

    public Obstacles(World w, Player p) {
        super();
        player = p;
        w.addStepListener(this);
        world = w;
        timer.setRepeats(false);

        //initilising obstacles
        obstacle1 = new Obstacle(world);
        obstacle1.setPosition(new Vec2(29f, -9.5f));
        obstacle2 = new Obstacle(world);
        obstacle2.setPosition(new Vec2(29f, -9.5f));
        obstacle3 = new Obstacle(world);
        obstacle3.setPosition(new Vec2(29f, -9.5f));
        obstacle4 = new Obstacle(world);
        obstacle4.setPosition(new Vec2(29f, -9.5f));

        //initilising platforms
        platform1 = new Platform(world);
        platform1.setPosition(new Vec2(29f, -4f));
        platform2 = new Platform(world);
        platform2.setPosition(new Vec2(29f, -4f));
    }
    @Override
    public void preStep(StepEvent stepEvent) {
        if (player.running){
            //random spawn of obstacles
            if (random.nextInt(128) == 1) {
                System.out.println("new obstacle");
                timer.start();
            //random spawn of platforms
            }else if(random.nextInt(128) == 1) {
                if (j == 0) {
                    activeObstacles.add(platform1);
                    j++;
                } else if (j == 1) {

                    activeObstacles.add(platform2);
                    j = 0;
                }
            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (player.running){
            for (int i = 0; i < activeObstacles.size(); i++){
                activeObstacles.get(i).setPosition(new Vec2(activeObstacles.get(i).getPosition().x +speed,
                        activeObstacles.get(i).getPosition().y));
                if (activeObstacles.get(i).getPosition().x < -18f){
                    activeObstacles.get(i).setPosition(new Vec2(29f, activeObstacles.get(i).getPosition().y));
                    activeObstacles.remove(i);
                    i-=1;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (i == 0) {
            activeObstacles.add(obstacle1);
            i++;
        } else if (i == 1) {
            activeObstacles.add(obstacle2);
            i++;
        } else if (i == 2) {
            activeObstacles.add(obstacle3);
            i++;
        } else if (i == 3) {
            activeObstacles.add(obstacle4);
            i = 0;
        }
        timer.stop();
    }
}
