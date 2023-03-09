# SimulatedAnnealing

Tayyeb Rafique

Combinatorial Optimisation Coursework using the Simulated Annealing algorithm to optimise the Kemeny Score of a tournament ranking.

A tournament ranking is an ordered list of competitors from best to worst, given a set of tournment results.
There are multiple ways to determine what makes a good ranking. One way is by measuring it's Kemeny Score.
The Kemeny Score of a ranking is a rating of how much the order of competitors disagrees with the tournament results.
i.e. If A is ahead of B in the ranking, but B beat A in the results, this is a disagreement, and the Kemeny Score would be increased by factor of how badly B beat A.
A lower Kemeny Score would indicate a more favourable ranking.

The program finds the Kemeny Score of a random ranking and it's nearest neighbour (see the report for more information on nearest neighbour).
It compares the new score to the previous score and moves to the new ranking if the score is better (downhill move).
The simulated annealing algorithm sometimes makes uphill moves, (moving to a worse score) to avoid local optima.

Usage -

java SAprogram "1994_Formula_One.wmg"
