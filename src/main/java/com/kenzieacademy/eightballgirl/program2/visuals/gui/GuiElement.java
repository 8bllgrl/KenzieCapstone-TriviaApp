package com.kenzieacademy.eightballgirl.program2.visuals.gui;

import java.awt.*;

public abstract class GuiElement implements GuiRenderable, GuiEventListener{

    public static void drawCenteredStringWithShadow(Graphics2D g2,String string, Font font,  int x, int y, int height, int offset, Color color){
        g2.setFont(font);
        //shadow
//        g2.setColor(Color.darkGray);
//        g2.drawString(string, x+9,y+(height/2)+(font.getSize()/2)+2);
        //actual text.
        g2.setColor(color);
        g2.drawString(string, x+5,y+(height/2)+(font.getSize()/2));
    }

    @Override
    public void render(Graphics2D g2){

    }
}
