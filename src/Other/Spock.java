package Other;


import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cvielma
 */
public class Spock {

    public static byte name_to_number(String name) {
        if ("rock".equals(name)) {
            return 0;
        }
        if ("Spock".equals(name)) {
            return 1;
        }
        if ("paper".equals(name)) {
            return 2;
        }
        if ("lizard".equals(name)) {
            return 3;
        }
        if ("scissors".equals(name)) {
            return 4;
        }
        System.out.println("Sorry, that's not a valid name");
        return -1;
    }

    public static String number_to_name(byte number) {
        if (number == 0) {
            return "rock";
        }
        if (number == 1) {
            return "Spock";
        }
        if (number == 2) {
            return "paper";
        }
        if (number == 3) {
            return "lizard";
        }
        if (number == 4) {
            return "scissors";
        }
        return "Impossible to match, incorrect number";
    }

    public static void rpsls(String player_choice) {
        System.out.println("");
        System.out.println("Player chooses " + player_choice);
        byte player_number = name_to_number(player_choice);
        if (player_number >= 0) {
            byte comp_number = (byte) (Math.random() * 5);
            String comp_choice = number_to_name(comp_number);
            System.out.println("Computer chooses " + comp_choice);
            int difference = Math.abs((comp_number - player_number) % 5);
            if (difference == 1 || difference == 2) {
                System.out.println("Computer wins!");
            } else if (difference == 3 || difference == 4) {
                System.out.println("Player wins!");
            } else if (difference == 0) {
                System.out.println("Player and Computer tie!");
            }
        }
    }

    public static void main(String[] args) {
        rpsls("rock");
        rpsls("Spock");
        rpsls("paper");
        rpsls("lizard");
        rpsls("scissors");
    }
 }
