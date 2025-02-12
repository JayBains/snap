import java.util.ArrayList;

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
}
