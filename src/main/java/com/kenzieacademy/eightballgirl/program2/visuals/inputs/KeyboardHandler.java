package com.kenzieacademy.eightballgirl.program2.visuals.inputs;

import com.kenzieacademy.eightballgirl.program2.main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {


    //Description:

    //variables

    //constructors

    //methods
    @Override
    public void keyTyped(KeyEvent e) {

        Main.screen.keyTyped(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //getters & setters
}
