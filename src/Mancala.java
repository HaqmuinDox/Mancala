import java.util.Random;
import java.util.Scanner;

public class Mancala {
    static final int BOARD_SIZE = 14;
    static final int P1MANCALA = 6;
    static final int P2MANCALA = 13;
    static final int P1FIRSTPOCKET = 0;
    static final int P1LASTPOCKET = 5;
    static final int P2FIRSTPOCKET = 7;
    static final int P2LASTPOCKET = 12;


    static int[] _gameBoard = new int[BOARD_SIZE];
    static int _minOfAllGames = 24;
    static Scanner scanner = new Scanner(System.in);

    static void playMancala(int j) {
        startNewGame();
        for (int i = 1; i <= j; i ++) {
            if (p1PlaysATurn() + p2PlaysATurn() == 2) {
                if (_minOfAllGames > Math.min(_gameBoard[P1MANCALA], _gameBoard[P2MANCALA])) {
                    _minOfAllGames = Math.min(_gameBoard[P1MANCALA], _gameBoard[P2MANCALA]);
                }

                System.out.print("Game Over in " + i + " moves. ");
                if (_gameBoard[P1MANCALA] > _gameBoard[P2MANCALA]) {
                    System.out.println("P1 won with " + _gameBoard[P1MANCALA] + ":" + _gameBoard[P2MANCALA]);
                } else if (_gameBoard[P1MANCALA] < _gameBoard[P2MANCALA]) {
                    System.out.println("P2 won with " + _gameBoard[P1MANCALA] + ":" + _gameBoard[P2MANCALA]);
                } else {
                    System.out.println("Draw");
                }
                break;
            }

        }
    }

    static void playUserMancala(int maxMoves) {
        startNewGame();
        for (int i = 1; i <= maxMoves; i++) {
            // Check for game end condition before P1's turn
            if (isGameOver()) break;

            userPlaysATurn();

            if (isGameOver()) break;

            p2PlaysATurnAgainstUser();

            if (isGameOver()) break;
        }
        printGameOver();
    }

    private static void userPlaysATurn() {
        printGameState();

        int sum = 0;
        for(int i = P1FIRSTPOCKET; i <= P1LASTPOCKET; i++) sum += _gameBoard[i];
        if (sum == 0) return;

        int choice = -1;
        while (true) {
            System.out.print("P1 Turn - Choose pocket (1-6): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                int actualIndex = choice - 1; // Map 1-6 to 0-5

                if (choice >= 1 && choice <= 6 && _gameBoard[actualIndex] > 0) {
                    int hand = _gameBoard[actualIndex];
                    _gameBoard[actualIndex] = 0;
                    distributeStones(actualIndex, hand, "P1");
                    break;
                }
            } else {
                scanner.next(); // clear invalid input
            }
            System.out.println("Invalid move. Choose a non-empty pocket between 1 and 6.");
        }
    }

    private static void p2PlaysATurnAgainstUser() {
        int sum = 0;
        for(int i = P2FIRSTPOCKET; i <= P2LASTPOCKET; i++) sum += _gameBoard[i];
        if (sum == 0) return;

        int pocket = getRandomNumberUsingNextInt(P2FIRSTPOCKET, P2LASTPOCKET + 1);
        while(_gameBoard[pocket] == 0) {
            pocket = getRandomNumberUsingNextInt(P2FIRSTPOCKET, P2LASTPOCKET + 1);
        }

        System.out.println("\n--- P2 (AI) chose pocket: " + (pocket - 6) + " ---");
        int hand = _gameBoard[pocket];
        _gameBoard[pocket] = 0;
        distributeStones(pocket, hand, "P2");
    }

    private static boolean isGameOver() {
        int p1Sum = 0;
        int p2Sum = 0;
        for(int i = P1FIRSTPOCKET; i <= P1LASTPOCKET; i++) p1Sum += _gameBoard[i];
        for(int i = P2FIRSTPOCKET; i <= P2LASTPOCKET; i++) p2Sum += _gameBoard[i];
        return (p1Sum == 0 || p2Sum == 0);
    }
    private static void printGameOver() {
        printGameState();
        System.out.println("Game Over!");
        if (_gameBoard[P1MANCALA] > _gameBoard[P2MANCALA]) {
            System.out.println("You wins! " + _gameBoard[P1MANCALA] + ":" + _gameBoard[P2MANCALA]);
        } else if (_gameBoard[P1MANCALA] < _gameBoard[P2MANCALA]) {
            System.out.println("Computer wins! " + _gameBoard[P1MANCALA] + ":" + _gameBoard[P2MANCALA]);
        } else {
            System.out.println("It's a Draw!");
        }
    }

    private static int p1PlaysATurn() {
        //System.out.println("P1's Turn");
        int sum = 0;
        for(int i = P1FIRSTPOCKET; i <=P1LASTPOCKET; i++) {
            sum = sum + _gameBoard[i];
        }
        if (sum == 0) {
            //System.out.println("No possible moves");
            //System.out.println();
            return 1;
        }

        int pocket = getRandomNumberUsingNextInt(P1FIRSTPOCKET, P1LASTPOCKET+1);

        while(_gameBoard[pocket] == 0) {
            pocket = getRandomNumberUsingNextInt(P1FIRSTPOCKET, P1LASTPOCKET+1);
        }
        //System.out.println("P1 chose: " + pocket);
        int hand = _gameBoard[pocket];
        _gameBoard[pocket] = 0;

        distributeStones(pocket, hand, "P1");
        //System.out.println();

        return 0;
    }

    private static int p2PlaysATurn() {
        //System.out.println("P2's Turn");
        int sum = 0;
        for(int i = P2FIRSTPOCKET; i <=P2LASTPOCKET; i++) {
            sum = sum + _gameBoard[i];
        }
        if (sum == 0) {
            //System.out.println("No possible moves");
            //System.out.println();
            return 1;
        }

        int pocket = getRandomNumberUsingNextInt(P2FIRSTPOCKET, P2LASTPOCKET+1);

        while(_gameBoard[pocket] == 0) {
            pocket = getRandomNumberUsingNextInt(P2FIRSTPOCKET, P2LASTPOCKET+1);
        }
        //System.out.println("P2 chose: " + pocket);
        int hand = _gameBoard[pocket];
        _gameBoard[pocket] = 0;

        distributeStones(pocket, hand, "P2");
        //System.out.println();

        return 0;

    }

    private static void distributeStones(int pocket, int hand, String player) {

        if (player.equals("P1")) {
            int carry = (hand)/BOARD_SIZE;
            int lastPocket = pocket + hand + carry;
            for(int i = pocket + 1; i <= lastPocket; i++) {
                if(i != P2MANCALA) {
                    _gameBoard[i%BOARD_SIZE]++;
                }
            }
            if (lastPocket % BOARD_SIZE == P1MANCALA) {
                //printGameState();
                p1PlaysATurn();
            } else if (_gameBoard[lastPocket % BOARD_SIZE] == 1) {
                endedOnEmptySpace(lastPocket % BOARD_SIZE);
            }
            //printGameState();


        } else {
            int carry = (hand)/BOARD_SIZE ;
            int lastPocket = pocket + hand + carry;
            for(int i = pocket + 1; i <= lastPocket; i++) {
                if(i != P1MANCALA) {
                    _gameBoard[i%BOARD_SIZE]++;
                }
            }
            if (lastPocket % BOARD_SIZE == P2MANCALA) {
                p2PlaysATurn();
            } else if (_gameBoard[lastPocket % BOARD_SIZE] == 1 && ((lastPocket % BOARD_SIZE) != P2MANCALA)) {
                //System.out.println(lastPocket % BOARD_SIZE);
                endedOnEmptySpace(lastPocket % BOARD_SIZE);
            }
            //printGameState();
        }

    }

    private static void endedOnEmptySpace(int pocket) {
        if (pocket< P1LASTPOCKET && pocket > P1FIRSTPOCKET) {
            int t = _gameBoard[pocket] + _gameBoard[12 - pocket];
            _gameBoard[pocket] = 0;
            _gameBoard[12 - pocket] = 0;
            _gameBoard[P1MANCALA] += t;
        } else if (pocket > P2FIRSTPOCKET && pocket < P2LASTPOCKET) {
            int mirror = (12 - pocket);
            int t = _gameBoard[pocket] + _gameBoard[mirror];
            _gameBoard[pocket] = 0;
            _gameBoard[mirror] = 0;
            _gameBoard[P2MANCALA] += t;
        }
    }

    static void startNewGame() {
        for (int i = P1FIRSTPOCKET; i <= P1LASTPOCKET; i++) {
            _gameBoard[i] = 4;
        }
        _gameBoard[P1MANCALA] = 0;

        for (int i = P2FIRSTPOCKET; i <= P2LASTPOCKET; i++) {
            _gameBoard[i] = 4;
        }
        _gameBoard[P2MANCALA] = 0;
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void printGameState() {
        System.out.print("P2: [   [");
        for (int i = P2LASTPOCKET; i >= P2FIRSTPOCKET; i--) {
            if (i != P2FIRSTPOCKET) {
                if (_gameBoard[i] < 10) {
                    System.out.printf(" " + _gameBoard[i] + ", ");
                } else {
                    System.out.printf(_gameBoard[i] + ", ");
                }
            } else {
                if (_gameBoard[i] < 10) {
                    System.out.println(" " + _gameBoard[i] + "]   ]");
                } else {
                    System.out.println(_gameBoard[i] + "]   ]");
                }

            }
        }
        System.out.println("    [ " + _gameBoard[P2MANCALA] + "                          " + _gameBoard[P1MANCALA] + " ]");
        System.out.print("P1: [   [");
        for (int i = P1FIRSTPOCKET; i <= P1LASTPOCKET; i++) {
            if (i != P1LASTPOCKET) {
                if (_gameBoard[i] < 10) {
                    System.out.printf(" " + _gameBoard[i] + ", ");
                } else {
                    System.out.printf(_gameBoard[i] + ", ");
                }
            } else {
                if (_gameBoard[i] < 10) {
                    System.out.println(" " + _gameBoard[i] + "]   ]");
                } else {
                    System.out.println(_gameBoard[i] + "]   ]");
                }

            }
        }
    }

    public static void printGameMap() {
        System.out.print("P2: [   [");
        for (int i = P2LASTPOCKET; i >= P2FIRSTPOCKET; i--) {
            if (i != P2FIRSTPOCKET) {
                if (i < 10) {
                    System.out.printf(" " + i + ", ");
                } else {
                    System.out.printf(i + ", ");
                }
            } else {
                if (i < 10) {
                    System.out.println(" " + i + "]   ]");
                } else {
                    System.out.println(i + "]   ]");
                }

            }
        }
        System.out.println("    [ " + P2MANCALA + "                         " + P1MANCALA + " ]");
        System.out.print("P1: [   [");
        for (int i = P1FIRSTPOCKET; i <= P1LASTPOCKET; i++) {
            if (i != P1LASTPOCKET) {
                if (i < 10) {
                    System.out.printf(" " + i + ", ");
                } else {
                    System.out.printf(i + ", ");
                }
            } else {
                if (i < 10) {
                    System.out.println(" " + i + "]   ]");
                } else {
                    System.out.println(i + "]   ]");
                }

            }
        }
    }
}
