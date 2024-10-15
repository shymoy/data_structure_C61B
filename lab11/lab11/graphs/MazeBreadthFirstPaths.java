package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* inherit public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean isFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            //return first one
            int x = queue.poll();
            marked[x] = true;
            //judge is found?
            if (x == t) {
                isFound = true;
                return;
            }
            //add adjacent to the queue
            for (int i : maze.adj(x)) {
                if (!marked[i]) {
                    queue.add(i);
                    distTo[i] = distTo[x] + 1;
                    edgeTo[i] = x;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

