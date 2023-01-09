package com.kenzieacademy.eightballgirl.program2.visuals;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;

    public Window(Renderer appRender){

        frame = new JFrame();
//        frame.setUndecorated(true);

        frame.getRootPane().setOpaque(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(appRender);

        //size
        frame.setResizable(false);
        frame.pack();

        frame.setTitle("Trivia Game!");
        frame.setBackground(Color.black);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
