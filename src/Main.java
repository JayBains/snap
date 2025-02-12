public class Main {
    public static void main(String[] args) {
        CardGame newGame = new CardGame();
        newGame.listDeck();
        newGame.shuffleDeck();
        newGame.listDeck();
        newGame.sortDeckInNumberOrder();
        newGame.listDeck();
        newGame.sortDeckIntoSuits();
        newGame.listDeck();
        newGame.dealCard();
    }
}