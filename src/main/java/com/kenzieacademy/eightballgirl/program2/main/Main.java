package com.kenzieacademy.eightballgirl.program2.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzieacademy.eightballgirl.program1.CustomHttpClient;
import com.kenzieacademy.eightballgirl.program2.logic.HttpConnectionHelpers;
import com.kenzieacademy.eightballgirl.program2.logic.constants.AppConstants;
import com.kenzieacademy.eightballgirl.program2.logic.restapi.ClueDTO;
import com.kenzieacademy.eightballgirl.program2.visuals.Renderer;
import com.kenzieacademy.eightballgirl.program2.visuals.Window;
import com.kenzieacademy.eightballgirl.program2.visuals.inputs.KeyboardHandler;
import com.kenzieacademy.eightballgirl.program2.visuals.inputs.MouseHandler;
import com.kenzieacademy.eightballgirl.program2.visuals.screens.BasicScreen;
import com.kenzieacademy.eightballgirl.program2.visuals.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    private static int FPS = 60;
    private static int TICKSPEED = 200;

    //Clue info//
    private static final int MAXIMUM_CLUE_VALUE = 355237;
    private static ClueDTO clueDataTransferObject;
    private static int currentClueNumber;
    public static String currentClueCategory;
    public static String currentClueAnswer;
    public static String currentClueQuestion;

    //arraylists:

    private static ArrayList<Integer> usedCluesByID =
            new ArrayList<>(); //Stores clues so duplicate question/answer pairs aren't called.
    private static ArrayList<String> userAnswersList =
            new ArrayList<>(); //Stores the user's answers in an array so that they can be displayed after the loop.
    private static ArrayList<String> correctAnswersList =
            new ArrayList<>(); //Stores the correct answers in an array so that they can be displayed after the loop.

    //For user score-keeping:
    private static String userAnswer = " ";
    private static String correctAnswer = " ";
    private static int userScore = 0;

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
                double timePerFrame = 1000000000.0 / FPS;
                double timePerUpdate = 1000000000.0 / TICKSPEED;

                long previousTime = System.nanoTime();

                int frames = 0;
                int updates = 0;
                long lastCheck = System.currentTimeMillis();

                double deltaUpdate = 0;
                double deltaFrame = 0;

                while (appThread != null) {
                    long currentTime = System.nanoTime();

                    deltaUpdate += (currentTime - previousTime) / timePerUpdate;
                    deltaFrame += (currentTime - previousTime) / timePerFrame;
                    previousTime = currentTime;

                    if (deltaUpdate >= 1) {
                        screen.update();
                        updates++;
                        deltaUpdate--;
                    }

                    if (deltaFrame >= 1) {
                        renderer.repaint();
                        frames++;
                        deltaFrame--;

                    }

                    if (System.currentTimeMillis() - lastCheck >= 1000) {
                        lastCheck = System.currentTimeMillis();
//                System.out.println("FPS: " + frames + " |  UPS:" + updates);
                        setFPS(frames);

                        updates = 0;
                        frames = 0;
                    }
                }
            }
        };
        Thread thread = new Thread(appThread);
        thread.start();
    }

    public static void render(Graphics2D g2) {
        screen.render(g2);
    }

    private static void initializeClueInfo() {
        //this sets the information of the current clue stuff.
        currentClueNumber = new Random().nextInt(MAXIMUM_CLUE_VALUE);
        initializeHTTPConnect();

        currentClueCategory = clueDataTransferObject.getCategory().getTitle();
        currentClueQuestion = clueDataTransferObject.getQuestion();
        currentClueAnswer = clueDataTransferObject.getAnswer();
        System.out.println(currentClueCategory);
        System.out.println(currentClueQuestion);
        System.out.println(currentClueNumber);
        System.out.println(currentClueAnswer);
    }

    private static void initializeHTTPConnect() {
        try {
            HttpConnectionHelpers.sendGET(HttpConnectionHelpers.formatQueryOnBaseUrl(currentClueNumber));
            String response = CustomHttpClient.sendGET(HttpConnectionHelpers.formatQueryOnBaseUrl(currentClueNumber));
            ObjectMapper objectMapper = new ObjectMapper();
            clueDataTransferObject = objectMapper.readValue(response, ClueDTO.class);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("This error occurred because something went wrong with" +
                    " the URL Passed, the clue api, or because something went wrong with reading user input.");
        }


    }

    //getters & setters


    public static void setFPS(int FPS) {
        Main.FPS = FPS;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {


        //start the loop.
        initializeClueInfo();
        System.out.println(currentClueCategory);
        visualize();


    }


    //this stays at the very bottom.
}
