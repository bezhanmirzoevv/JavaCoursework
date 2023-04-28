package Game;;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World implements StepListener{
    private static BodyImage floorimage = new BodyImage("data/floor.png", 2);
    private static Shape floor = new BoxShape(30, 1f);
    private StaticBody ground = new StaticBody(this, floor);
    private StaticBody ground2 = new StaticBody(this, floor);
    private boolean gameStarted = false;

    private Player player;
    public GameLevel(){
        super();
        this.addStepListener(this);

        //make a ground platform
        ground.setName("ground");
        ground2.setName("ground");

        //initialising player
        player = new Player(this);

        Collision collisionListener = new Collision(player);
        player.addCollisionListener(collisionListener);
    }

    public Player getPlayer(){
        return this.player;
    }

    public StaticBody getGround(){return this.ground;}
    public StaticBody getGround2(){return this.ground2;}
    public void startRound(){this.gameStarted = true;}
    public boolean isGameStarted(){return this.gameStarted;}

    @Override
    public void preStep(StepEvent stepEvent) {}

    @Override
    public void postStep(StepEvent stepEvent) {
        //System.out.println("working");
        if (player.getPosition().x < -30f){
            player.stopWalking();
            player.gameover = true;
            player.gamerunning = false;
        }
            if (player.gamerunning){
                if (ground.getPosition().x < -54f){
                    ground.setPosition(new Vec2(50f, ground.getPosition().y));
                }
                if (ground2.getPosition().x < -54f){
                    ground2.setPosition(new Vec2(50f, ground2.getPosition().y));
                }
                ground.setPosition(new Vec2(ground.getPosition().x+player.gameSpeed, ground.getPosition().y));
                ground2.setPosition(new Vec2(ground2.getPosition().x+player.gameSpeed, ground2.getPosition().y));
            }
    }
    public void reset(){
        player.reset = true;
        player.playerReset();
        player.setPosition(new Vec2(-22f, -8.5f));
        ground.setPosition(new Vec2(0f, -11.5f));
        ground2.setPosition(new Vec2(50f, -11.5f));
        this.getDynamicBodies().get(0).destroy();
    }

}
