package Game;

import city.cs.engine.SimulationSettings;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class Game {

    private GameView view;
    private Controller controller;
    private GameLevel world;
    private JFrame frame = new JFrame("Ninja Runner Game");
    private StartScreen startScreen;
    public Game() {
        World w = new World();
        world = new Level1();
        view = new GameView(world);

        Obstacles movingObstacles = new Obstacles(world, world.getPlayer());

        //controlling player
        controller = new Controller(world.getPlayer(), world);
        view.addKeyListener(controller);



        //view.addMouseListener(new GameView(view));
        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);

        //start screen
        startScreen = new StartScreen(world, this);

        //frame
        frame.add(startScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        SimulationSettings settings = new SimulationSettings(60);

        //Debugging view
        //JFrame debugView = new DebugViewer(world, 1000, 500);

        world.start();
        view.requestFocus();
    }


    public void startGame(){
        frame.remove(startScreen);
        frame.add(view);
        frame.revalidate();
        view.requestFocus();
    }
    public JFrame getFrame() {
        return frame;
    }

    public void setStartScreen() {
        frame.remove(frame.getComponent(0));
        frame.add(startScreen);
        frame.revalidate();
    }
    public GameLevel getWorld(){
        return world;
    }


    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
