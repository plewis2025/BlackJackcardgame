package com.blackjack;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        System.out.println("Welcome to Blackjack!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // Deal 2 cards to player and dealer
        playerHand.deal(deck.deal());
        playerHand.deal(deck.deal());
        dealerHand.deal(deck.deal());
        dealerHand.deal(deck.deal());

        System.out.println("\n" + playerName + "'s Hand:");
        playerHand.showHand();
        System.out.println("Total value: " + playerHand.getValue());

        boolean playerTurn = true;

        // Player's turn
        while (playerTurn) {
            System.out.print("\nDo you want to (H)it or (S)tay? ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("h")) {
                playerHand.deal(deck.deal());
                System.out.println("\n" + playerName + "'s Hand:");
                playerHand.showHand();
                System.out.println("Total value: " + playerHand.getValue());

                if (playerHand.getValue() > 21) {
                    System.out.println("\n💥 " + playerName + " busts! You went over 21.");
                    playerTurn = false;
                    break;
                }
            } else if (choice.equals("s")) {
                playerTurn = false;
            } else {
                System.out.println("Invalid choice. Please type H or S.");
            }
        }

        // Dealer's turn
        System.out.println("\nDealer's turn...");
        dealerHand.showHand();
        System.out.println("Dealer total: " + dealerHand.getValue());

        while (dealerHand.getValue() < 17) {
            System.out.println("Dealer hits!");
            dealerHand.deal(deck.deal());
            dealerHand.showHand();
            System.out.println("Dealer total: " + dealerHand.getValue());
        }

        if (dealerHand.getValue() > 21) {
            System.out.println("\n💥 Dealer busts! " + playerName + " wins!");
        } else if (playerHand.getValue() > 21) {
            System.out.println("\nDealer wins!");
        } else if (playerHand.getValue() > dealerHand.getValue()) {
            System.out.println("\n🏆 " + playerName + " wins!");
        } else if (playerHand.getValue() < dealerHand.getValue()) {
            System.out.println("\nDealer wins!");
        } else {
            System.out.println("\nIt's a tie!");
        }

        System.out.println("\nThanks for playing Blackjack!");
        scanner.close();
    }
}
