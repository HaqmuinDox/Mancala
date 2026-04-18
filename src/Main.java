import java.util.Random;

public class Main {
    private static int[] _gameBoard = new int[14];
    private static int _p1Mancala = _gameBoard[6];
    private static int _p2Mancala = _gameBoard[13];
    private static int _p1FirstPocket = 0;
    private static int _p1LastPocket = 5;
    private static int _p2FirstPocket = 7;
    private static int _p2LastPocket = 12;

    public static void main(String[] args) {
        System.out.println("Hello and welcome to Mancala!");

        // p1Pockets = _gameBoard[1] to _gameBoard[6]
        // p2Pockets = _gameBoard[8] to _gameBoard[13]

        startNewGame();

        //printGameMap();
        printGameState();

        p1PlaysATurn();




    }

    private static void p1PlaysATurn() {
        int pocket = getRandomNumberUsingNextInt(1, 7);

        while(_gameBoard[pocket] == 0) {
            pocket = getRandomNumberUsingNextInt(1, 7);
        }
        int hand = _gameBoard[pocket];
        _gameBoard[pocket] = 0;

        distributeStones("P1");

    }

    private static void distributeStones(String player) {
        if (player.equals("P1")) {


        }
    }

    private static void startNewGame() {
        for (int i = _p1FirstPocket; i <= _p1LastPocket ; i++) {
            _gameBoard[i] = 4;
        }
        _p1Mancala = 0;

        for (int i = _p2FirstPocket; i <= _p2LastPocket ; i++) {
            _gameBoard[i] = 4;
        }
        _p2Mancala = 0;
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void printGameState() {
        System.out.printf("P2: [   [");
        for (int i = _p2LastPocket; i >= _p2FirstPocket ; i--) {
            if (i != _p2FirstPocket) {
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
        System.out.println("    [ " + _p2Mancala + "                          " + _p1Mancala + " ]");
        System.out.printf("P1: [   [");
        for (int i = _p1FirstPocket; i <= _p1LastPocket ; i++) {
            if (i != _p1LastPocket) {
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
        for (int i = _p2LastPocket; i >= _p2FirstPocket ; i--) {
            if (i != _p2FirstPocket) {
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
        System.out.println("    [ " + _p2Mancala + "                          " + _p1Mancala + " ]");
        System.out.printf("P1: [   [");
        for (int i = _p1FirstPocket; i <= _p1LastPocket ; i++) {
            if (i != _p1LastPocket) {
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