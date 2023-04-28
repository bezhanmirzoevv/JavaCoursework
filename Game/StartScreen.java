package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen{
    private JButton button1;
    private JPanel Startscreen;

    public StartScreen(){

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getStartscreen() {
        return Startscreen;
    }
}
