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
        String[] suits = {"♥", "♣", "♦", "♠"};

        for (String suit : suits) {
            for (Symbol symbols : Symbol.values()){
                deckOfCards.add(new Card(suit, symbols.getSymbol(), symbols.getValue()));
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
