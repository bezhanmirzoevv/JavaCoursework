package Game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    private final Image backgroundImage = new ImageIcon("").getImage();
    public GameView(GameWorld world){
        super(world, 1000, 500);
    }

}
