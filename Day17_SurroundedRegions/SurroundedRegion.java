package Day17_SurroundedRegions;

import java.util.Scanner;

class Solution {
    private boolean isSafe(int x, int y, int m, int n) {
        return !(x <0 || x >= m || y < 0 || y >= n);
    }
    void floodFill(char[][] board, boolean[][] vis, char u, char v, int x, int y, int[][] dir) {
        
        vis[x][y] = true;
        board[x][y] = v;
        for (int i = 0; i < 4; ++i) {
            int xt = x + dir[0][i];
            int yt = y + dir[1][i];
            if (isSafe(xt, yt, board.length, board[0].length) && !vis[xt][yt] && (board[xt][yt] == u)) {
                floodFill(board, vis, u, v, xt, yt, dir);
            }
        }

    }
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;

        boolean[][] vis = new boolean[m][n];
        int[][] dir = {{-1, 1, 0, 0},{0, 0, -1, 1}};

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = '-';
                }
            }
        }

        

        for (int i = 0; i < board.length; ++i) {
            if (!vis[i][0] && board[i][0] == '-') {
                floodFill(board, vis, '-', 'O', i , 0, dir);
            }
            if (!vis[i][board[i].length - 1] && board[i][board[i].length - 1] == '-') {
                floodFill(board, vis, '-', 'O', i , board[i].length - 1, dir);
            }
        }

        for (int i = 0; i < board[0].length; ++i) {
            if (!vis[0][i] && board[0][i] == '-') {
                floodFill(board, vis, '-', 'O', 0 , i, dir);
            }
            if (!vis[board.length - 1][i] && board[board.length - 1][i] == '-') {
                floodFill(board, vis, '-', 'O', board.length - 1 , i, dir);
            }
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == '-') {
                    board[i][j] = 'X';
                }
            }
        }

    }
}
public class SurroundedRegion {
    public static void main(String[] args) {
        
    }
}
