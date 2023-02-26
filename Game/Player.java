package Game;

import city.cs.engine.*;

public class Player extends Walker {
    private int score;
    private static final Shape player = new BoxShape(1,2);

    //private static final BodyImage playerImage = new BodyImage("data/student.png", 4);

    public Player(World world) {
        super(world, player);
        //addImage(playerImage);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }
}
