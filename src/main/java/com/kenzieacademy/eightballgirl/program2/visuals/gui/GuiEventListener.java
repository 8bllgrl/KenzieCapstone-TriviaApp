package com.kenzieacademy.eightballgirl.program2.visuals.gui;

import java.awt.event.KeyEvent;

public interface GuiEventListener {
    boolean charTyped(KeyEvent e);

    boolean keyTyped(KeyEvent e);
}
