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
        SimulationSettings settings = new SimulationSettings(120);

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
                frame.add(levelScreen);
                break;
            case "Game":
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

    }
    public void nextLevel(String level){
        int credits = world.getPlayer().getCredits();
        int score = world.getPlayer().getScore();
        world.stop();
        switch(level){
            case "Level1":
                world = new Level1(this, credits, score, world.islevel1Complete(), world.islevel2Complete());
                break;
            case "Level2":
                world = new Level2(this, credits, score, world.islevel1Complete(), world.islevel2Complete());
                break;
            case "Level3":
                world = new Level3(this, credits, score, world.islevel1Complete(), world.islevel2Complete());
                break;
        }
        view = new GameView(world);
        view.addKeyListener(controller);
        controller.Update(world.getPlayer(), world);
        world.start();
    }

    public GameLevel getWorld(){
        return world;
    }
    public GameView getView(){return this.view;}


    /** Run the game. */
    public static void main(String[] args) {
        System.out.println("****FOR THE MARKER TO READ***");
        System.out.println("The levels are long so if you dont have time, " +
                "you can press 7 on the keyboard to increase the players score and pass the level");
        new Game();
    }
}
