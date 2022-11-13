import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private Board initialBoard;
    private int order;
    private boolean solvable;
    private int totalMove;
    private Stack<Board> solveStack = new Stack<Board>();


    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves = -1;
        private SearchNode previousSearchNode;
        private int priority;

        public SearchNode(Board board, SearchNode previousSearchNode) {
            this.board = board;
            this.previousSearchNode = previousSearchNode;

            if (previousSearchNode == null)
                this.moves = 0;
            else
                this.moves = previousSearchNode.moves + 1;

            this.priority = this.board.manhattan() + this.moves;
        }

        public int compareTo(SearchNode that) {
            // return this.board.manhattan() + this.moves - that.board.manhattan() - that.moves;
            return this.priority - that.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();

        initialBoard = initial;
        order = initialBoard.dimension();

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinpq = new MinPQ<SearchNode>();

        pq.insert(new SearchNode(initialBoard, null));
        twinpq.insert(new SearchNode(initialBoard.twin(), null));

        SearchNode minNode, minNodeTwin;

        while (true) {
            minNode = pq.delMin();

            if (minNode.board.isGoal()) {
                solvable = true;
                break;
            }

            for (Board neighbor : minNode.board.neighbors()) {
                if (minNode.previousSearchNode == null ||
                        !neighbor.equals(minNode.previousSearchNode.board)
                )
                    pq.insert(new SearchNode(neighbor, minNode));
            }


            minNodeTwin = twinpq.delMin();

            if (minNodeTwin.board.isGoal()) {
                solvable = false;
                break;
            }

            for (Board neighbor : minNodeTwin.board.neighbors()) {
                if (minNodeTwin.previousSearchNode == null ||
                        !neighbor.equals(minNodeTwin.previousSearchNode.board)
                )
                    twinpq.insert(new SearchNode(neighbor, minNodeTwin));
            }
        }

        if (solvable) {
            totalMove = minNode.moves;

            while (minNode != null) {
                solveStack.push(minNode.board);
                minNode = minNode.previousSearchNode;
            }
        }
        else {
            totalMove = -1;
            solveStack = null;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.totalMove;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.solveStack;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}