public class Main {



    public static void main(String[] args) {
        System.out.println("Hello and welcome to Mancala!");
/*
        for(int i = 0; i < 10000000; i++) {
            Mancala.playMancala(150);
            if(Mancala._gameBoard[Mancala.P1MANCALA]==48 || Mancala._gameBoard[Mancala.P2MANCALA]==48) {
                System.out.println("We have found it in the " + i + "th game");
                break;
            }
        }

        System.out.println("Min = " + Mancala._minOfAllGames);
*/
        Mancala.playUserMancala(500);
        //Mancala.printGameMap();
        //Mancala.printGameState();
        //System.out.println("More moves reqd");

    }


}