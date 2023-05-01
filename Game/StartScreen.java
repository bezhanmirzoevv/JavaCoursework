package Game;

import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends UserView {
    private Graphics2D g = new draw();
    private final Image clouds = new ImageIcon("data/background/clouds.png").getImage();
    private final Image background = new ImageIcon("data/sky.png").getImage();
    private double cloudx = 0;
    private double cloud2x = 1000;
    public StartScreen(World w, Game g) {
        super(w, 1000, 500);
        JButton startButton = new JButton("Start");
        JButton controlButton = new JButton("Controls");
        JButton exitButton = new JButton("Exit");
        this.setLayout(null);


        startButton.setBounds(400, 250, 200, 50);
        controlButton.setBounds(400, 300, 200, 50);
        exitButton.setBounds(400, 350, 200, 50);

        this.add(startButton);
        this.add(controlButton);
        this.add(exitButton);

        //draw
        g.getWorld().getGround().addImage(new BodyImage("data/grassfloor.png", 2));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.nextScreen("Start", "Level");
            }
        });
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.nextScreen("Start", "Control");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    @Override
    protected void paintBackground(Graphics2D g){

        g.drawImage(background, -10, -5, 1020, 500, this);
        g.drawImage(clouds, (int) cloudx, 0, 1000, 500, this);
        g.drawImage(clouds, (int) cloud2x, 0, 1000, 500, this);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.setFont(new Font("Ninjastrike", Font.PLAIN, 120));
        g.drawString("Ninja Runner", 150, 170);
    }
}
