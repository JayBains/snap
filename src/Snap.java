import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Snap extends CardGame{
    public Snap(String name) {
        super(name);
    }

    Scanner scannerObj = new Scanner(System.in);
    Player playerOne = new Player();
    Player playerTwo = new Player();

    private boolean gameOver = false;
    private String previousCardSymbol = null;
    private int playerTurn = 1;

    public void playGame(){
        Scanner scannerObj = new Scanner(System.in);
        int playerCount;

        System.out.println("How many players are playing? (1 or 2)");
        do {
            playerCount = scannerObj.nextInt();
            if (playerCount != 1 && playerCount != 2) {
                System.out.println("Please enter a valid number of players (1 or 2)");
            }
        } while (playerCount != 1 && playerCount != 2);

        if (playerCount == 1) {
            singlePlayer();
        } else {
            twoPlayer();
        }
    }

    private void singlePlayer() {
        System.out.println("Game started. Press enter key to draw a card.");
        while (!gameOver) {
            try {
                scannerObj.nextLine();
                Card currentCard = dealCard();
                if (Objects.equals(currentCard.getSymbol(), previousCardSymbol)){
                    System.out.println("SNAP!");
                    gameOver = true;
                } else {
                    previousCardSymbol = currentCard.getSymbol();
                }
            } catch(NoSuchElementException e) {
                System.out.println(e.getMessage());
                gameOver = true;
            }
        }
    }

    private void twoPlayer() {
        getPlayerName();
        System.out.println(playerOne.getName() + " verses " + playerTwo.getName() + ".");
        System.out.println(playerOne.getName() + "'s turn. Press enter key to draw a card.");

        while (!gameOver) {
            try {
                scannerObj.nextLine();
                Card currentCard = dealCard();

                if (Objects.equals(currentCard.getSymbol(), previousCardSymbol)) {
                    if (playerTurn == 1) {
                        System.out.println("SNAP! " + playerOne.getName() + " wins.");
                    } else {
                        System.out.println("SNAP! " + playerTwo.getName() + " wins.");
                    }
                    gameOver = true;
                } else {
                    previousCardSymbol = currentCard.getSymbol();
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                gameOver = true;
            }

            if (!gameOver && playerTurn == 1){
                System.out.println(playerTwo.getName() + "'s turn.");
                playerTurn++;
            } else if (!gameOver && playerTurn == 2){
                System.out.println(playerOne.getName() + "'s turn.");
                playerTurn--;
            }
        }
    }

    private void getPlayerName(){
        System.out.println("Player One please enter name: ");
        do {
            playerOne.setName(scannerObj.nextLine());
        } while(playerOne.getName().isEmpty());

        System.out.println("Player Two please enter name: ");
        do {
            playerTwo.setName(scannerObj.nextLine());
        } while(playerTwo.getName().isEmpty());
    }
}
