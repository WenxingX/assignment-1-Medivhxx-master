import java.util.Scanner;
public class Nimsys {
    public static Scanner input;
    public static boolean selection;
    public static void main(String[] args)
    {
        System.out.println("Welcome to Nim");
        System.out.println();
        System.out.println("Please enter a command to continue\n");
        Nimsys nimsys = new Nimsys();
        input = new Scanner(System.in);
        selection = true;


        /*
         * This is the main choose function
         * Four different commands will trigger different methods
         * If the input is not matched initial commands, return this function
         */
        while (selection)
        {
            System.out.println("$ ");
            String SoN = input.next();
            String SoN_Lowercase = SoN.toLowerCase();
            switch (SoN_Lowercase)
            {
                case "start":
                    nimsys.startGame();
                    break;

                case "exit":
                    nimsys.exit();
                    break;

                case "help":
                    nimsys.help();
                    break;

                case "commands":
                    nimsys.commandList();
                    break;

                default:
            }
        }
    }


    /*
     * This method will trigger the start function of the game; Nim.
     * The game will repeat until the players decides not to continue.
     *
     * no param
     */
    public void startGame()
    {
        //Set up 2 new players name with input
        System.out.print("Please enter Player 1's name : ");
        String Player_1 = input.next();
        NimPlayer Player1 = new NimPlayer(Player_1);
        System.out.print("Please enter Player 2's name : ");
        String Player_2 = input.next();
        NimPlayer Player2 = new NimPlayer(Player_2);


        /*The main play function starts here
          When restart the game, set up name function will not be invoked
          This againFlag is for the judgement of whether restart game
         */
        boolean againFlag = true;
        while (againFlag)
        {
            //Set up the upper bound and total stones number
            System.out.print("Enter upper bound : ");
            int upper = input.nextInt();
            System.out.print("Enter initial number of stones : ");
            int stones = input.nextInt();
            System.out.println();

            /*
             * Start to remove the stones
             * this isNotTryAgain boolean is for judging the whether the
             * input is correct
             */
            int j = 0;
            boolean isNotTryAgain = true;
            while(stones>0)
            {
                if (isNotTryAgain) {
                    System.out.printf("%d stones left :", stones);
                    for (int i = stones; i > 0; i--) {
                        System.out.print(" *");
                    }
                    System.out.println();
                }
                isNotTryAgain = true;

                // Judge who's turn with odd & even
                if(j%2==0)
                {
                    System.out.printf("%s's turn. Enter stones to remove : ", Player1.getName());
                }
                else
                {
                    System.out.printf("%s's turn. Enter stones to remove : ", Player2.getName());
                }

                //Remove the stones and do the judgement
                int removeNum = input.nextInt();
                if (removeNum <= upper && removeNum>0 && removeNum <= stones)
                {
                    stones = NimPlayer.removeStone(stones, removeNum);
                    System.out.println();
                    j = j+1;
                }
                else if (removeNum > stones)
                {
                    System.out.printf(
                            "Invalid attempt, only %d stones remaining! Try again :\n", stones);
                    isNotTryAgain = false;
                }
                else
                {
                    System.out.printf(
                            "Upper bound limit exceed, upper bound maximum choice is %d\n", upper);
                    isNotTryAgain = false;
                }
            }
            System.out.println("Game Over");

            //Record the total game rounds with only one object
            Player1.setGames();

            // Judge the winner with odd & even
            if (j%2==0)
            {
                System.out.printf("%s wins!\n",Player1.getName());
                Player1.setWins();
            }
            else
            {
                System.out.printf("%s wins!\n",Player2.getName());
                Player2.setWins();
            }
            System.out.println();


            /*To choose whether restart a new game or not.
              input y or Y will restart the game
              input n or N will print the total result and end the game
              input anything else will maintain this judgement*/
            System.out.print("Do you want to play again (Y/N): ");
            String YoN = input.next();
            if (YoN.toLowerCase().equals("y"))
            {
                againFlag = true;
            }
            else if (YoN.toLowerCase().equals("n"))
            {
                againFlag = false;

                //Choose to print game or games for player1
                if (Player1.getWins() <= 1)
                {
                    System.out.printf("%s won %d game out of %d played\n",
                            Player1.getName(), Player1.getWins(), Player1.getGames());
                }
                else
                {
                    System.out.printf("%s won %d games out of %d played\n",
                            Player1.getName(), Player1.getWins(), Player1.getGames());
                }

                //Choose to print game or games for player2
                if (Player2.getWins() <= 1)
                {
                    System.out.printf("%s won %d game out of %d played\n",
                            Player2.getName(), Player2.getWins(), Player1.getGames());
                }
                else
                {
                    System.out.printf("%s won %d games out of %d played\n",
                            Player2.getName(), Player2.getWins(), Player1.getGames());
                }

            }
            else
            {
                return;
            }
        }
    }


    //This method will trigger the exit function of the game
    public void exit()
    {
        System.out.println("Thank you for playing Nim");
        selection = false;
    }

    //This method will trigger the help function of the game
    public void help()
    {
        System.out.println("Type 'commands' to list all available commands");
        System.out.println("Type 'start' to play game");
        System.out.println("Player to remove the last stone loses!");
    }

    //This method will trigger the command list function of the game
    public void commandList()
    {
        System.out.println(": start");
        System.out.println(": exit");
        System.out.println(": help");
        System.out.println(": commands");
    }
}
