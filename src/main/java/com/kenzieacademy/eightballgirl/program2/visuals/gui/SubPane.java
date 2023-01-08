package com.kenzieacademy.eightballgirl.program2.visuals.gui;

import com.kenzieacademy.eightballgirl.program2.logic.constants.AppConstants;

import java.awt.*;
import java.util.ArrayList;

public class SubPane implements GuiRenderable {

    //Description:
    /*
    This is used for the white text box in the basic screen.
    this will contain all of the graphics such as the edit box, header, question, etc.
    This could be turned into a more general class for more of that sweet, sweet inheritance, but I want to
    keep things simple for this application in particular. I just want to get this quiz app to have a visual
    to it before I start to concern myself with those things.
     */

    //variables

    int x = 50, y = 50, width = 50, height = 50;
    ArrayList<String> questionTxt;
    //editable text box
    //----where does the information get sent to? this subpanel, or to the application?
    //submit button extends guibutton
    //title text
    //question text

    //constructors
    //
    public SubPane(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = (AppConstants.APPLICATION_WIDTH / 2) - width / 2;
        this.y = (AppConstants.APPLICATION_HEIGHT / 2) - height / 2;
        questionTxt = new ArrayList<>();
        seperateStringToQuestionTxtList("A fast food chain: \"If it doesn't get all over the place, it doesn't belong in your face\"");
    }


    //methods


    public void renderV1backpane(Graphics2D g2) {
//        g2.setColor(new Color(187, 113, 205));
        Color highlight = new Color(218, 169, 238);
        Color shade = new Color(177, 97, 200);
        int spacing = 25;
        GradientPaint gradientPaint1 = new GradientPaint(150, height + y, shade, 0, 150, highlight);
        g2.setPaint(gradientPaint1);
        g2.fillRoundRect(x - (spacing / 2), y - (spacing / 2), width + spacing, height + spacing, 25, 25);

        //stroke in the middle and around the outside of the white.
        g2.setColor(new Color(120, 57, 161));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x - (spacing / 2), y - (spacing / 2), width + spacing, height + spacing, 25, 25);
        g2.setStroke(new BasicStroke(6));
        g2.drawRoundRect(x, y, width, height, 25, 25);

        //gradient white pane?
        highlight = new Color(248, 245, 253);
        shade = new Color(222, 218, 241);
        GradientPaint gradientPaint2 = new GradientPaint(0, height + y, shade, 0, y, highlight);
        g2.setPaint(gradientPaint2);
        g2.fillRoundRect(x, y, width, height, 25, 25);

    }

    @Override
    public void render(Graphics2D g2) {
        renderV1backpane(g2);
//        drawtemporarybuttons(g2);
        //replace this with a forloop:
        renderTitle(g2);
        renderQuestion(g2);
        renderSubmitBox(g2);
    }

    private void renderSubmitBox(Graphics2D g2) {
        g2.setColor(new Color(110, 62, 164));
        int submitWidth = 300;
        int submitHeight = 65;
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2-submitWidth/2,450,submitWidth,submitHeight,20,20);
        g2.setColor(Color.white);
        g2.setFont(new Font("Gill Sans MT", Font.BOLD,45));
        g2.drawString("SUBMIT",submitWidth/2+25 , 465+submitHeight/2);


    }

    private void renderQuestion(Graphics2D g2) {
        g2.setColor(new Color(110, 62, 164));
        g2.setFont(new Font("Gill Sans MT", Font.PLAIN,25));
        //separate the string into more than one string, put it in an array, and then loop through it. each time that a new 'i' is reached, add +15 to the y.
        //how to separate string ?

        //make a copy of the base string.
        //take the substring 0 - 32.
        //save this substring to a new temp string.
        //add this temp string to an arraylist of strings.
        //remove substring 0-32 from the string copy.
        //loop again.

        //after this, there is a loop that loops through the arraylist that was created. this is the question string arraylist.
        //temp Y int is made. 200 at first.
        //for each string in the arraylist, g2. draw string( questionTxtArraylist.get(i) , 65, y )
        // when the 'i' becomes 2, add +25 to the temp Y int.
        //when loop is done, make temp Y = 200 again.
        //there is a public method that allows you to clear the arraylist so that strings from previous questions do not appear in the next questions.

        int ySpacing = 150;
        for (String s : questionTxt){
            g2.drawString( s, 95,ySpacing);
            ySpacing = ySpacing+25;
        }
    }

    private void seperateStringToQuestionTxtList(String string){
        String copyOfString = string;
        System.out.println(copyOfString);
        int rowLength = 37;


        while (copyOfString!=null){
            String substring;
            substring = copyOfString.substring(0,rowLength);
            questionTxt.add(substring);
            copyOfString = copyOfString.substring(rowLength);

            if (copyOfString.length() <= rowLength){
                questionTxt.add(copyOfString);
                return;
            }
        }

        for (String value : questionTxt) {
            System.out.println(value);

        }
    }

    private void renderTitle(Graphics2D g2) {
        g2.setColor(new Color(37, 6, 83));
        g2.setFont(new Font("Haettenschweiler", Font.PLAIN,55));
        g2.drawString("Question Genre.", width/2 - 55,100);
    }

    private void drawtemporarybuttons(Graphics2D g2) {
        g2.setColor(new Color(110, 62, 164));
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2,AppConstants.APPLICATION_WIDTH/2,150,50,20,20);
        g2.setColor(new Color(71, 179, 179));
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2,AppConstants.APPLICATION_WIDTH/2-100,150,50,20,20);
        g2.setColor(new Color(209, 18, 65));
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2,AppConstants.APPLICATION_WIDTH/2+100,150,50,20,20);
        g2.setColor(new Color(29, 211, 29));
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2-160,AppConstants.APPLICATION_WIDTH/2+100,150,50,20,20);
        g2.setColor(new Color(253, 194, 83));
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2-160,AppConstants.APPLICATION_WIDTH/2-100,150,50,20,20);
        g2.setColor(new Color(37, 6, 83));
        g2.fillRoundRect(AppConstants.APPLICATION_WIDTH/2-160,AppConstants.APPLICATION_WIDTH/2,150,50,20,20);
    }

    //getters & setters

}
