//topological sorting
import java.io.*;
import java.util.*;

// graph using adjacency list
class Graph {
    // number of vertices
    private int nV;

    // adj list (graph)
    private ArrayList<ArrayList<Integer> > adj;

    // Constructor
    Graph(int v){
        nV = v;
        adj = new ArrayList<ArrayList<Integer> >(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }

    // adding an edge in the graph
    void addEdge(int v, int w) {
      adj.get(v).add(w);
    }

    // recursive function
    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack)
    {
        // putting current node as visited (orange status)
        visited[v] = true;
        Integer i;

        // recursion in all its vertices
        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack  which stores result
        stack.push(new Integer(v));
    }

    // The function to do Topological Sort.
    void topologicalSort()
    {
        Stack<Integer> stack = new Stack<Integer>();

        // Put all the vertices as not visited
        boolean visited[] = new boolean[nV];
        for (int i = 0; i < nV; i++)
            visited[i] = false;

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices one by one
        for (int i = 0; i < nV; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        // Print contents of stack
        while (stack.empty() == false){
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String args[]){
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Topological Sorting");
        // Function Call
          g.topologicalSort();
    }
}
