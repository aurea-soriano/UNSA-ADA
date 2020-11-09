/*
Horspool Algorithm is used to search the pattern in the
given string using a shift table. Its another variation of
the Boyer-Moore Algorithm where it uses two shift tables - bad
shift table and good suffix table but in Horspool Algorithm we are
 using just one shift table to search the pattern in the given string.

 A shift table is the table that contains two things : -
Either length of the Pattern to be searched
Or distance of first occurrence of each character from the right most side of the pattern.


*/


import java.util.*;
public class Horspool  {

   public static int SIZE=500;
   public static int table[]=new int[SIZE];

public void shifttable(String pattern) {

 int i,j,m;
 char p[] = pattern.toCharArray();
 m=pattern.length();

 for (i=0;i<SIZE;i++)
    table[i]=m;
 for (j=0;j<m;j++)
    table[p[j]]=m-1-j;
}
public int horspool(String source,String pattern)
  {
      int i,k,pos,m;
      char s[] = source.toCharArray();
      char p[] = pattern.toCharArray();
      m=pattern.length();

      for(i=m-1;i<source.length();)
        {
           k=0;
            while((k<m) && (p[m-1-k] == s[i-k]))
              k++;
           if(k==m)
     {   pos=i-m+2;
               return pos;
     }
           else
               i+=table[s[i]];
        }
        return -1;
  }
     public static void main(String []args){
       String source= "ABAAABCD";
       String pattern = "ABC";
        int pos;
        Horspool  h = new Horspool ();

        h.shifttable(pattern);
        pos = h.horspool(source,pattern);

        if(pos == -1)
            System.out.println("PATTERN NOT FOUND");
        else
            System.out.println("PATTERN FOUND FROM POSITION: \t"+pos+"\n");
     }
}
