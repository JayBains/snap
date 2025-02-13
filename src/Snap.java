import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Snap extends CardGame{
    public Snap(String name) {
        super(name);
    }


    public void playGame(){
        Scanner scannerObj = new Scanner(System.in);
        String previousCardSymbol = null;
        boolean gameOver = false;

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
}
