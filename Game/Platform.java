package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    public static int width = 4;
    private static Shape Platform = new BoxShape(width,0.5f);

    private static final BodyImage playerImage = new BodyImage("data/Level1Background/platform.png", 1);

    public Platform(GameLevel world) {
        super(world, Platform);
        addImage(world.getPlatform()[0]);

    }
}
