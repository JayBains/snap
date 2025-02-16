import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Snap extends CardGame{
    public Snap(String name) {
        super(name);
    }

    Player playerOne = new Player();
    Player playerTwo = new Player();

    public void playGame(){
        Scanner scannerObj = new Scanner(System.in);
        String previousCardSymbol = null;
        boolean gameOver = false;
        boolean playerOneTurn = false;

        System.out.println("How many players are playing? (1 or 2)");
        int input = Integer.parseInt(scannerObj.nextLine());

        if (input == 1) {

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

        } else if (input == 2){

            System.out.println("Player One please enter name: ");
            playerOne.setName(scannerObj.nextLine());

            System.out.println("Player One please enter name: ");
            playerTwo.setName(scannerObj.nextLine());

            System.out.println(playerOne.getName());
            System.out.println(playerTwo.getName());

        }
    }
}
