public class Player {
    private String name;
    private int score;

    public Player() {
        this.name = "";
        this.score = 0;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getScore(){
        return this.score;
    }

    public void addScore(){
        this.score++;
    }
}
