package Game;

import city.cs.engine.DebugViewer;
import city.cs.engine.SimulationSettings;

import javax.swing.*;
import java.sql.SQLOutput;

public class Game {
    public Game() {


        GameWorld world = new GameWorld();
        GameView view = new GameView(world);

        //controlling player
        Controller controller = new Controller(world.getPlayer(), world);
        view.addKeyListener(controller);

        //view.addMouseListener(new GameView(view));
        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);


        final JFrame frame = new JFrame("Chicken Runner Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        SimulationSettings settings = new SimulationSettings(64);

        //Debugging view
        //JFrame debugView = new DebugViewer(world, 1000, 500);

        world.start();
        view.requestFocus();
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
