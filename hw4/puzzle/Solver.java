package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    MinPQ<searchNode> pq;
    searchNode state;

    private static class searchNode implements Comparable<searchNode> {

        WorldState w;
        int move;
        searchNode previousNode;
        int priority;

        public searchNode(WorldState w, int move, searchNode previousNode) {
            this.w = w;
            this.move = move;
            this.previousNode = previousNode;
            priority = move + w.estimatedDistanceToGoal();
        }

        public int compareTo(searchNode o) {
            return Integer.compare(this.priority, o.priority);
        }
    }

    public Solver(WorldState w) {
        pq = new MinPQ<>();
        pq.insert(new searchNode(w, 0, null));

        while (!pq.isEmpty()) {
            state = pq.delMin();

            if (state.w.isGoal()) {
                break;
            } else {
                for (WorldState word : state.w.neighbors()) {
                    if (state.previousNode == null || !word.equals(state.previousNode.w)) {
                        pq.insert(new searchNode(word, state.move + 1, state));
                    }
                }
            }
        }
    }

    public int moves() {
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