package com.steven;

//import java.util.Scanner;

public class PlayGame {



    public static void playGame(){
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a game to play, if single player practice press 1");
        String gameChoice = "1";
        System.out.println(gameChoice);
        practice();

    }

    public static void practice(){
        System.out.println("practice game began");
//        Scanner practiceScanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String userName = "Ste";
        System.out.println("Please enter your starting score");
        int startingScore = 490;
        System.out.println(userName);
        System.out.println(startingScore);

        Match practiceMatch = new Match(startingScore, userName);
        System.out.println(practiceMatch.getCurrentScore());
        System.out.println(practiceMatch.getPlayerName());
        while (practiceMatch.getCurrentScore() != 0){
            System.out.println("Please enter your score");
            int score = 70;
            practiceMatch.removeScore(score);
            if(practiceMatch.isOnFinish(practiceMatch.getCurrentScore())){
                System.out.println("You are on a finish and your remaining score is: " + practiceMatch.getCurrentScore());
            } else {
                System.out.println("Your remaining score is: " + practiceMatch.getCurrentScore());
            }
        }
        System.out.println("Please enter the number of darts to finish the leg");
        int dartsAtDouble = 2;
        practiceMatch.setDartsAtDouble(dartsAtDouble);
        System.out.println("You threw " + practiceMatch.getDartsThrown() + " darts this leg");
        System.out.print("Your average for this leg was ");
        System.out.printf("%5.2f%n", practiceMatch.calculateAverage());
    }
}
