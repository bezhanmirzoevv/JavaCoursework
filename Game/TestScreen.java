package Game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestScreen extends UserView {
    public TestScreen(World w, Game g) {
        super(w, 1000, 500);
        JButton startButton = new JButton("Start");
        this.setLayout(null);
        startButton.setBounds(500, 100, 100, 100);
        this.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button working");
                g.startGame();
            }
        });

    }
    @Override
    protected void paintBackground(Graphics2D g){

    }
}
