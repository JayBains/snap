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
    private String previousCardSymbol = null;
    private boolean gameOver = false;
    private boolean isPlayerOneTurn = true;

    public void playGame(){
        String playerCount;
        System.out.println("How many players are playing? (1 or 2)");
        do {
            playerCount = scannerObj.nextLine();
            if (!playerCount.equals("1") && !playerCount.equals("2")) {
                System.out.println("Please enter a valid number of players (1 or 2)");
            }
        } while (!playerCount.equals("1") && !playerCount.equals("2"));

        if (playerCount.equals("1")) {
            singlePlayer();
        } else {
            twoPlayer();
        }
    }

    private void singlePlayer() {
        System.out.println("Game started. Press enter key to draw a card.");
        while (!gameOver) {
            try {
                playTurn();
            } catch(NoSuchElementException e) {
                System.out.println(e.getMessage());
                restartGame();
            }
        }
    }

    private void twoPlayer() {
        inputPlayerName();
        System.out.println(playerOne.getName() + " verses " + playerTwo.getName() + ".");
        System.out.println(playerOne.getName() + "'s turn. Press enter key to draw a card.");
        while (!gameOver) {
            try {
                playTurn();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                restartGame();
            }
            nextPlayerTurn();
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

        if (currentCard.getSymbol().equals(previousCardSymbol)) {
            double timer = System.currentTimeMillis();
            System.out.println("Symbols match! Type 'SNAP' to win.");
            String quickTimeEvent = scannerObj.nextLine().toLowerCase().trim();
            if (quickTimeEvent.equals("snap") && (System.currentTimeMillis() - timer) < 2000) {
                turnWin();
            } else if (!quickTimeEvent.equals("snap") && (System.currentTimeMillis() - timer) < 2000){
                System.out.println("Incorrect!");
            } else {
                System.out.println("Too slow!");
            }
        } else {
            previousCardSymbol = currentCard.getSymbol();
        }
    }

    private void turnWin(){
        if (isPlayerOneTurn){
            System.out.println("SNAP! " + playerOne.getName() + " You win.");
            playerOne.addScore();
            restartGame();
        } else {
            System.out.println("SNAP! " + playerTwo.getName() + " You win.");
            playerTwo.addScore();
            restartGame();
        }
    }

    private void nextPlayerTurn(){
        if (!gameOver && isPlayerOneTurn){
            System.out.println(playerTwo.getName() + "'s turn.");
            isPlayerOneTurn = false;
        } else if (!gameOver){
            System.out.println(playerOne.getName() + "'s turn.");
            isPlayerOneTurn = true;
        }
    }

    private void restartGame() {
        String inputL;
        do {
            System.out.println("Would you like to play again? Y/N");
            inputL = scannerObj.nextLine().toLowerCase().trim();
            if (inputL.equals("y")) {
                remakeDeck();
            } else if (inputL.equals("n")) {
                System.out.println("Thank you for playing! \nFinal score: " + playerOne.getScore() + " - " + playerTwo.getScore());
                gameOver = true;
            } else {
                System.out.println("Please enter a valid answer.");
            }
        } while (!inputL.equals("n") && !inputL.equals("y"));
    }
}