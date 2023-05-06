package Game;


import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Level3 extends GameLevel {

    private static BodyImage floorimage = new BodyImage("data/Level3Background/Floor.png", 2);
    private static final BodyImage Platform = new BodyImage("data/Level3Background/Platform.png", 1);
    private final Image background1 = new ImageIcon("data/Level3Background/Background1.png").getImage();
    private final Image background2 = new ImageIcon("data/Level3Background/Background2.png").getImage();
    private final Image background3 = new ImageIcon("data/Level3Background/Background3.png").getImage();

    public Level3(Game g, int playercredits, int playerScore,boolean level1complete, boolean level2complete){
        super(g,playercredits,playerScore ,level1complete,level2complete);

        //setting the ground
        getGround().setPosition(new Vec2(0f, -11.5f));
        getGround2().setPosition(new Vec2(50f, -11.5f));
        getGround().addImage(floorimage);
        getGround2().addImage(floorimage);

        //player
        getPlayer().setPosition(new Vec2(-22f, -8.5f));
    }

    @Override
    public void reinitialise(){
        getGround().addImage(floorimage);
        getGround2().addImage(floorimage);
        getPlayer().setPosition(new Vec2(-22f, -8.5f));
    }

    @Override
    public Image[] getBackground(){
        return new Image[]{background1, background2, background3, background3};
    }

    @Override
    public BodyImage[] getPlatform(){
        return new BodyImage[]{Platform};
    }
    public void nexLevel(){
        if (getPlayer().getScore()>100){

        }
    }
}
