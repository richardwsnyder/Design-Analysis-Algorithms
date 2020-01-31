//DO NOT CHANGE ANY EXISTING CODE IN THIS FILE
//DO NOT CHANGE THE NAMES OF ANY EXISTING FUNCTIONS
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

class Vertex {
	public int d; /* shortest distance from source */
	public Vertex pred; /* predecessor vertex */
	public int vertexNum; /* Vertex number used for indexing in to adjacency matrix */
	public int uniquePath; /* boolean representing if v has a unique shortest path from source */

	public Vertex(int num) {
		d = Integer.MAX_VALUE;
		pred = null;
		vertexNum = num;
		uniquePath = 1;
	}
}

class Graph {
	public Hashtable<Integer, Vertex> vertices; /* Set of vertices in the graph */
	public int source; /* source vertex number */
	public int[][] adjMatrix; /* adjacency matrix */
	int numVertices;

	public Graph(int n, int e, int mat[][], int s) {
		source = s;
		adjMatrix = new int[n][n];
		vertices = new Hashtable<>();
		numVertices = n;
		int i, j;
		for(i = 0; i < n; i++) 
		{
			vertices.put(i, new Vertex(i));
			for(j = 0; j < n; j++)
			{
				adjMatrix[i][j] = Integer.MAX_VALUE;
			}
		}

		Vertex sourceVertex = vertices.get(s-1);
		sourceVertex.d = 0;
		// initialize adjacency matrix
		for(i = 0; i < e; i++)
		{
			int vertA = mat[i][0];
			int vertB = mat[i][1];
			adjMatrix[vertA-1][vertB-1] = mat[i][2];
			adjMatrix[vertB-1][vertA-1] = mat[i][2];
		}
	}

	// debugging function to print the 
	// adjacency matrix of a graph
	public void printGraph() {
		int i, j;
		for(i = 0; i < numVertices; i++)
		{
			for(j = 0; j < numVertices; j++)
			{
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
}

public class Dijkstra {
	
	public static void relax(Vertex u, Vertex v, int w) {

		if(v.d > u.d + w) /* test if you need to update v.d */
		{
			v.d = u.d + w;
			v.pred = u;
		}

		// If you've found an edge that has the same weight
		// as the previously determined shortest path,
		// then the shortest path is not unique
		else if(v.d == u.d + w)
		{
			v.uniquePath = 0;	
		}
	}

	// extract the minimum value from an
	// array of vertices
	public static Vertex extractMin(ArrayList<Vertex> pq) {
		int i, min = Integer.MAX_VALUE, size = pq.size();
		Vertex minVertex = null;
		for(i = 0; i < size; i++)
		{
			Vertex v = pq.get(i);
			if(v.d < min)
			{
				min = v.d;
				minVertex = v;
			}
		}
		pq.remove(minVertex); /* remove the minimum vertex from queue */
		return minVertex;
	}

		// You need to write your own priority queue becuase the priority queue
		// provided by Java doesn't update when you update the values inside of it. 
	    public static int [][] Dijkstra_alg( int n, int e, int mat[][], int s)
	    {
	    	 //Write your code here to calculate shortest paths and usp values from source to all vertices
			 //This method will have four inputs (Please see testcase file)
			 //This method should return a two dimensional array as output (Please see testcase file) 
			
			 Graph g = new Graph(n, e, mat, s); /* initialize graph */
			ArrayList<Vertex> pq = new ArrayList<>(); /* priority queue */
			int i;

			// initialize priority queue
			for(i = 0; i < g.numVertices; i++)
			{
				pq.add(g.vertices.get(i));
			}

			while(pq.size() > 0)
			{
				Vertex u = extractMin(pq); /* extract min from priority queue */
				int rowInAdjMatrix = u.vertexNum;

				for(i = 0; i < g.numVertices; i++)
				{
					if(g.adjMatrix[rowInAdjMatrix][i] != Integer.MAX_VALUE) /* relax edge (u,v) for all v in u's adjacency list */
					{	
						relax(u, g.vertices.get(i), g.adjMatrix[rowInAdjMatrix][i]);
					}
				}
			}

			int[][] retMat = new int[g.numVertices][2];
			for(i = 0; i < g.numVertices; i++)
			{
				retMat[i][0] = g.vertices.get(i).d;
				retMat[i][1] = g.vertices.get(i).uniquePath;
			}

			return retMat;
	}

	    public static void main(String []args) {
	
      		Result result = JUnitCore.runClasses(DijkstraTest.class);
		if(result.wasSuccessful())
			System.out.println("All test cases passed");
	
		for (Failure failure : result.getFailures()) {
         		System.out.println(failure.toString());
      		}
	}
}
