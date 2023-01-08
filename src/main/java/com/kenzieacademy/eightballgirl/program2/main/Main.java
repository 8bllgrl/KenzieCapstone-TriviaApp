package com.kenzieacademy.eightballgirl.program2.main;

import com.kenzieacademy.eightballgirl.program2.logic.constants.AppConstants;
import com.kenzieacademy.eightballgirl.program2.visuals.Renderer;
import com.kenzieacademy.eightballgirl.program2.visuals.Window;
import com.kenzieacademy.eightballgirl.program2.visuals.inputs.KeyboardHandler;
import com.kenzieacademy.eightballgirl.program2.visuals.inputs.MouseHandler;
import com.kenzieacademy.eightballgirl.program2.visuals.screens.BasicScreen;
import com.kenzieacademy.eightballgirl.program2.visuals.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Main {

    //Description:

    //variables

    public static Renderer renderer;
    public static Window window;
    @Nullable
    public static Screen screen;
    public static KeyboardHandler keyboardHandler;
    public static MouseHandler mouseHandler;
    public static AppThread appThread;
    //constructors

    //methods

    public static void visualize() {
        mouseHandler = new MouseHandler();
        keyboardHandler = new KeyboardHandler();

        setScreen(new BasicScreen());

        renderer = new Renderer();
        window = new Window(renderer);
        renderer.setFocusable(true);
        renderer.requestFocus();

        startThread();
    }

    private static void setScreen(@Nullable Screen screenpassedin) {
        if (screenpassedin == null) {
            screenpassedin = new BasicScreen();
        }
        screen = screenpassedin;
        screenpassedin.init(AppConstants.APPLICATION_WIDTH, AppConstants.APPLICATION_HEIGHT);
    }

    public static void startThread() {
        appThread = new AppThread(){
            @Override
            public void run() {
                renderer.repaint();
            }
        };
        Thread thread = new Thread(appThread);
        thread.start();
    }

    public static void render(Graphics2D g2) {
        screen.render(g2);
    }


    //getters & setters


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {

        visualize();

    }
    //this stays at the very bottom.
}
