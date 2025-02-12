import java.util.ArrayList;

public class CardGame {
    private String name;
    public ArrayList<Card> deckOfCards = new ArrayList<>();

    private void createDeck(){
        String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♥", "♣", "♦", "♠"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    }
}
