package Game;


import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {
    private static BodyImage floorimage = new BodyImage("data/floor.png", 2);
    public Level1(){
        super();

        //setting the ground
        getGround().setPosition(new Vec2(0f, -11.5f));
        getGround2().setPosition(new Vec2(50f, -11.5f));
        getGround().addImage(floorimage);
        getGround2().addImage(floorimage);

        //player
        getPlayer().setPosition(new Vec2(-22f, -8.5f));
    }

    @Override
    public void strt(){
        getGround().addImage(floorimage);
        getGround2().addImage(floorimage);
    }
    public void nexLevel(){
        if (getPlayer().getScore()>100){

        }
    }
}
