import java.lang.String;
public class NimPlayer{

    private final String name;

    private int wins = 0;

    private int games = 0;


    public NimPlayer(String name)
    {
        this.name = name;
    }


    public String getName()
    {
        return this.name;
    }


    public static int removeStone(int count, int num)
    {
        return count - num;
    }


    public void setWins()
    {
        this.wins++;
    }


    public void setGames()
    {
        this.games++;
    }


    public int getWins()
    {
        return wins;
    }


    public int getGames()
    {
        return games;
    }
}

/*Your full name: Wenxing Xu
  Your username : WENXINGX1
  Github repository link: https://github.com/COMP90041/assignment-1-Medivhxx
  */



