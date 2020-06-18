// Problem Link : https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3363/

//Surrounded Regions


/* Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically. */

#include <iostream>
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    
    bool isSafe(int x, int y, int m, int n) {
        return !(x <0 || x >= m || y < 0 || y >= n);
    }
    void floodFill(vector<vector<char>>& board, vector<vector<bool>>& vis, char u, char v, int x, int y, vector<vector<int>>& dir) {
        
        vis[x][y] = true;
        board[x][y] = v;
        for (int i = 0; i < 4; ++i) {
            int xt = x + dir[0][i];
            int yt = y + dir[1][i];
            if (isSafe(xt, yt, board.size(), board[0].size()) && !vis[xt][yt] && (board[xt][yt] == u)) {
                floodFill(board, vis, u, v, xt, yt, dir);
            }
        }

    }
    void solve(vector<vector<char>>& board) {
        vector<vector<bool>> vis;
        for (int i = 0; i < board.size(); ++i) {
            vector<bool> temp(board[i].size(), false);
            vis.push_back(temp);
        }

        vector<vector<int>> dir{{-1, 1, 0, 0},{0, 0, -1, 1}};
        
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board[i].size(); ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = '-';
                }
            }
        }

        for (int i = 0; i < board.size(); ++i) {
            if (!vis[i][0] && board[i][0] == '-') {
                floodFill(board, vis, '-', 'O', i , 0, dir);
            }
            if (!vis[i][board[i].size() - 1] && board[i][board[i].size() - 1] == '-') {
                floodFill(board, vis, '-', 'O', i , board[i].size() - 1, dir);
            }
        }

        for (int i = 0; i < board[0].size(); ++i) {
            if (!vis[0][i] && board[0][i] == '-') {
                floodFill(board, vis, '-', 'O', 0 , i, dir);
            }
            if (!vis[board.size() - 1][i] && board[board.size() - 1][i] == '-') {
                floodFill(board, vis, '-', 'O', board.size() - 1 , i, dir);
            }
        }

        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board[i].size(); ++j) {
                if (board[i][j] == '-') {
                    board[i][j] = 'X';
                }
            }
        }


    }
};

/* 
4 4
X X X X
X O O X
X X O X
X O X X



 */
int main() {

    int m, n;
    cin >> m >> n;
    vector<vector<char>> board;
    for (int i = 0; i < m; ++i) {
        vector<char> temp;
        for (int j = 0; j < n; ++j) {
            char x;
            cin >> x;
            temp.push_back(x);
        } 
        board.push_back(temp);
    }
    for (int i = 0; i < board.size(); ++i) {
        for (int j = 0; j < board[i].size(); ++j) {
            cout << board[i][j] << " ";
        }
        cout << '\n';
    }
    cout << "-------------------------------------------------\n";
    Solution s;
    s.solve(board);
    for (int i = 0; i < board.size(); ++i) {
        for (int j = 0; j < board[i].size(); ++j) {
            cout << board[i][j] << " ";
        }
        cout << '\n';
    }
    return 0;
}