import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CardGame {
    public ArrayList<Card> deckOfCards;
    String name;

    public CardGame(String name) {
        this.deckOfCards = new ArrayList<>();
        this.name = name;
        createDeck();
        shuffleDeck();
    }

    private void createDeck(){
        deckOfCards.clear();
        String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (String suit : suits) {
            for (int i = 0; i < symbols.length; i++){
                deckOfCards.add(new Card(suit, symbols[i], values[i]));
            }
        }
    }

    public ArrayList<Card> getDeck(){
        return deckOfCards;
    }

    public Card dealCard(){
        if (deckOfCards.isEmpty()){
            throw new NoSuchElementException("No cards left in the deck.");
        }
        return deckOfCards.removeFirst();
    }

    public void sortDeckInNumberOrder() {
        deckOfCards.sort(Comparator.comparing(Card::getValue));
    }

    public void sortDeckIntoSuits() {
        deckOfCards.sort(Comparator.comparing(Card::getSuit));
    }

    public void shuffleDeck(){
        Collections.shuffle(deckOfCards);
    }

    public void remakeDeck(){
        createDeck();
        shuffleDeck();
    }
}
