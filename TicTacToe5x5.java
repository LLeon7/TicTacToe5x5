package TicTacToe5x5;//CE213 assignment Leon Lau 1605627

import java.util.*;

public class TicTacToe5x5 {
    public static void main(String[] args) {

        Board5x5 b = new Board5x5();
        Point p = new Point(0, 0);
        Random rand = new Random();
        AIplayer5x5 AI = new AIplayer5x5(b);  //New AI player is created here
        int node = AI.valueOfGamePos();

        b.displayBoard();

        System.out.println("Who makes first move? (1)Computer (2)User: ");
        int choice = b.scan.nextInt();

        if(choice == 1){        //AI making the first move
            p.x = rand.nextInt(5);
            p.y = rand.nextInt(5);
            b.placeAMove(p, 1);
            b.displayBoard();
        }

        while (!b.isGameOver()) {       //User making the move

            System.out.println("Value of current game node to AI = "+AI.valueOfGamePos());
            /*
            Prints the current value of the game node
            The node value should always be greater than 0 to show AI is in favour
            When the node value == 0 then the game is a stalemate (draw)
            */
            if(AI.valueOfGamePos() == 0){
                System.out.println("The game is at a stalemate!");
            }
            System.out.println("");


            System.out.println("Your move: line (1, 2, 3, 4 or 5) colunm (1, 2, 3, 4 or 5)");
            Point userMove = new Point(b.scan.nextInt()-1, b.scan.nextInt()-1);

            while (b.getState(userMove)!=0) {
                System.out.println("Invalid move. Make your move again: ");
                userMove.x=b.scan.nextInt()-1;
                userMove.y=b.scan.nextInt()-1;
            }
            b.placeAMove(userMove, 2);
            b.displayBoard();

            if (b.isGameOver()) {
                break;
            }

            //In the following do loop, Computer makes random moves. Your assignment is to replace it by implementing
            //an AIplayer class that chooses the best moves based on minimax search.

            do {

                AI.minimax(AI.valueOfGamePos(),2,1);
                p = AI.bestMove;            //Sets the point where the maximum value is found
                                            //This should be the best possible move to minimise possible loss
            }

            while (b.getState(p)!=0);
            b.placeAMove(p, 1);
            b.displayBoard();


        }

        if (b.hasXWon()) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasOWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }

}

