package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Obstacle extends StaticBody implements ActionListener {
    public Timer timer = new Timer(60, this);
    public static Vec2 position = new Vec2(10f, -9.5f);
    private final static Shape obstacle = new BoxShape(1,1);
    private static String[] Images = {"data/shiruken/shiruken0.png", "data/shiruken/shiruken1.png",
            "data/shiruken/shiruken2.png", "data/shiruken/shiruken3.png", "data/shiruken/shiruken4.png",
            "data/shiruken/shiruken5.png"};
    private static int imagePointer = 0;
    private static BodyImage playerImage = new BodyImage(Images[imagePointer], 4);

    public Obstacle(World world) {
        super(world, obstacle);
        this.addImage(playerImage);
    }

    public static void setImagePointer() {
        if (imagePointer == Images.length-1){
            imagePointer = 0;
        }else {
            imagePointer += 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setImagePointer();
        this.removeAllImages();
        playerImage = new BodyImage(Images[imagePointer], 4);
        this.addImage(playerImage);
    }
}
