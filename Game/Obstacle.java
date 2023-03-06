package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Obstacle extends StaticBody implements ActionListener {
    private Timer timer = new Timer(500, this);
    public Vec2 position = new Vec2(10f, -9.5f);
    private static final Shape obstacle = new BoxShape(1,1);
    private static String[] Images = {"", "", ""};
    private static int imagePointer = 0;
    //private static BodyImage playerImage = new BodyImage(Images[imagePointer], 4);

    public Obstacle(World world) {
        super(world, obstacle);
        //this.addImage(playerImage);
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
        //playerImage = new BodyImage(Images[imagePointer]);
        //this.addImage(playerImage);
    }
}
