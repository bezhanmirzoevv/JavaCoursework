package Game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Obstacle extends Walker {
    private Vec2 position = new Vec2(10f, -11.5f);
    private static final Shape obstacle = new BoxShape(1,1);

    //private static final BodyImage playerImage = new BodyImage("data/student.png", 4);

    public Obstacle(World world) {
        super(world, obstacle);
        //addImage(playerImage);
    }
    public Vec2 getPostition(){
        return this.position;
    }
}
