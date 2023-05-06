package Game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelScreen extends UserView {
    private Image background = new ImageIcon("data/Screens/Background.png").getImage();
    private JButton level2button;
    private JButton level3button;
    private boolean level1complete = false;
    private boolean level2complete = true;
    private GameLevel w;

    public LevelScreen(GameLevel world, Game g){
        super(world,1000, 500);
        this.setLayout(null);
        w = world;

        JButton level1 = new JButton("Play");
        level2button = new JButton("Play");
        level3button = new JButton("Play");
        JButton backButton = new JButton("Back");

        level1.setBounds(50, 300, 230, 50);
        level2button.setBounds(385, 300, 230, 50);
        level3button.setBounds(720, 300, 230, 50);
        backButton.setBounds(10, 10, 100, 25);

        this.add(level1);
        this.add(level2button);
        this.add(level3button);
        this.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.nextScreen("Level", "Start");
            }
        });
        level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.nextLevel("Level1");
                g.nextScreen("Level", "Game");
            }
        });
        level2button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level1complete) {
                    g.nextLevel("Level2");
                    g.nextScreen("Level", "Game");
                }
            }
        });

        level3button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level2complete) {
                    g.nextLevel("Level3");
                    g.nextScreen("Level", "Game");
                }
            }
        });
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, 0, 0,1000,500, this);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.setFont(new Font("Ninjastrike", Font.PLAIN, 70));
        //g.drawString("Ninja Runner", 300, 70);
    }
    public void setLevel1complete(){this.level1complete=true;}

    public void setLevel2complete() {this.level2complete=true;}
}
