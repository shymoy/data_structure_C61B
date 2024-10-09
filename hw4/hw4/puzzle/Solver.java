package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Deque;
import java.util.LinkedList;


public class Solver {
    private MinPQ<SearchNode> pq;
    private SearchNode state;

    private static class SearchNode implements Comparable<SearchNode> {

        WorldState w;
        int move;
        SearchNode previousNode;
        int priority;

        public SearchNode(WorldState w, int move, SearchNode previousNode) {
            this.w = w;
            this.move = move;
            this.previousNode = previousNode;
            priority = move + w.estimatedDistanceToGoal();
        }

        public int compareTo(SearchNode o) {
            return Integer.compare(this.priority, o.priority);
        }
    }

    public Solver(WorldState w) {
        if (w == null) {
            throw new IllegalArgumentException("WorldState cannot be null.");
        }
        
        pq = new MinPQ<>();
        pq.insert(new SearchNode(w, 0, null));

        while (!pq.isEmpty()) {
            state = pq.delMin();

            if (state.w.isGoal()) {
                break;
            } else {
                for (WorldState word : state.w.neighbors()) {
                    if (state.previousNode == null || !word.equals(state.previousNode.w)) {
                        pq.insert(new SearchNode(word, state.move + 1, state));
                    }
                }
            }
        }
    }

    public int moves() {
        if (state == null) {
            throw new IllegalStateException("Solver has not found a solution.");
        }
        return state.move;
    }

    public Iterable<WorldState> solution() {
        Deque<WorldState> queue = new LinkedList<>();
        while (state != null) {
            queue.push(state.w);
            state = state.previousNode;
        }
        return queue;
    }
}
