package Game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends UserView {
    private Image background = new ImageIcon("data/Screens/EndScreen.png").getImage();
    public EndScreen(World g){
        super(g, 1000, 500);
        this.setLayout(null);
        JButton exit = new JButton("Exit");
        exit.setBounds(385, 400, 230, 50);
        this.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, 0, 0,1000,500, this);
    }
}
