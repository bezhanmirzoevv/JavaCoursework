package Game;;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public abstract class GameLevel extends World implements StepListener{
    private static Shape floor = new BoxShape(30, 1f);
    private StaticBody ground = new StaticBody(this, floor);
    private StaticBody ground2 = new StaticBody(this, floor);
    private Obstacles movingObstacles;
    public boolean levelcomplete = false;
    private boolean level1;
    private boolean level2;
    private Player player;
    private Collision collisionListener;
    private int savedscore;
    private int savedcredits;
    private Game game;
    public GameLevel(Game g, int playercredits, int playerScore, boolean level1complete, boolean level2complete){
        super();
        this.addStepListener(this);
        game = g;
        //make a ground platform
        ground.setName("ground");
        ground2.setName("ground");

        //initialising player
        player = new Player(this);
        player.setCredits(playercredits);
        player.setScore(playerScore);
        level1 = level1complete;
        level2 = level2complete;


        collisionListener = new Collision(player);
        player.addCollisionListener(collisionListener);

        movingObstacles = new Obstacles(this, player);

        //saving stats incase of reset on level
        this.savedcredits = playercredits;
        this.savedscore = playerScore;
    }

    public Player getPlayer(){
        return this.player;
    }

    public StaticBody getGround(){return this.ground;}
    public StaticBody getGround2(){return this.ground2;}
    public void level1Complete(){level1=true;}
    public void level2Complete(){level2=true;}
    public boolean islevel1Complete(){return level1;}
    public boolean islevel2Complete(){return level2;}

    public int[] getsavedStats(){return new int[]{this.savedscore, this.savedcredits};}

    @Override
    public void preStep(StepEvent stepEvent) {
        if (player.getPosition().x>29f){
            player.stopWalking();
            reinitialise();
            game.levelComplete();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

        if (player.getPosition().x < -30f){
            player.stopWalking();
            player.gameover = true;
            player.gamerunning = false;
        }
        if (player.getPosition().x > -15 && !levelcomplete && !player.paused && !player.gameover){
            player.stopWalking();
            player.setLinearVelocity(new Vec2(0, player.getLinearVelocity().y));
            player.gamerunning = true;
        }
        if (player.gamerunning){
            if (ground.getPosition().x < -54f){
                ground.setPosition(new Vec2(50f, ground.getPosition().y));
            }
            if (ground2.getPosition().x < -54f){
                ground2.setPosition(new Vec2(50f, ground2.getPosition().y));
            }
            ground.setPosition(new Vec2(ground.getPosition().x+player.getgamespeed(), ground.getPosition().y));
            ground2.setPosition(new Vec2(ground2.getPosition().x+player.getgamespeed(), ground2.getPosition().y));
        }
    }
    public void reset(){
        player.playerReset();
        movingObstacles.reset();
        game.getView().backgroundreset();
        game.getWorld().reinitialise();
        //this.getDynamicBodies().get(0).destroy();
        game.getFrame().repaint();
        game.getFrame().revalidate();
    }

    public abstract void reinitialise();
    public abstract Image[] getBackground();
    public BodyImage[] getPlatform(){
        return null;
    }
}
