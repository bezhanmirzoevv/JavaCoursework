package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Obstacle extends StaticBody {
    public Vec2 position = new Vec2(10f, -9.5f);
    private static final Shape obstacle = new BoxShape(1,1);

    //private static final BodyImage playerImage = new BodyImage("data/student.png", 4);

    public Obstacle(World world) {
        super(world, obstacle);
        //addImage(playerImage);
    }
}
