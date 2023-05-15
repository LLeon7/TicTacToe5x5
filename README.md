# TicTacToe5x5

Five by five board game where you try to match your symbols five times in a row by either horizontal, vertical or diagonal. 
You take turns against an AI and whoever matches five in a row first is the winner.

The AI is coded using the minimax algorithm.
Each turn the AI assesses each move on the board and calculates the best next move using a heuristic evaluation function.
This function calculates the value of each line and returns a sum of those lines

There are 3 rows, 3 columns and 2 diagonal lines which totals to 8 lines to calculate

The AI will be playing as X, while the player plays as O

If X and O are on the same line, line value = 0
If there is X but no O, line value = 10 to the power of number of X's
if there is O but no X, line value = -10 to the power of number of O's

By summing the score of all 8 lines we get a value of the current game state.

To find the next best move for the AI we use the minimax algorithm to iterate through every possible next move and calculate their gate state value.

The game state with the highest value will be used as the next move.

![Screenshot of program run](/pictures/sample2.png)
