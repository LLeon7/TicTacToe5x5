package TicTacToe5x5;//CE213 assignment Leon Lau 1605627

import java.util.ArrayList;
import java.util.List;

class AIplayer5x5 {

    Board5x5 b = new Board5x5();

    public AIplayer5x5(Board5x5 b) {
        this.b = b;
    }

    public Point bestMove;

        public int minimax (int node, int depth, int player) {
        /*
        Returns the maximum value which is the best possible move for the AI
        This function also finds the best point and sets it as the bestMove attribute under this
        AI player class

        For this function minimax is no longer recursive. It instead finds the maximum value using my heuristic
        evaluation function.
        */

            List<Point> avaiablePoints = b.getAvailablePoints();    //Remaining unexpanded nodes

            List<PointsAndScores> pointsAndScores = new ArrayList<>();  //List to contain the points and scores
            //at different states


            int bestScore = 0;

            if (b.isGameOver()) {            //Returns a postive integer if X won
                return valueOfGamePos();    //Returns a negative integer if O won
            }                               //Returns 0 if draw


            for (Point p : avaiablePoints) {

                if (player == 1) {  //AI player
                    b.placeAMove(p, 1);
                    int score = valueOfGamePos();
                    pointsAndScores.add(new PointsAndScores(score, p));

                    bestScore = Integer.MIN_VALUE;
                    Point bestPoint;
                    for (PointsAndScores ps : pointsAndScores) {
                        if (bestScore < ps.score) {     //Find the maximum score and the point with it
                            bestScore = ps.score;
                            bestPoint = ps.point;
                            bestMove = bestPoint;       //Sets the maximum score with its point as best move
                        }
                    }
                } else if (player == 2) { //User Player
                    b.placeAMove(p, 2);
                    int score = valueOfGamePos();
                    pointsAndScores.add(new PointsAndScores(score, p));

                    bestScore = Integer.MAX_VALUE;
                    Point bestPoint;
                    for (PointsAndScores ps : pointsAndScores) {
                        if (bestScore > ps.score) {     //Find the minimum value and the point with it
                            bestScore = ps.score;
                            bestPoint = ps.point;
                            bestMove = bestPoint;       //Sets the minimum score with its point as best move
                        }
                    }
                }

                b.placeAMove(p, 0); //Undo the current point
            }

            return bestScore;
        }


    public int valueOfGamePos() {   //Heuristic evaluation function
        /*
        This function calculates the value of each line and returns a sum of those lines
        There are 3 rows, 3 columns and 2 diagonal lines which totals to 8 lines to calculate

        If X and O are on the same line, line value = 0
        If there is X but no O, line value = 10 to the power of number of X
        if there is O but no X, line value = -10 to the power of number of O
         */

        int valueOfGamePos = 0;  //For each line calculate the total is added to valueOfGamePos

        Point statePoint00 = new Point(0, 0);           //I gave each point and state a reference
        Point statePoint01 = new Point(0, 1);           //to make coding shorter and easier
        Point statePoint02 = new Point(0, 2);
        Point statePoint03 = new Point(0, 3);
        Point statePoint04 = new Point(0, 4);
        Point statePoint10 = new Point(1, 0);
        Point statePoint11 = new Point(1, 1);
        Point statePoint12 = new Point(1, 2);
        Point statePoint13 = new Point(1, 3);
        Point statePoint14 = new Point(1, 4);
        Point statePoint20 = new Point(2, 0);
        Point statePoint21 = new Point(2, 1);
        Point statePoint22 = new Point(2, 2);
        Point statePoint23 = new Point(2, 3);
        Point statePoint24 = new Point(2, 4);
        Point statePoint30 = new Point(3, 0);
        Point statePoint31 = new Point(3, 1);
        Point statePoint32 = new Point(3, 2);
        Point statePoint33 = new Point(3, 3);
        Point statePoint34 = new Point(3, 4);
        Point statePoint40 = new Point(4, 0);
        Point statePoint41 = new Point(4, 1);
        Point statePoint42 = new Point(4, 2);
        Point statePoint43 = new Point(4, 3);
        Point statePoint44 = new Point(4, 4);


        int stateValue00 = b.getState(statePoint00);
        int stateValue01 = b.getState(statePoint01);
        int stateValue02 = b.getState(statePoint02);
        int stateValue03 = b.getState(statePoint03);
        int stateValue04 = b.getState(statePoint04);
        int stateValue10 = b.getState(statePoint10);
        int stateValue11 = b.getState(statePoint11);
        int stateValue12 = b.getState(statePoint12);
        int stateValue13 = b.getState(statePoint13);
        int stateValue14 = b.getState(statePoint14);
        int stateValue20 = b.getState(statePoint20);
        int stateValue21 = b.getState(statePoint21);
        int stateValue22 = b.getState(statePoint22);
        int stateValue23 = b.getState(statePoint23);
        int stateValue24 = b.getState(statePoint24);
        int stateValue30 = b.getState(statePoint30);
        int stateValue31 = b.getState(statePoint31);
        int stateValue32 = b.getState(statePoint32);
        int stateValue33 = b.getState(statePoint33);
        int stateValue34 = b.getState(statePoint34);
        int stateValue40 = b.getState(statePoint40);
        int stateValue41 = b.getState(statePoint41);
        int stateValue42 = b.getState(statePoint42);
        int stateValue43 = b.getState(statePoint43);
        int stateValue44 = b.getState(statePoint44);


        //----- Finding the value of the line diagonal from top left to bottom right

        List<Integer> diagonalFromTopLeft = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Point statePoint = new Point(i, i);
            int stateValue = b.getState(statePoint);
            diagonalFromTopLeft.add(stateValue);
        }

        int aiPowerLeft = 0;
        int userPowerLeft = 0;
        for (int i : diagonalFromTopLeft) {
            if (i == 1) {
                aiPowerLeft++;
            } else if (i == 2) {
                userPowerLeft++;
            }
        }

        if (aiPowerLeft > 0 && userPowerLeft == 0) {
            valueOfGamePos += Math.pow(10, aiPowerLeft);
        } else if (userPowerLeft > 0 && aiPowerLeft == 0) {
            valueOfGamePos -= Math.pow(10, userPowerLeft);
        }

        //----- Finding the value of the line diagonal from top right to bottom left

        List<Integer> diagonalFromTopRight = new ArrayList<>();

        diagonalFromTopRight.add(stateValue04);
        diagonalFromTopRight.add(stateValue13);
        diagonalFromTopRight.add(stateValue22);
        diagonalFromTopRight.add(stateValue31);
        diagonalFromTopRight.add(stateValue40);

        int aiPowerRight = 0;
        int userPowerRight = 0;
        for (int r : diagonalFromTopRight) {
            if (r == 1) {
                aiPowerRight++;
            } else if (r == 2) {
                userPowerRight++;
            }
        }

        if (aiPowerRight > 0 && userPowerRight == 0) {
            valueOfGamePos += Math.pow(10, aiPowerRight);
        } else if (userPowerRight > 0 && aiPowerRight == 0) {
            valueOfGamePos -= Math.pow(10, userPowerRight);
        }

        //----- Find the value of the first row
        List<Integer> row1 = new ArrayList<>();

        row1.add(stateValue00);
        row1.add(stateValue01);
        row1.add(stateValue02);
        row1.add(stateValue03);
        row1.add(stateValue04);

        int aiPowerR1 = 0;
        int userPowerR1 = 0;
        for (int r : row1) {
            if (r == 1) {
                aiPowerR1++;
            } else if (r == 2) {
                userPowerR1++;
            }
        }

        if (aiPowerR1 > 0 && userPowerR1 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerR1);
        } else if (userPowerR1 > 0 && aiPowerR1 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerR1);
        }

        //----- Find the value of the second row
        List<Integer> row2 = new ArrayList<>();

        row2.add(stateValue10);
        row2.add(stateValue11);
        row2.add(stateValue12);
        row2.add(stateValue13);
        row2.add(stateValue14);

        int aiPowerR2 = 0;
        int userPowerR2 = 0;
        for (int r : row2) {
            if (r == 1) {
                aiPowerR2++;
            } else if (r == 2) {
                userPowerR2++;
            }
        }

        if (aiPowerR2 > 0 && userPowerR2 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerR2);
        } else if (userPowerR2 > 0 && aiPowerR2 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerR2);
        }

        //----- Find the value of the third row
        List<Integer> row3 = new ArrayList<>();

        row3.add(stateValue20);
        row3.add(stateValue21);
        row3.add(stateValue22);
        row3.add(stateValue23);
        row3.add(stateValue24);

        int aiPowerR3 = 0;
        int userPowerR3 = 0;
        for (int r : row3) {
            if (r == 1) {
                aiPowerR3++;
            } else if (r == 2) {
                userPowerR3++;
            }
        }

        if (aiPowerR3 > 0 && userPowerR3 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerR3);
        } else if (userPowerR3 > 0 && aiPowerR3 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerR3);
        }

        //----- Find the value of the fourth row
        List<Integer> row4 = new ArrayList<>();

        row4.add(stateValue30);
        row4.add(stateValue31);
        row4.add(stateValue32);
        row4.add(stateValue33);
        row4.add(stateValue34);

        int aiPowerR4 = 0;
        int userPowerR4 = 0;
        for (int r : row4) {
            if (r == 1) {
                aiPowerR4++;
            } else if (r == 2) {
                userPowerR4++;
            }
        }

        if (aiPowerR4 > 0 && userPowerR4 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerR4);
        } else if (userPowerR4 > 0 && aiPowerR4 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerR4);
        }

        //----- Find the value of the fifth row
        List<Integer> row5 = new ArrayList<>();

        row5.add(stateValue40);
        row5.add(stateValue41);
        row5.add(stateValue42);
        row5.add(stateValue43);
        row5.add(stateValue44);

        int aiPowerR5 = 0;
        int userPowerR5 = 0;
        for (int r : row5) {
            if (r == 1) {
                aiPowerR5++;
            } else if (r == 2) {
                userPowerR5++;
            }
        }

        if (aiPowerR5 > 0 && userPowerR5 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerR5);
        } else if (userPowerR5 > 0 && aiPowerR5 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerR5);
        }

        //----- Find the value the first column
        List<Integer> c1 = new ArrayList<>();

        c1.add(stateValue00);
        c1.add(stateValue10);
        c1.add(stateValue20);
        c1.add(stateValue30);
        c1.add(stateValue40);

        int aiPowerC1 = 0;
        int userPowerC1 = 0;
        for (int r : c1) {
            if (r == 1) {
                aiPowerC1++;
            } else if (r == 2) {
                userPowerC1++;
            }
        }

        if (aiPowerC1 > 0 && userPowerC1 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerC1);
        } else if (userPowerC1 > 0 && aiPowerC1 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerC1);
        }


        //----- Find the value of the second column
        List<Integer> c2 = new ArrayList<>();

        c2.add(stateValue01);
        c2.add(stateValue11);
        c2.add(stateValue21);
        c2.add(stateValue31);
        c2.add(stateValue41);

        int aiPowerC2 = 0;
        int userPowerC2 = 0;
        for (int r : c2) {
            if (r == 1) {
                aiPowerC2++;
            } else if (r == 2) {
                userPowerC2++;
            }
        }

        if (aiPowerC2 > 0 && userPowerC2 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerC2);
        } else if (userPowerC2 > 0 && aiPowerC2 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerC2);
        }

        //----- Find the value of the third column
        List<Integer> c3 = new ArrayList<>();

        c3.add(stateValue02);
        c3.add(stateValue12);
        c3.add(stateValue22);
        c3.add(stateValue32);
        c3.add(stateValue42);

        int aiPowerC3 = 0;
        int userPowerC3 = 0;
        for (int r : c3) {
            if (r == 1) {
                aiPowerC3++;
            } else if (r == 2) {
                userPowerC3++;
            }
        }

        if (aiPowerC3 > 0 && userPowerC3 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerC3);
        } else if (userPowerC3 > 0 && aiPowerC3 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerC3);
        }

        //----- Find the value of the fourth column
        List<Integer> c4 = new ArrayList<>();

        c4.add(stateValue03);
        c4.add(stateValue13);
        c4.add(stateValue23);
        c4.add(stateValue33);
        c4.add(stateValue43);

        int aiPowerC4 = 0;
        int userPowerC4 = 0;
        for (int r : c4) {
            if (r == 1) {
                aiPowerC4++;
            } else if (r == 2) {
                userPowerC4++;
            }
        }

        if (aiPowerC4 > 0 && userPowerC4 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerC4);
        } else if (userPowerC4 > 0 && aiPowerC4 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerC4);
        }

        //----- Find the value of the fifth column
        List<Integer> c5 = new ArrayList<>();

        c5.add(stateValue04);
        c5.add(stateValue14);
        c5.add(stateValue24);
        c5.add(stateValue34);
        c5.add(stateValue44);

        int aiPowerC5 = 0;
        int userPowerC5 = 0;
        for (int r : c5) {
            if (r == 1) {
                aiPowerC5++;
            } else if (r == 2) {
                userPowerC5++;
            }
        }

        if (aiPowerC5 > 0 && userPowerC5 == 0) {
            valueOfGamePos += Math.pow(10, aiPowerC5);
        } else if (userPowerC5 > 0 && aiPowerC5 == 0) {
            valueOfGamePos -= Math.pow(10, userPowerC5);
        }

        return valueOfGamePos;
    }

}

