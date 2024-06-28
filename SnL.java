import java.util.ArrayList;
import java.util.Scanner;

/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 14
 * Members  :
 * 1. 5026231193 - Jonathan Berlianto
 * 2. 5026231229 - Lailatul Fitaliqoh
 * 3. 5026231232 - Bara Ardiwinata
 * ------------------------------------------------------
 */

public class SnL {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;
    private int turn;

    public SnL(int s) {
        this.boardSize = s;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.gameStatus = 0;
        this.turn = 0;
    }

    public void setBoardSize(int s) {
        this.boardSize = s;
    }

    public void setGameStatus(int s) {
        this.gameStatus = s;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        Player playerInTurn;
        ArrayList<String> playerName = new ArrayList<String>();
        ArrayList<Player> playerObjects = new ArrayList<Player>();
        Scanner read = new Scanner(System.in);
        String inputPlayer = "";
        int i = 0;
        do {
            i++;
            System.out.println("Please enter Player " + i + ": ");
            inputPlayer = read.nextLine();
            if (!inputPlayer.equals("-")) {
                playerName.add(inputPlayer);
                Player objectPlayer = new Player(inputPlayer);
                playerObjects.add(objectPlayer);
                addPlayer(playerObjects.get(i - 1));
            } else break;
        } while (!inputPlayer.equals("-"));

        String playAgainMes = "";
        do {
            initiateGame();
            resetGame();
            do {
                playerInTurn = getWhoseTurn();
                boolean playAgain;
                do {
                    playAgain = false;
                    System.out.println("Now Playing " + playerInTurn.getName());
                    System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
                    String enter = read.nextLine();
                    int x = 0;
                    if (enter.isEmpty()) {
                        x = playerInTurn.rollDice();
                        Main.playDiceSound();  // Tambahkan pemutaran suara dadu di sini
                    }
                    System.out.println("Dice Number : " + x);
                    movePlayerAround(playerInTurn, x);
                    System.out.println("New Position: " + playerInTurn.getPosition());
                    System.out.println("==============================================");
                    if (x == 6) {
                        playAgain = true;
                        System.out.println(playerInTurn.getName() + " rolled a 6 and gets to play again!");
                    }
                    if (checkExtraTurn(playerInTurn.getPosition())) {
                        playAgain = true;
                        System.out.println(playerInTurn.getName() + " gets to play again!");
                    }
                } while (playAgain && getGameStatus() != 2);
            } while (getGameStatus() != 2);
            System.out.println("The winner is: " + playerInTurn.getName());
            Main.playGameEndSound();  // Tambahkan pemutaran suara kemenangan di sini
            playerInTurn.addPoint();
            System.out.print("Do you want to play again? Y/N ");
            playAgainMes = read.nextLine();

        } while (playAgainMes.equalsIgnoreCase("Y"));
    }

    public void addPlayer(Player s) {
        this.players.add(s);
    }

    public ArrayList<Player> getPlayers(Player s) {
        return this.players;
    }

    public void addSnake(Snake s) {
        this.snakes.add(s);
    }

    public void addSnakes(int[][] s) {
        for (int r = 0; r < s.length; r++) {
            Snake snake = new Snake(s[r][0], s[r][1]);
            this.snakes.add(snake);
        }
    }

    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }

    public void addLadders(int[][] l) {
        for (int r = 0; r < l.length; r++) {
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public void initiateGame() {
        int[][] l = {
                {2, 23},
                {8, 34},
                {20, 77},
                {32, 68},
                {41, 79},
                {74, 88},
                {82, 100},
                {85, 95}
        };
        addLadders(l);

        int[][] s = {
                {5, 47},
                {9, 29},
                {15, 38},
                {25, 97},
                {33, 53},
                {37, 62},
                {54, 86},
                {70, 92}
        };

        addSnakes(s);
    }

    public void movePlayerAround(Player p, int x) {
        p.moveAround(x, this.boardSize);
        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getBottomPosition()) {
                System.out.println(p.getName() + " you got Ladder from: " + l.getBottomPosition() + " To: " + l.getTopPosition());
                p.setPosition(l.getTopPosition());
                Main.playLadderSound();  // Tambahkan pemutaran suara tangga di sini
            }
        }
        for (Snake s : this.snakes) {
            if (p.getPosition() == s.getHeadPosition()) {
                p.setPosition(s.getTailPosition());
                System.out.println(p.getName() + " you get snake head from " + s.getHeadPosition() + " slide down to " + s.getTailPosition());
                Main.playSnakeSound();  // Tambahkan pemutaran suara ular di sini
            }
        }
        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    public Player getWhoseTurn() {
        if (this.gameStatus == 0) {
            this.gameStatus = 1;
            this.turn = (int) (Math.random() * players.size());
            return this.players.get(turn);
        } else {
            this.turn = (this.turn + 1) % (this.players.size());
            return this.players.get(turn);
        }
    }

    private boolean checkExtraTurn(int position) {
        int[] extraTurnPositions = {15, 35, 55, 75, 95};
        for (int pos : extraTurnPositions) {
            if (position == pos) {
                return true;
            }
        }
        return false;
    }

    private void resetGame() {
        this.gameStatus = 0;
        this.turn = 0;
        for (Player player : this.players) {
            player.setPosition(0);
        }
        System.out.println("Game reset. Ready to start a new game.");
    }
}