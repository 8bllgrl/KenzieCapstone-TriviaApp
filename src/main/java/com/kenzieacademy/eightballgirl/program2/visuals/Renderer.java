package com.kenzieacademy.eightballgirl.program2.visuals;

import com.kenzieacademy.eightballgirl.program2.logic.constants.AppConstants;
import com.kenzieacademy.eightballgirl.program2.main.Main;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    public static Main main;

//    public Renderer(Main main){
//        this.main = main;
//        addKeyListener(main.keyboardHandler);
//        addMouseListener(main.mouseHandler);
//        addMouseMotionListener(main.mouseHandler);
//
//        //keyboard
//        setPanelSize();
//    }
    public Renderer(){
        addKeyListener(Main.keyboardHandler);
        addMouseListener(Main.mouseHandler);
        addMouseMotionListener(Main.mouseHandler);

        //keyboard
        setPanelSize();
    }

    private void setPanelSize() {
        Dimension panelsize = new Dimension(AppConstants.APPLICATION_WIDTH, AppConstants.APPLICATION_HEIGHT);
        setPreferredSize(panelsize);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        ///////////////////////////////////////////////////////
        //Set  anti-alias!
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Set anti-alias for text
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        ///////////////////////////////////////////////////////
        Main.render(g2);
        //stays at bottom
        g2.dispose();
    }
}
