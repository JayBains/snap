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
            playerCount = scannerObj.nextLine().trim();
            if (!playerCount.equals("1") && !playerCount.equals("2")) {
                System.out.println("Please enter a valid number of players (1 or 2)");
            }
        } while (!playerCount.equals("1") && !playerCount.equals("2"));

        if (playerCount.equals("1")) {
            startSinglePlayerGame();
        } else {
            startTwoPlayerGame();
        }
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
        System.out.println(playerOne.getName() + " verses " + playerTwo.getName() + ".");
        System.out.println(playerOne.getName() + "'s turn. Press enter key to draw a card.");
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
        if (currentCard.getSymbol().equals(previousCardSymbol)) {
            double timer = System.currentTimeMillis();
            System.out.println("Symbols match! Type 'SNAP' to win.");
            String quickTimeEvent = scannerObj.nextLine().toLowerCase().trim();
            if (quickTimeEvent.equals("snap") && (System.currentTimeMillis() - timer) < 2000) {
                handlePlayerWin();
            } else if (!quickTimeEvent.equals("snap") && (System.currentTimeMillis() - timer) < 2000){
                System.out.println("Incorrect!");
            } else {
                System.out.println("Too slow!");
            }
        } else {
            previousCardSymbol = currentCard.getSymbol();
        }
    }

    private void printCard() {
        if (isPlayerOneTurn && playerCount.equals("2")) {
            System.out.println(currentCard + " drawn by " + playerOne.getName());
        } else if (!isPlayerOneTurn) {
            System.out.println(currentCard + " drawn by " + playerTwo.getName());
        } else {
            System.out.println(currentCard);
        }
    }

    private void handlePlayerWin(){
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

    private void changePlayerTurn(){
        if (!isGameOver && isPlayerOneTurn){
            isPlayerOneTurn = false;
        } else if (!isGameOver){
            isPlayerOneTurn = true;
        }
    }

    private void restartGame() {
        String inputToRestart;
        do {
            System.out.println("Would you like to play again? Y/N");
            inputToRestart = scannerObj.nextLine().toLowerCase().trim();
            if (inputToRestart.equals("y")) {
                remakeDeck();
                isPlayerOneTurn = true;
                System.out.println(playerOne.getName() + " Press enter to draw a card");
            } else if (inputToRestart.equals("n")) {
                System.out.println("Thank you for playing! \nFinal score: " + playerOne.getScore() + " - " + playerTwo.getScore());
                isGameOver = true;
            } else {
                System.out.println("Please enter a valid answer.");
            }
        } while (!inputToRestart.equals("n") && !inputToRestart.equals("y"));
    }
}