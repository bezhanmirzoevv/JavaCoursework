package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    public static int width = 4;
    private static Shape Platform = new BoxShape(width,0.5f);;

    public Platform(GameLevel world) {
        super(world, Platform);
        if (world instanceof Level3){
            width = 2;
        }
        addImage(world.getPlatform()[0]);

    }
}
