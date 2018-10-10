/**
 * You get a deck of cards with n cards. Then you turn every card, every second,
 * every third, every fourth and so on until you turn the n-th card. 
 * The question is how many cards are then face-up? I give you an example of 
 * a deck with 4 cards. 0 represents hidden and 1 means open 
 * 0: 0 0 0 0 
 * 1: 1 1 1 1 
 * 2: 1 0 1 0 
 * 3: 1 0 0 0 
 * 4: 1 0 0 1 
 * So the answer for n = 4 would be two. 
 * I now challenge you to write a code to solve this puzzle for a general n. 
 * You can also solve it mathematicaly and you will get a much easier..
 */
package flippingcards;

import java.util.Arrays;
import java.util.Scanner;

public final class FlippingCards {
    
    private final int numberOfCards;
    private final boolean[] deckOfCards;
    private String turnByTurn;

    public static void main(String[] args) {
        FlippingCards fp = new FlippingCards(input());
        fp.flipping();
        System.out.printf("Number of cards: %d\t"
                + "Square root of the cards number: %d\t"
                + "Number of face-up cards: %d%n"
                + "Turn-by-turn:%n%s%n", 
                fp.numberOfCards, 
                (int) Math.sqrt(fp.numberOfCards), 
                fp.count(),
                fp.turnByTurn);
    }

    public FlippingCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
        this.deckOfCards = new boolean[this.numberOfCards];
        this.turnByTurn = "0: " + this.toString();
    }
    
    public void turning(int increment) {
        for (int i = increment - 1; i < this.deckOfCards.length; i += increment) {
            this.deckOfCards[i] = !this.deckOfCards[i];
        }
    }
    
    public void flipping() {
        for (int i = 1; i < this.numberOfCards + 1; i++) {
            this.turning(i);
            this.turnByTurn += String.format("\n%d: %s", i, this.toString());
        }
    }
    
    public int count() {
        int count = 0;
        for (boolean card : deckOfCards) {
            count += card ? 1 : 0;
        }
        return count;
    }
    
    private static int input() {
        int number = 0;
        while (number < 1) {
            System.out.print("Enter the number of cards: ");
            try {
                String nextLine = new Scanner(System.in).nextLine();
                number = Integer.parseInt(nextLine);
            } catch(NumberFormatException e) {
                System.err.println(e);
                System.out.println("You have to enter a positive whole number!");
            }
        }
        return number;
    }

    @Override
    public String toString() {
        int[] array = new int[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            array[i] = deckOfCards[i] ? 1 : 0;
        }
        return Arrays.toString(array);
    }
    
}