public class Main {
    public static void main(String[] args) {
        CardGame newGame = new CardGame();
        System.out.println(newGame.getDeck());
        newGame.shuffleDeck();
        System.out.println(newGame.getDeck());
        newGame.sortDeckInNumberOrder();
        System.out.println(newGame.getDeck());
        newGame.sortDeckIntoSuits();
        System.out.println(newGame.getDeck());
        newGame.shuffleDeck();
        System.out.println(newGame.getDeck());
        newGame.dealCard();
        newGame.dealCard();
        newGame.dealCard();
        newGame.dealCard();
        newGame.dealCard();
        System.out.println(newGame.getDeck());
    }
}