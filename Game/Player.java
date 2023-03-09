package Game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Walker implements ActionListener {
    private int score;
    public boolean gameover = false;
    public boolean doublejump = true;
    private int doubleJumpCoolDown = 0;
    public int speed = 150;
    public boolean sliding = false;
    public boolean jump = false;
    public Timer timer = new Timer(speed, this);
    public boolean gamerunning = false;
    private static final Shape player = new BoxShape(1,2);
    private static int runImagePointer = 0;
    private static String[] runImages = {"data/playerImages/Run0.png", "data/playerImages/Run1.png",
            "data/playerImages/Run2.png", "data/playerImages/Run3.png", "data/playerImages/Run4.png",
            "data/playerImages/Run5.png", "data/playerImages/Run6.png", "data/playerImages/Run7.png",
            "data/playerImages/Run8.png", "data/playerImages/Run9.png"};
    private static int jumpImagePointer = 0;
    private static String[] jumpImages = {"data/playerImages/Jump0.png", "data/playerImages/Jump1.png",
            "data/playerImages/Jump2.png", "data/playerImages/Jump3.png", "data/playerImages/Jump4.png",
            "data/playerImages/Jump5.png", "data/playerImages/Jump6.png", "data/playerImages/Jump7.png",
            "data/playerImages/Jump8.png", "data/playerImages/Jump9.png"};

    private static int slideImagePointer = 0;
    private static String[] slideImages = {"data/playerImages/Slide0.png", "data/playerImages/Slide1.png",
            "data/playerImages/Slide2.png", "data/playerImages/Slide3.png", "data/playerImages/Slide4.png",
            "data/playerImages/Slide5.png", "data/playerImages/Slide6.png", "data/playerImages/Slide7.png",
            "data/playerImages/Slide8.png", "data/playerImages/Slide9.png"};

    private static BodyImage playerImage = new BodyImage(runImages[runImagePointer], 4);
    private static BodyImage IdleImage = new BodyImage("data/playerImages/Idle0.png", 4);

    public Player(World world) {
        super(world, player);
        addImage(IdleImage);
        this.score = 0;
        this.setGravityScale(3);
        timer.start();
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public void setImagePointer(String pointer) {
        if (pointer == "runImagePointer") {
            jumpImagePointer = 0;
            slideImagePointer = 0;
            if (runImagePointer == runImages.length - 1) {
                runImagePointer = 0;
            } else {
                runImagePointer += 1;
            }
        }else if (pointer == "jumpImagePointer"){
            if (jumpImagePointer == jumpImages.length - 1) {
                jumpImagePointer = 0;
                this.jump = false;
            } else {
                jumpImagePointer += 1;
            }

        }else if (pointer == "slideImagePointer"){

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(doublejump) ) {
            doubleJumpCoolDown += 1;
            if (doubleJumpCoolDown == 10) {
                System.out.println("doublejump replenished");
                this.doublejump = true;
                doubleJumpCoolDown = 0;
            }
        }
        removeAllImages();
        if (sliding){
            //implement slide function
        } else if(jump){
            //jump images
            setImagePointer("jumpImagePointer");
            playerImage = new BodyImage(jumpImages[jumpImagePointer], 4);
            //addImage(playerImage);
        } else if (this.getLinearVelocity().x > 0 || this.gamerunning){
            setImagePointer("runImagePointer");
            playerImage = new BodyImage(runImages[runImagePointer], 4);
            //addImage(playerImage);
        } else{
            playerImage = IdleImage;
        }
        addImage(playerImage);
    }
}
