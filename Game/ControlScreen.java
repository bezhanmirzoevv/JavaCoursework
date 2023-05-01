package Game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlScreen extends UserView {

    public ControlScreen(World w, Game g){
        super(w,1000, 500);
        JButton backButton = new JButton("Back");
        this.setLayout(null);

        backButton.setBounds(75, 50, 100, 25);

        this.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.setStartScreen();
            }
        });
    }
}
