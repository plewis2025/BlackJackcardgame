package com.blackjack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void deal(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public int getValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            card.flip();
            int cardValue = card.getPointValue();
            value += cardValue;
            if (cardValue == 11) aceCount++;
            card.flip();
        }

        // Adjust Ace from 11 → 1 if needed to prevent bust
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public void showHand() {
        for (Card card : cards) {
            card.flip();
            System.out.println(card);
        }
    }
}
