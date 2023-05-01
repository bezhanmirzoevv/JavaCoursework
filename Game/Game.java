package Game;

import city.cs.engine.SimulationSettings;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class Game {

    private GameView view;
    private Controller controller;
    private GameLevel world;
    private World plainworld;
    private JFrame frame = new JFrame("Ninja Runner Game");
    private StartScreen startScreen;
    private ControlScreen controlScreen;
    private LevelScreen levelScreen;
    public Game() {
        plainworld = new World();
        world = new Level1();
        view = new GameView(world);

        Obstacles movingObstacles = new Obstacles(world, world.getPlayer());

        //controlling player
        controller = new Controller(world.getPlayer(), world);
        view.addKeyListener(controller);




        //view.addMouseListener(new GameView(view));
        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);

        //Screens
        startScreen = new StartScreen(world, this);
        controlScreen = new ControlScreen(world, this);
        levelScreen = new LevelScreen(world, this);

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
        System.out.println(frame.getComponents());
    }

    public JFrame getFrame() {
        return frame;
    }

    public void nextScreen(String current, String next){
        switch (current){
            case "Start":
                frame.remove(startScreen);
                break;
            case "Level":
                frame.remove(levelScreen);
                break;
            case "Control":
                frame.remove(controlScreen);
                plainworld.stop();
                break;
        }
        switch(next){
            case "Start":
                frame.add(startScreen);
                break;
            case "Control":
                plainworld.start();
                frame.add(controlScreen);
                break;
            case "Level":
                frame.add(levelScreen);
                break;
            case "Level1":
                world.strt();
                frame.add(view);
                view.requestFocus();
                break;
        }frame.revalidate();
    }

    public GameLevel getWorld(){
        return world;
    }


    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
