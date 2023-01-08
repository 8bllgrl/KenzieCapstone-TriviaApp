package com.kenzieacademy.eightballgirl.program2.visuals.screens;

import com.kenzieacademy.eightballgirl.program2.visuals.gui.SubPane;

import java.awt.*;

public class BasicScreen extends Screen {



    //Description:

    //variables
    private SubPane whiteSubpane;
    private static Color purple;

    //constructors

    public BasicScreen() {
        super();
        this.purple = new Color(148, 118, 185);
        this.whiteSubpane = new SubPane(400,500);
    }


    //methods


    //RENDER:
    public void render(Graphics2D g2){
        renderBg(g2);
        whiteSubpane.render(g2);
    }

    public void renderBg(Graphics2D g2){
        Color highlight = new Color(158, 131, 193);
        Color shade = new Color(103, 68, 146);
        GradientPaint gradientPaint2 = new GradientPaint(0, height + 50, shade, 0, 50, highlight);
        g2.setPaint(gradientPaint2);
        g2.fillRect(0,0,width,height);
    }
    //--render bg
    //--render white subpane


    //getters & setters

    //mouse moved
    //mouse clicked
    //keyboard events

}
