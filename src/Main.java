public class Main {
    public static void main(String[] args) {

        Snap newGame = new Snap("Snap");
        newGame.shuffleDeck();
        newGame.playGame();
    }
}