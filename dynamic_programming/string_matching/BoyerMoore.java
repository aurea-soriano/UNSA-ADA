/*Input:  txt[] = "THIS IS A TEST TEXT"
        pat[] = "TEST"
Output: Pattern found at index 10

Input:  txt[] =  "AABAACAADAABAABA"
        pat[] =  "AABA"
Output: Pattern found at index 0
        Pattern found at index 9
        Pattern found at index 12

The Bad Character Heuristic may take O(mn) time in worst case.
 The worst case occurs when all characters of the text and pattern are same.
 For example, txt[] = “AAAAAAAAAAAAAAAAAA” and pat[] = “AAAAA”.
 Dynamic programming is when you use past knowledge to make solving a future problem easier.

which is not the case with Boyer-Moore string search algorithm.
The key features of the algorithm are to match on the tail of the pattern
rather than the head, and to skip along the text in jumps of multiple
characters rather than searching every single character in the text.
        */

        /* Java Program for Bad Character Heuristic of Boyer
        Moore String Matching Algorithm */


        class BoyerMoore{

             static int NO_OF_CHARS = 256;

            //A utility function to get maximum of two integers
             static int max (int a, int b) { return (a > b)? a: b; }

             //The preprocessing function for Boyer Moore's
             //bad character heuristic
             static void badCharHeuristic( char []str, int size,int badchar[])
             {
              int i;

              // Initialize all occurrences as -1
              for (i = 0; i < NO_OF_CHARS; i++)
                   badchar[i] = -1;

              // Fill the actual value of last occurrence
              // of a character
              for (i = 0; i < size; i++)
                   badchar[(int) str[i]] = i;
             }

             /* A pattern searching function that uses Bad
             Character Heuristic of Boyer Moore Algorithm */
             static void search( char txt[],  char pat[])
             {
              int m = pat.length;
              int n = txt.length;

              int badchar[] = new int[NO_OF_CHARS];

              /* Fill the bad character array by calling
                 the preprocessing function badCharHeuristic()
                 for given pattern */
              badCharHeuristic(pat, m, badchar);

              int s = 0;  // s is shift of the pattern with
                          // respect to text
              while(s <= (n - m))
              {
                  int j = m-1;

                  /* Keep reducing index j of pattern while
                     characters of pattern and text are
                     matching at this shift s */
                  while(j >= 0 && pat[j] == txt[s+j])
                      j--;

                  /* If the pattern is present at current
                     shift, then index j will become -1 after
                     the above loop */
                  if (j < 0)
                  {
                      System.out.println("Patterns occur at shift = " + s);

                      /* Shift the pattern so that the next
                         character in text aligns with the last
                         occurrence of it in pattern.
                         The condition s+m < n is necessary for
                         the case when pattern occurs at the end
                         of text */
                      s += (s+m < n)? m-badchar[txt[s+m]] : 1;

                  }

                  else
                      /* Shift the pattern so that the bad character
                         in text aligns with the last occurrence of
                         it in pattern. The max function is used to
                         make sure that we get a positive shift.
                         We may get a negative shift if the last
                         occurrence  of bad character in pattern
                         is on the right side of the current
                         character. */
                      s += max(1, j - badchar[txt[s+j]]);
              }
             }

             /* Driver program to test above function */
            public static void main(String []args) {

                 char txt[] = "ABAAABCD".toCharArray();
                 char pat[] = "ABC".toCharArray();
                 search(txt, pat);
            }
        }
