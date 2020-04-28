package sudoku.main;

import java.lang.*;
import java.util.Arrays;

public class Sudoku {

    int[] board[];
    int k;
    int sum;
    int[][] answer;

    public Sudoku(int k) {
        this.k = k;
        board = new int[9][9];
    }

    public void generate() {
        fillCross();
        fill(0, 3);
        saveAnswer();
        maskNumbers();
    }

    public int getSum() {
        sum = 0;
        for (int r = 0; r < answer.length; r++) {
            for (int c = 0; c < answer[r].length; c++) {
                sum += answer[r][c];
            }
        }
        return sum;
    }

    void saveAnswer() {
        answer = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }

    void fillCross() {
        for (int i = 0; i < 9; i = i + 3) {
            fillMatrix(i, i);
        }
    }

    void fillMatrix(int row, int col) {
        int n;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    n = randomNumber(9);
                } while (!freeNumber(row, col, n));

                board[row + i][col + j] = n;
            }
        }
    }

    int randomNumber(int n) {
        return (int) Math.floor((Math.random() * n + 1));
    }

    boolean freeNumber(int rowPos, int colPos, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowPos + i][colPos + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean fill(int i, int j) {
        if (j >= 9 && i < 9 - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= 9 && j >= 9) {
            return true;
        }

        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < 6) {
            if (j == (int) (i / 3) * 3) {
                j = j + 3;
            }
        } else {
            if (j == 6) {
                i = i + 1;
                j = 0;
                if (i >= 9) {
                    return true;
                }
            }
        }
        for (int n = 1; n <= 9; n++) {
            if (checkFree(i, j, n)) {
                board[i][j] = n;
                if (fill(i, j + 1)) {
                    return true;
                }

                board[i][j] = 0;
            }
        }
        return false;
    }

    boolean checkFree(int i, int j, int n) {
        return (checkMissingRow(i, n)
                && checkMissingCol(j, n)
                && freeNumber(i - i % 3, j - j % 3, n));
    }

    boolean checkMissingRow(int i, int n) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == n) {
                return false;
            }
        }
        return true;
    }

    boolean checkMissingCol(int j, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[i][j] == n) {
                return false;
            }
        }
        return true;
    }

    public void maskNumbers() {
        int count = k;
        while (count != 0) {
            int position = randomNumber(9 * 9) - 1;

            int i = (position / 9);
            int j = position % 9;

            if (board[i][j] != 0) {
                count--;
                board[i][j] = 0;
            }
        }
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printAnswer() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String returnCell(int x, int y) {
        if (board[x][y] == 0) {
            return "  ";
        }
        return String.valueOf(board[x][y]);
    }

    public void setNumber(int i, int j, int z) {
        board[i][j] = z;
    }

    public boolean checkAnswer() {
        return Arrays.equals(board, answer);
    }

}