package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class coin extends DynamicBody implements ActionListener, StepListener {
    private final static Shape coinShape = new BoxShape(1.5f,2f);
    private static int imagePointer = 0;
    private Player player;
    public Timer timer = new Timer(200, this);
    private static String[] runImages = {"data/powerups/coin_rotate0.png", "data/powerups/coin_rotate1.png",
            "data/powerups/coin_rotate2.png", "data/powerups/coin_rotate3.png", "data/powerups/coin_rotate4.png",
            "data/powerups/coin_rotate5.png"};
    private static BodyImage image = new BodyImage(runImages[imagePointer], 2);
    public coin(World w, Player p) {
        super(w, coinShape);
        this.addImage(image);
        w.addStepListener(this);
        timer.start();
        player = p;
    }

    public void increasepointer() {
        if (imagePointer == runImages.length - 1) {
            imagePointer = 0;
        } else {
            imagePointer += 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        increasepointer();
        this.removeAllImages();
        image = new BodyImage(runImages[imagePointer], 2);
        this.addImage(image);
    }

    @Override
    public void preStep(StepEvent stepEvent) {}

    @Override
    public void postStep(StepEvent stepEvent) {
        if (player.gamerunning) {
            this.setPosition(new Vec2(this.getPosition().x + player.gameSpeed, this.getPosition().y));
        }
    }
}
