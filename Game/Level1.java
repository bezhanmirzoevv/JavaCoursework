package Game;

import city.cs.engine.BodyImage;

public class Level1 extends GameLevel{
    private static BodyImage floorimage = new BodyImage("data/floor.png", 2);
    public Level1(){
        super();
        ground.addImage(floorimage);
        ground2.addImage(floorimage);
    }
    public boolean isComplete(){
        if (getPlayer().getScore()>200)
            return true;
        else return false;
    }
}
