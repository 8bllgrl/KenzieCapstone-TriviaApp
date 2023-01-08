package com.kenzieacademy.eightballgirl.program2.main;

public class AppThread implements Runnable {
    @Override
    public void run() {

        //TODO : REMOVE. this is for testing purposes.
        while (this != null){
            System.out.println("An AppThread is alive.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
