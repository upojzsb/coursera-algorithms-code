import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {

    private int[][] board;
    private final int order;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        order = tiles.length;

        board = new int[order][order];

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Integer.toString(order) + "\n");

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                sb.append(board[i][j] + " ");
            }
            if (i < order - 1)
                sb.append("\n");
        }

        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return order;
    }

    // number of tiles out of place
    public int hamming() {
        int dist = 0;

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                int goalValue = i * order + j + 1;
                if (goalValue == order * order)
                    goalValue = 0;

                if (board[i][j] != goalValue && goalValue != 0) {
                    dist++;
                }
            }
        }

        return dist;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int dist = 0;

        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                if (board[i][j] == 0)
                    continue;

                int destI = (board[i][j] - 1) / order;
                int destJ = (board[i][j] - 1) % order;

                dist += Math.abs(i - destI) + Math.abs(j - destJ);
            }
        }

        return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < order; i++)
            for (int j = 0; j < order; j++) {
                int goalValue = i * order + j + 1;
                if (goalValue == order * order)
                    goalValue = 0;

                if (board[i][j] != goalValue)
                    return false;
            }

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;

        if (this.dimension() != that.dimension())
            return false;

        for (int i = 0; i < order; i++)
            for (int j = 0; j < order; j++)
                if (this.board[i][j] != that.board[i][j])
                    return false;

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();

        int zeroX = -1;
        int zeroY = -1;

        for (int i = 0; i < order; i++)
            for (int j = 0; j < order; j++)
                if (board[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }

        Board cases;
        // case1: top
        if (zeroX > 0) {
            cases = new Board(board);

            cases.board[zeroX][zeroY] = cases.board[zeroX - 1][zeroY];
            cases.board[zeroX - 1][zeroY] = 0;

            neighbors.enqueue(cases);
        }


        // case 2: down
        if (zeroX < order - 1) {
            cases = new Board(board);

            cases.board[zeroX][zeroY] = cases.board[zeroX + 1][zeroY];
            cases.board[zeroX + 1][zeroY] = 0;

            neighbors.enqueue(cases);
        }

        // case 3: left
        if (zeroY > 0) {
            cases = new Board(board);

            cases.board[zeroX][zeroY] = cases.board[zeroX][zeroY - 1];
            cases.board[zeroX][zeroY - 1] = 0;

            neighbors.enqueue(cases);
        }

        // case 4: right
        if (zeroY < order - 1) {
            cases = new Board(board);

            cases.board[zeroX][zeroY] = cases.board[zeroX][zeroY + 1];
            cases.board[zeroX][zeroY + 1] = 0;

            neighbors.enqueue(cases);
        }

        return neighbors;
    }


    // a board that is obtained by exchanging any pair of tiles
    // IMPORTANT: IF A BOARD IS UNSOLVABLE, ITS TWIN WILL BE SOLVABLE
    public Board twin() {
        Board twin = new Board(this.board);

        int x1 = -1;
        int x2 = -1;
        int y1 = -1;
        int y2 = -1;

        if (board[0][0] != 0) {
            x1 = 0;
            y1 = 0;
        }
        if (board[order - 1][order - 1] != 0) {
            if (x1 == -1) {
                x1 = order - 1;
                y1 = order - 1;
            }
            else {
                x2 = order - 1;
                y2 = order - 1;
            }
        }
        if (board[0][order - 1] != 0) {
            if (x1 == -1) {
                x1 = 0;
                y1 = order - 1;
            }
            else {
                x2 = 0;
                y2 = order - 1;
            }
        }

        int tmp;

        tmp = twin.board[x1][y1];
        twin.board[x1][y1] = twin.board[x2][y2];
        twin.board[x2][y2] = tmp;

        return twin;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int n = 3;
        int[][] bd = new int[n][n];

        bd[0][0] = 1;
        bd[0][1] = 8;
        bd[0][2] = 3;

        bd[1][0] = 0;
        bd[1][1] = 4;
        bd[1][2] = 2;

        bd[2][0] = 7;
        bd[2][1] = 6;
        bd[2][2] = 5;

        Board board = new Board(bd);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());

        for (Board b : board.neighbors()) {
            StdOut.println(b);
            StdOut.println(b.twin());
        }

        int[][] db = new int[2][2];
        db[0][0] = 0;
        db[0][1] = 3;
        db[1][0] = 1;
        db[1][1] = 2;

        board = new Board(db);
        StdOut.println(board);
        StdOut.println(board.manhattan());
    }

}