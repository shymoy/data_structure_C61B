package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int[] nodeTo;
    private boolean isFound = false;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0, -1);
    }

    // Helper methods go here
    private void dfs(int currentNode, int privousNode) {
        marked[currentNode] = true;
        announce();

        for (int w : maze.adj(currentNode)) {
            if (!marked[w]) {
                nodeTo[w] = currentNode;
                dfs(w, currentNode);
            } else if(w != privousNode) {
                edgeTo[w] = currentNode;
                announce();
                for (int i = currentNode; i != w; i = nodeTo[i]) {
                    edgeTo[i] = nodeTo[i];
                    announce();
                }
                isFound = true;
            }
            if (isFound) {
                return;
            }
        }
    }
}

