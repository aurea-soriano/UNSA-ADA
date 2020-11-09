/* A Naive recursive implementation
of 0-1 Knapsack problem */
class KnapsackBF {



    // A utility function that returns
    // maximum of two integers
    int max(int a, int b)
    {
      return (a > b) ? a : b;
    }

    // Returns the maximum value that
    // can be put in a knapsack of
    // capacity W
    int knapSackBF(int W, int wt[], int val[], int n)
    {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is
        // more than Knapsack capacity W,
        // then this item cannot be included
        // in the optimal solution
        if (wt[n - 1] > W)
            return knapSackBF(W, wt, val, n - 1);

        // Return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        else
            return max(val[n - 1]+ knapSackBF(W - wt[n - 1], wt, val, n - 1),
                       knapSackBF(W, wt, val, n - 1));
    }

    // Driver code
    public static void main(String args[])
    {

        KnapsackBF knapsack = new KnapsackBF();
        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 50;
        int n = val.length;
        System.out.println(knapsack.knapSackBF(W, wt, val, n));
/*
        Time Complexity: O(2n).
As there are redundant subproblems.
Auxiliary Space :O(1).
As no extra data structure has been used for storing values.
*/
    }
}
