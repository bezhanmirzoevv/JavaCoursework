package Game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelScreen extends UserView {

    public LevelScreen(World w, Game g){
        super(w,1000, 500);
        this.setLayout(null);

        JButton level1 = new JButton("Level1");
        JButton level2 = new JButton("Level2");
        JButton level3 = new JButton("Level3");
        JButton backButton = new JButton("Back");

        level1.setBounds(50, 300, 230, 50);
        level2.setBounds(385, 300, 230, 50);
        level3.setBounds(720, 300, 230, 50);
        backButton.setBounds(10, 10, 100, 25);

        this.add(level1);
        this.add(level2);
        this.add(level3);
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
                g.nextScreen("Level", "Level1");
            }
        });
    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.setFont(new Font("Ninjastrike", Font.PLAIN, 70));
        g.drawString("Ninja Runner", 300, 70);
    }
}
