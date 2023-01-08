package com.kenzieacademy.eightballgirl.program1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzieacademy.eightballgirl.program1.clueapi.ClueDTO;

import java.util.*;

/*:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:*/
//Sources cited:

//https://my.kenzie.academy/courses/258/pages/video-using-stringbuilder?module_item_id=53723
//https://www.baeldung.com/java-generating-random-numbers-in-range
//Minecraft code to inspire commenting style.

//private String userAnswer = " ";//TODO: Could become a string builder to reduce memory used.

//TODO: Turn certain parts into methods.

public class Quiz {

    //declaring variables
    /**
     * ~ Arrays to keep track of previous clues and user responses. ~
     */

    private ArrayList<Integer> usedCluesByID =
            new ArrayList<>(); //Stores clues so duplicate question/answer pairs aren't called.
    private ArrayList<String> userAnswersList =
            new ArrayList<>(); //Stores the user's answers in an array so that they can be displayed after the loop.
    private ArrayList<String> correctAnswersList =
            new ArrayList<>(); //Stores the correct answers in an array so that they can be displayed after the loop.

    /**
     * ~ For storing clue and user answers, ready for comparison. ~
     */
    private String userAnswer = " ";
    private String correctAnswer = " ";
    private int userScore = 0;

    //private String userAnswer = " ";
    //    private String correctAnswer = " ";
    //    private int userScore = 0;

    /**
     * ~ Quiz Utilities; this supplies the quiz with resources to make an engaging loop. ~
     */
    private ClueDTO cluesDTO;                     //Used to call specified json properties from the API call.
    private Random randomness = new Random();
    private Scanner scanner;                     //for user input to be read and stored in Strings.
    private final String URL_STRING = "https://jservice.kenzie.academy/api/clues";

    private int MAX_QUESTION_AMOUNT; //Modify this variable if you want the loop to change in length.
    private int questionsAsked = 0; //NOTE: DO NOT CHANGE, or the loop will not work properly.

    /*~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~ ~-~*/

    /**
     * This starts the quiz: initializes the ClueDTO, the Scanner, and connecting the ClueDTO to CustomHttpClient. ~
     */

    public Quiz() {
        try {
            CustomHttpClient.sendGET(URL_STRING);
            String response = CustomHttpClient.sendGET(URL_STRING);
            ObjectMapper objectMapper = new ObjectMapper();
            cluesDTO = objectMapper.readValue(response, ClueDTO.class);
            scanner = new Scanner(System.in);

            MAX_QUESTION_AMOUNT = 10;

            /*~ This is the start of the Quiz loop: this is a majority of where the quiz happens.
                    After the amount of questions asked reaches maximum amount of questions desired, the loop ends. ~*/
            while (questionsAsked < MAX_QUESTION_AMOUNT) {

                /* Turned into a method */
                int randomNumber_to100 = randomClueNumber_to100();

//prettiness
                System.out.println(":~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:");
                /* Turned into a method */
                printToConsole(randomNumber_to100);
//                TEST_printToConsole(randomNumber_to100);


                /* step two: dealing with user input. */

                /*~ Collects the answer to the current Question & Uses the scanner to store the user response */
                correctAnswer = cluesDTO.getClues().get(randomNumber_to100).getAnswer();
                userAnswer = scanner.nextLine();

                /* Loop checks if the answer String is empty :: Don't want an easy mistake to cause the user to fail! */
                while (Objects.equals(userAnswer, "")) {
                    System.out.println("Give it your best shot. Please enter your own answer to the question.");
                    //REPEAT THE QUESTION AND CATEGORY
                    printToConsole(randomNumber_to100);
                    userAnswer = scanner.nextLine();
                }

                //after the user has entered a String. . .
                addAnswersToArraylists();

                /*~~ This sees if the answer the user provided in is correct. User gains a point if it is correct. ~~*/
                if (!Objects.equals(userAnswer, "")) {
                    if (answerComparison(correctAnswer, userAnswer)) {
                        /* if the user answer is equal to the correct answer */
                        System.out.println();
                        System.out.println("CORRECT. the answer was: " + "\"" + correctAnswer + "\"");
                        userScore++;
                    } else {
                        /* if the user answer is incorrect in any way */
                        System.out.println();
                        System.out.println("INCORRECT. the answer was: " + "\"" + correctAnswer + "\"");
                    }
                }
                System.out.println("TOTAL SCORE: " + userScore);


                questionsAsked++;
                /* this is because this is a while loop. this will increment the quiz,
                causing it to loop around until this number reaches the maximum. a simplified
                for loop could have worked just as well, but i haven't used while loops in a.. *while* :) */
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("This error occurred because something went wrong with" +
                    " the URL Passed, the clue api, or because something went wrong with reading user input.");
        }


        /* ~~~ AFTER THE LOOP IS OVER... ~~~ */
        //for visibility and distinguish between the quiz and quiz results.
        System.out.println();System.out.println();System.out.println();
        System.out.println("Quiz end.");
        System.out.println("Let's see how you did.");System.out.println();System.out.println();System.out.println();
        System.out.println("/////////////////////////////////////////////////////////////////");
        for (int i = 0; i < usedCluesByID.size(); i++) {
            printQuizDetails(usedCluesByID.get(i), i, true);
        }

        /* Printing the final score. */
        System.out.println("Your total score: " + userScore);
        System.out.println(questionsAsked);
    }

    public Quiz(int questionAmount) {
        MAX_QUESTION_AMOUNT = questionAmount;

        try {
            CustomHttpClient.sendGET(URL_STRING);
            String response = CustomHttpClient.sendGET(URL_STRING);
            ObjectMapper objectMapper = new ObjectMapper();
            cluesDTO = objectMapper.readValue(response, ClueDTO.class);
            scanner = new Scanner(System.in);

            /*~ This is the start of the Quiz loop: this is a majority of where the quiz happens.
                    After the amount of questions asked reaches maximum amount of questions desired, the loop ends. ~*/
            while (questionsAsked < MAX_QUESTION_AMOUNT) {

                /* Turned into a method */
                int randomNumber_to100 = randomClueNumber_to100();

//prettiness
                System.out.println(":~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:");
                /* Turned into a method */
                printToConsole(randomNumber_to100);
//                TEST_printToConsole(randomNumber_to100);


                /* step two: dealing with user input. */

                /*~ Collects the answer to the current Question & Uses the scanner to store the user response */
                correctAnswer = cluesDTO.getClues().get(randomNumber_to100).getAnswer();
                userAnswer = scanner.nextLine();

                /* Loop checks if the answer String is empty :: Don't want an easy mistake to cause the user to fail! */
                while (Objects.equals(userAnswer, "")) {
                    System.out.println("Give it your best shot. Please enter your own answer to the question.");
                    //REPEAT THE QUESTION AND CATEGORY
                    printToConsole(randomNumber_to100);
                    userAnswer = scanner.nextLine();
                }

                //after the user has entered a String. . .
                addAnswersToArraylists();

                /*~~ This sees if the answer the user provided in is correct. User gains a point if it is correct. ~~*/
                if (!Objects.equals(userAnswer, "")) {
                    if (answerComparison(correctAnswer, userAnswer)) {
                        /* if the user answer is equal to the correct answer */
                        System.out.println();
                        System.out.println("CORRECT. the answer was: " + "\"" + correctAnswer + "\"");
                        userScore++;
                    } else {
                        /* if the user answer is incorrect in any way */
                        System.out.println();
                        System.out.println("INCORRECT. the answer was: " + "\"" + correctAnswer + "\"");
                    }
                }
                System.out.println("TOTAL SCORE: " + userScore);


                questionsAsked++;
                /* this is because this is a while loop. this will increment the quiz,
                causing it to loop around until this number reaches the maximum. a simplified
                for loop could have worked just as well, but i haven't used while loops in a.. *while* :) */
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("This error occurred because something went wrong with" +
                    " the URL Passed, the clue api, or because something went wrong with reading user input.");
        }


        /* ~~~ AFTER THE LOOP IS OVER... ~~~ */
        //for visibility and distinguish between the quiz and quiz results.
        System.out.println();System.out.println();System.out.println();
        System.out.println("Quiz end.");
        System.out.println("Let's see how you did.");System.out.println();System.out.println();System.out.println();
        System.out.println("/////////////////////////////////////////////////////////////////");
        for (int i = 0; i < usedCluesByID.size(); i++) {
            printQuizDetails(usedCluesByID.get(i), i, true);
        }

        /* Printing the final score. */
        System.out.println("Your total score: " + userScore);
        System.out.println(questionsAsked);
    }


    /**
     * The variables a clue answer and a user answer get passed through this method.
     */
    private boolean answerComparison(String correctAnswer, String userAnswer) {

        String trueAnswerSimplified;
        String userAnswerSimplified;
        boolean returnVariable = false;

        //step one: simplify 1st string and remove spaces
        trueAnswerSimplified = correctAnswer.toUpperCase();
        trueAnswerSimplified = trueAnswerSimplified.replaceAll("[-+.^:,!?;`'=]", "");
        trueAnswerSimplified = trueAnswerSimplified.replaceAll("[\\[\\](){}]", "");
        trueAnswerSimplified = trueAnswerSimplified.replaceAll("[@#$%^&*]", "");
        trueAnswerSimplified = trueAnswerSimplified.replaceAll("\"", "");
        trueAnswerSimplified = trueAnswerSimplified.replaceAll("\\s+", "");

        //step two: simplify 2nd string BUT DON'T REMOVE SPACES.
        userAnswerSimplified = userAnswer.toUpperCase();
        userAnswerSimplified = userAnswerSimplified.replaceAll("[-+.^:,!?;`'=]", "");
        userAnswerSimplified = userAnswerSimplified.replaceAll("[\\[\\](){}]", "");
        userAnswerSimplified = userAnswerSimplified.replaceAll("[@#$%^&*]", "");
        userAnswerSimplified = userAnswerSimplified.replaceAll("\"", "");

        //step three: check if the answer is already true
        if (trueAnswerSimplified.equals(userAnswerSimplified)) {
            return true;
        }
        //step four : split myAnswer into an arraylist.
        ArrayList<String> myNewList = new ArrayList<>(Arrays.asList(userAnswerSimplified.split(" ")));

        //step five: remove 1-2 letter words
        for (int i = 0; i < myNewList.size(); i++) {
            if (myNewList.get(i).length() < 2) {
                myNewList.remove(i);
            }
        }

        //step six: check if the true answer contains a substring of any arraylist contents. if so, return true.
        for (String s : myNewList) {
            //if true answer contains a substring of the index, return true.
            returnVariable = trueAnswerSimplified.contains(s);
        }
        return returnVariable;
    }

    private void printQuizDetails(int loopNumber, int index, boolean printAnswer) {

        System.out.print("Category: ");
        System.out.println(cluesDTO.getClues().get(loopNumber).getCategory().getTitle());
        System.out.print("Question: ");
        System.out.println(cluesDTO.getClues().get(loopNumber).getQuestion());

        if (printAnswer) {
            System.out.println(":~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:");
            //print answer
            //print user answer, even if it is "".
            System.out.println("Your answer: " + userAnswersList.get(index));
            System.out.println("Correct answer: " + correctAnswersList.get(index));
            System.out.print("Result: ");


            if (answerComparison(correctAnswersList.get(index), userAnswersList.get(index))) {
                System.out.println("CORRECT");
            } else if (!answerComparison(correctAnswersList.get(index), userAnswersList.get(index))) {
                System.out.println("INCORRECT");
            }
            System.out.println();
        }
    }

    private int randomClueNumber_to100() {
        /*~ A new ID number is called only at the beginning of the loop. ~*/
        int randomNumber_to100 = randomness.nextInt((100));
        //Check if the ID number has already been used.
        while (usedCluesByID.contains(randomNumber_to100)) {
            /*~ This is only used if a duplicate ID number appears. */
            randomNumber_to100 = randomness.nextInt((100));
        }
        /*~ if the ID number is new, store it to an Array of expended clues. */
        if (!usedCluesByID.contains(randomNumber_to100)) {
            usedCluesByID.add(randomNumber_to100);
        }

        return randomNumber_to100;

    }

    private void printToConsole(int randomNumber) {

        /* ~~~ Printing out to the console ~~~ */
        /* step one: reveal the category and ask the question. */
        System.out.print("Category: ");
        System.out.println(cluesDTO.getClues().get((randomNumber)).getCategory().getTitle());
        System.out.print("Question: ");
        System.out.println(cluesDTO.getClues().get((randomNumber)).getQuestion());
        System.out.println();


    }

    private void TEST_printToConsole(int randomNumber_to100) {
        //  //  // // // // // // // // // // Testing //  //  // // // // // // // // // // // // //
        //////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Clue: " + cluesDTO.getClues().get(randomNumber_to100).getId());
        System.out.println("Answer: " + cluesDTO.getClues().get(randomNumber_to100).getAnswer());


        /////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void addAnswersToArraylists(){
        /* this adds the user's answer and the correct answer to their respective lists. */
        userAnswersList.add(userAnswer);
        correctAnswersList.add(correctAnswer);

    }
}
/*:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:~~:*/


