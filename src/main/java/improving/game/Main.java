package improving.game;

import java.util.*;

public class Main {
    public static int numRows = 8;
    public static int numCols = 8;
    public static int playerShips;
    public static int computerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];

    public static int numberOfshipsHit = 0;

    public static void main(String[] args){
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        createOceanMap();
        //deployComputerShips();
        do {
            Battle();
        }while(BattleShips.computerShips != 0);
        gameOver();
    }

    public static void createOceanMap(){
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + grid[i][j]);
                else if (j == grid[i].length - 1)
                    System.out.print(grid[i][j] + "|" + i);
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void deployComputerShips(int shipSize){
        System.out.println("\nComputer is deploying ships");
        BattleShips.computerShips = 4;
        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = (int)(Math.random() * 8); // 0-6
            int y = (int)(Math.random() * 8);

            if( (grid[x][y] == " "))
            {
                grid[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
        printOceanMap();
    }

    public static void Battle(){
        playerTurn();
        printOceanMap();

        System.out.println();
        System.out.println("Computer ships: " + BattleShips.computerShips);
        System.out.println();
    }

    public static void playerTurn(){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "x")
                {
                    System.out.println("Boom! You sunk the ship!");
                    grid[x][y] = "!";
                    --BattleShips.computerShips;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("Sorry, you missed");
                    grid[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));
    }

    public static void gameOver(){
        System.out.println("Computer ships: " + BattleShips.computerShips);
        if(BattleShips.computerShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
    }

    public static void printOceanMap(){
        System.out.println();
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int x = 0; x < grid.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++){
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);
        }

        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }
}