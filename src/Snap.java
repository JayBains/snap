import java.util.NoSuchElementException;
import java.util.Scanner;

public class Snap extends CardGame{
    public Snap(String name) {
        super(name);
    }

    Scanner scannerObj = new Scanner(System.in);
    Player playerOne = new Player();
    Player playerTwo = new Player();
    Card currentCard;
    private String playerCount;
    private String previousCardSymbol = null;
    private boolean isGameOver = false;
    private boolean isPlayerOneTurn = true;

    public void playGame(){
        System.out.println("How many players are playing? (1 or 2)");

        do {
            switch (playerCount = scannerObj.nextLine().trim()) {
                case "1":
                    startSinglePlayerGame();
                    return;
                case "2":
                    startTwoPlayerGame();
                    return;
                default:
                    System.out.println("Please enter a valid number of players (1 or 2)");
            }
        } while (true);
    }

    private void startSinglePlayerGame() {
        System.out.println("Game started. Press enter key to draw a card.");
        while (!isGameOver) {
            try {
                playTurn();
            } catch(NoSuchElementException e) {
                System.out.println(e.getMessage());
                restartGame();
            }
        }
    }

    private void startTwoPlayerGame() {
        inputPlayerName();
        System.out.println(playerOne.getName() + " verses " + playerTwo.getName() + ".\n" + playerOne.getName() + "'s turn. Press enter key to draw a card.");
        while (!isGameOver) {
            try {
                playTurn();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                restartGame();
            }
            changePlayerTurn();
        }
    }

    private void inputPlayerName(){
        System.out.println("Player One please enter name: ");
        do {
            playerOne.setName(scannerObj.nextLine());
        } while(playerOne.getName().isEmpty());

        System.out.println("Player Two please enter name: ");
        do {
            playerTwo.setName(scannerObj.nextLine());
        } while(playerTwo.getName().isEmpty());
    }

    private void playTurn(){
        scannerObj.nextLine();
        currentCard = dealCard();
        printCard();

        if (!currentCard.getSymbol().equals(previousCardSymbol)){
            previousCardSymbol = currentCard.getSymbol();
            return;
        }

        double timer = System.currentTimeMillis();
        System.out.println("Symbols match! Type 'SNAP' to win.");
        String quickTimeEvent = scannerObj.nextLine().toLowerCase().trim();

        if ((System.currentTimeMillis() - timer) > 2000) {
            System.out.println("Too slow! Next turn.");
            return;
        }

        if (!quickTimeEvent.equals("snap")){
            System.out.println("Incorrect!");
            return;
        }

        handlePlayerWin();
    }

    private void printCard() {
        if(!playerCount.equals("2")){
            System.out.println(currentCard);
            return;
        }

        if (!isPlayerOneTurn) {
            System.out.println(currentCard + " drawn by " + playerTwo.getName());
            return;
        }

        System.out.println(currentCard + " drawn by " + playerOne.getName());
    }

    private void handlePlayerWin(){
        if (isPlayerOneTurn){
            System.out.println("SNAP! " + playerOne.getName() + " You win.");
            playerOne.addScore();
            restartGame();
            return;
        }

        System.out.println("SNAP! " + playerTwo.getName() + " You win.");
        playerTwo.addScore();
        restartGame();
    }

    private void changePlayerTurn(){
        if (isGameOver){
            return;
        }

        isPlayerOneTurn = !isPlayerOneTurn;
    }

    private void restartGame() {
        System.out.println("Would you like to play again? Y/N");

        do {
            switch (scannerObj.nextLine().toLowerCase().trim()){
                case "y":
                    remakeDeck();
                    isPlayerOneTurn = true;
                    System.out.println(playerOne.getName() + " Press enter to draw a card");
                    return;
                case "n":
                    System.out.println("Thank you for playing! Final score: " + playerOne.getScore() + " - " + playerTwo.getScore());
                    isGameOver = true;
                    return;
                default:
                    System.out.println("Please enter a valid answer. Play again, Y or N ?");
            }
        } while (true);
    }
}