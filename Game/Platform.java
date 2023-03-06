package Game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    public static int width = 4;
    public Vec2 position = new Vec2(-8f, -4f);
    private static Shape Platform = new BoxShape(width,0.5f);

    //private static final BodyImage playerImage = new BodyImage("data/student.png", 4);

    public Platform(World world) {
        super(world, Platform);
        //addImage(playerImage);
    }
}
