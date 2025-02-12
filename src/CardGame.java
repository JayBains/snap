import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardGame {
    public ArrayList<Card> deckOfCards;

    public CardGame() {
        this.deckOfCards = new ArrayList<>();
        createDeck();
    }

    private void createDeck(){
        String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (String suit : suits) {
            for (int i = 0; i < symbols.length; i++){
                deckOfCards.add(new Card(suit, symbols[i], values[i]));
            }
        }
    }

    public void listDeck(){
        for (Card card : deckOfCards){
            System.out.println(card);
        }
    }

    public Card dealCard(){
        if (deckOfCards.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck");
        }
        Card topCard = deckOfCards.getFirst();
        System.out.println(topCard);
        deckOfCards.removeFirst();
        return topCard;
    }

    public ArrayList<Card> sortDeckInNumberOrder() {
        deckOfCards.sort(Comparator.comparing(Card::getValue));
        return deckOfCards;
    }

    public ArrayList<Card> sortDeckIntoSuits() {
        deckOfCards.sort(Comparator.comparing(Card::getSuit));
        return deckOfCards;
    }

    public ArrayList<Card> shuffleDeck(){
        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }
}
