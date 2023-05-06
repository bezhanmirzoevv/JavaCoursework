package Game;

import city.cs.engine.BodyImage;
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
    private EndScreen endscreen;
    public Game() {
        plainworld = new World();
        world = new Level1(this, 0, 0, false, false);
        view = new GameView(world);

        //controlling player
        controller = new Controller(world.getPlayer(), world);
        view.addKeyListener(controller);




        //view.addMouseListener(new GameView(view));
        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);

        //Screens
        startScreen = new StartScreen(world, this);
        controlScreen = new ControlScreen(plainworld, this);
        levelScreen = new LevelScreen(world, this);
        endscreen = new EndScreen(plainworld);

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
            case "Game":
                frame.remove(view);
                //view.transferFocus();
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
                System.out.println(world.islevel1Complete());
                frame.add(levelScreen);
                break;
            case "Game":
                world.reinitialise();
                world.levelcomplete = false;
                frame.add(view);
                view.requestFocus();
                break;
            case "End":
                plainworld.start();
                frame.add(endscreen);
        }
        frame.repaint();
        frame.revalidate();
    }
    public void levelComplete(){
        if (world instanceof Level1){
            world.level1Complete();
            levelScreen.setLevel1complete();
            nextScreen("Game", "Level");
        }else if (world instanceof  Level2){
            world.level2Complete();
            levelScreen.setLevel2complete();
            nextScreen("Game", "Level");
        }else if (world instanceof Level3){
            nextScreen("Game", "End");
        }
        //levelScreen.requestFocus();
        //levelScreen.revalidate();

    }
    public void nextLevel(String level){
        world.stop();
        System.out.println(world.getPlayer().getCredits());
        switch(level){
            case "Level1":
                world = new Level1(this, world.getPlayer().getCredits(), world.getPlayer().getScore(), world.islevel1Complete(), world.islevel2Complete());
                break;
            case "Level2":
                world = new Level2(this, world.getPlayer().getCredits(), world.getPlayer().getScore(), world.islevel1Complete(), world.islevel2Complete());
                break;
            case "Level3":
                world = new Level3(this, world.getPlayer().getCredits(), world.getPlayer().getScore(), world.islevel1Complete(), world.islevel2Complete());
                break;
        }
        world.getPlayer().setCredits(world.getsavedStats()[1]);
        view = new GameView(world);
        view.addKeyListener(controller);
        controller.Update(world.getPlayer(), world);
        world.start();
    }

    public GameLevel getWorld(){
        return world;
    }


    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
