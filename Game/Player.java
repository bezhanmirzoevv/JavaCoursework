package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Walker implements ActionListener {
    private GameLevel world;
    public boolean reset = false;
    private int score;
    private int credits=0;
    public int scoremultiplier = 1;
    public float gameSpeed = -0.25f;
    public boolean gameover = false;
    private boolean roundComplete = false;
    public boolean doublejump = true;
    public int doubleJumpCoolDown = 0;
    public int speed = 150;
    public boolean sliding = false;
    private float slidingtimer = 0;
    public boolean jump = false;
    public Timer timer = new Timer(speed, this);
    public boolean gamerunning = false;
    public static Shape shape = new BoxShape(1,2);
    private static int deadImagePointer = 0;
    private static String[] deadImages = {"data/playerImages/Dead__000.png", "data/playerImages/Dead__001.png",
            "data/playerImages/Dead__002.png", "data/playerImages/Dead__003.png", "data/playerImages/Dead__004.png",
            "data/playerImages/Dead__005.png", "data/playerImages/Dead__006.png", "data/playerImages/Dead__007.png",
            "data/playerImages/Dead__008.png", "data/playerImages/Dead__009.png"};
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

    public Player(GameLevel world) {
        super(world, shape);
        this.setAlwaysOutline(true);
        addImage(IdleImage);
        this.score = 0;
        this.setGravityScale(3);
        timer.start();
        this.world = world;
    }

    public void playerReset(){
        this.gamerunning = false;
        this.gameover = false;
        this.credits = world.getsavedStats()[1];
        this.score = world.getsavedStats()[0];
        jumpImagePointer = 0;
        runImagePointer = 0;
        deadImagePointer = 0;
        slideImagePointer = 0;
    };
    public int getScore() {
        return score;
    }
    public void setScore(int score){this.score = score;}
    public void increaseScore() {
        this.score+=1*scoremultiplier;
    }
    public void increaseCredits() {
        this.credits++;
    }
    public int getCredits(){return this.credits;}
    public void setCredits(int a){this.credits-=a;}

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
            } else {
                jumpImagePointer += 1;
            }

        }else if (pointer == "slideImagePointer"){
            if (slideImagePointer == slideImages.length - 1) {
                if (slidingtimer==4) {
                    slideImagePointer = 0;
                    world.playerRotate();
                    this.slidingtimer = 0;
                }
            } else {
                slideImagePointer += 1;
            }
        } else if (pointer == "deadImagePointer") {
            if (deadImagePointer < deadImages.length-1) {
                deadImagePointer += 1;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(doublejump) ) {
            doubleJumpCoolDown += 1;
            if (doubleJumpCoolDown == 15) {
                System.out.println("doublejump replenished");
                this.doublejump = true;
                doubleJumpCoolDown = 0;
            }
        }
        removeAllImages();
        System.out.println(sliding);
        if (sliding){
            //slide function
            setImagePointer("slideImagePointer");
            playerImage = new BodyImage(slideImages[slideImagePointer], 4);
            slidingtimer+=1;

        } else if(jump){
            //jump images
            setImagePointer("jumpImagePointer");
            playerImage = new BodyImage(jumpImages[jumpImagePointer], 4);
            //addImage(playerImage);
        } else if (this.getLinearVelocity().x > 0 || this.gamerunning){
            setImagePointer("runImagePointer");
            playerImage = new BodyImage(runImages[runImagePointer], 4);
            //addImage(playerImage);
        } else if (this.gameover){
            setImagePointer("deadImagePointer");
            playerImage = new BodyImage(deadImages[deadImagePointer], 4);
        }else{
            playerImage = IdleImage;
        }
        addImage(playerImage);
    }
}
