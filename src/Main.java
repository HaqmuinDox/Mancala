import java.util.Random;

public class Main {
    private static final int BOARD_SIZE = 14;
    private static final int P1MANCALA = 6;
    private static final int P2MANCALA = 13;
    private static final int P1FIRSTPOCKET = 0;
    private static final int P1LASTPOCKET = 5;
    private static final int P2FIRSTPOCKET = 7;
    private static final int P2LASTPOCKET = 12;

    private static int[] _gameBoard = new int[BOARD_SIZE];


    public static void main(String[] args) {
        System.out.println("Hello and welcome to Mancala!");

        // p1Pockets = _gameBoard[1] to _gameBoard[6]
        // p2Pockets = _gameBoard[8] to _gameBoard[13]

        startNewGame();

        printGameMap();
        //printGameState();


        for (int i = 0; i < 101; i ++) {
            if (p1PlaysATurn() + p2PlaysATurn() == 2) {
                System.out.println("Game Over in " + i + " moves");
                break;
            }

        }
        //System.out.println("More moves reqd");










    }

    private static int p1PlaysATurn() {
        System.out.println("P1's Turn");
        int sum = 0;
        for(int i = P1FIRSTPOCKET; i <=P1LASTPOCKET; i++) {
            sum = sum + _gameBoard[i];
        }
        if (sum == 0) {
            System.out.println("No possible moves");
            return 1;
        }

        int pocket = getRandomNumberUsingNextInt(P1FIRSTPOCKET, P1LASTPOCKET+1);

        while(_gameBoard[pocket] == 0) {
            pocket = getRandomNumberUsingNextInt(P1FIRSTPOCKET, P1LASTPOCKET+1);
        }
        System.out.println("P1 chose: " + pocket);
        int hand = _gameBoard[pocket];
        _gameBoard[pocket] = 0;

        distributeStones(pocket, hand, "P1");
        System.out.println();

        return 0;
    }

    private static int p2PlaysATurn() {
        System.out.println("P2's Turn");
        int sum = 0;
        for(int i = P2FIRSTPOCKET; i <=P2LASTPOCKET; i++) {
            sum = sum + _gameBoard[i];
        }
        if (sum == 0) {
            System.out.println("No possible moves");
            return 1;
        }

        int pocket = getRandomNumberUsingNextInt(P2FIRSTPOCKET, P2LASTPOCKET+1);

        while(_gameBoard[pocket] == 0) {
            pocket = getRandomNumberUsingNextInt(P2FIRSTPOCKET, P2LASTPOCKET+1);
        }
        System.out.println("P2 chose: " + pocket);
        int hand = _gameBoard[pocket];
        _gameBoard[pocket] = 0;

        distributeStones(pocket, hand, "P2");
        System.out.println();

        return 0;

    }

    private static void distributeStones(int pocket, int hand, String player) {

        if (player.equals("P1")) {
            int carry = (pocket+hand)/BOARD_SIZE;
            int lastPocket = pocket + hand + carry;
            for(int i = pocket + 1; i <= lastPocket; i++) {
                if(i != P2MANCALA) {
                    _gameBoard[i%BOARD_SIZE]++;
                }
            }
            printGameState();
            if (lastPocket % BOARD_SIZE == P1MANCALA) {
                p1PlaysATurn();
            }

        } else {
            int carry = (pocket+hand)/BOARD_SIZE ;
            int lastPocket = pocket + hand + carry - 1;
            for(int i = pocket + 1; i <= lastPocket; i++) {
                if(i != P1MANCALA) {
                    _gameBoard[i%BOARD_SIZE]++;
                }
            }
            printGameState();
            if (lastPocket % BOARD_SIZE == P2MANCALA) {
                p2PlaysATurn();
            }
        }

    }

    private static void startNewGame() {
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
        System.out.printf("P2: [   [");
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
        System.out.printf("P1: [   [");
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
        System.out.printf("P2: [   [");
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
        System.out.printf("P1: [   [");
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