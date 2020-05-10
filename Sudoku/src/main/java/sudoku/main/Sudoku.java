package sudoku.main;

import java.lang.*;
import java.util.Arrays;
/**
 * Luokka joka on vastuussa ratkaistavien sudokujen luomisesta.
 * 
 */
public class Sudoku {
    /* ... */
    int[] board[];
    int k;
    int sum;
    int[][] answer;

    public Sudoku(int k) {
        this.k = k;
        board = new int[9][9];
    }
    /**
     * Metodi joka luo uuden sudokun.
     */
    public void generate() {
        fillCross();
        fill(0, 3);
        saveAnswer();
        maskNumbers();
    }

    /**
     * Valmiin sudokun tallennus 'answer' arrayseen.
     */
    void saveAnswer() {
        answer = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }
    /**
     * Metodi täyttää ruudukon diagonaalisesti.
     */
    void fillCross() {
        for (int i = 0; i < 9; i = i + 3) {
            fillMatrix(i, i);
        }
    }
    /**
     * Metodi täyttää 3x3 ruudukon.
     * @param row   rivi
     * @param col   kolumni
     */
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
    /**
     * Metodi satunnaisen numeron luomiseen.
     * @param n     Numeron ylin katto
     * @return      Satunnainen numero
     */
    int randomNumber(int n) {
        return (int) Math.floor((Math.random() * n + 1));
    }
    /**
     * Metodi joka tarkistaa onko 3x3 ruudukossa annettu numero.
     * @param rowPos    Rivi
     * @param colPos    Kolumni
     * @param num       Tarkastettava numero
     * @return          boolean
     */
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
    /**
     * Metodi joka täyttää lopun sudokusta.
     * @param i     iteraattori
     * @param j     iteraattori
     * @return      boolean
     */
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
    /**
     *  Metodi joka tarkastaa voiko ruutuun laittaa numeron
     * @param i     Rivi
     * @param j     Kolumni
     * @param n     Tarkastettava numero
     * @return      boolean
     */
    boolean checkFree(int i, int j, int n) {
        return (checkMissingRow(i, n)
                && checkMissingCol(j, n)
                && freeNumber(i - i % 3, j - j % 3, n));
    }
    /**
     *  Metodi joka tarkastaa voiko riviin laittaa numeron
     * @param i     Rivi
     * @param n     Tarkastettava numero
     * @return      boolean
     */
    boolean checkMissingRow(int i, int n) {
        for (int j = 0; j < 9; j++) {
            if (board[i][j] == n) {
                return false;
            }
        }
        return true;
    }
    /**
     * Metodi joka tarkastaa voiko kolumniin laittaa numeron
     * @param j     Kolumni
     * @param n     Tarkastetteava numero
     * @return      boolean
     */
    boolean checkMissingCol(int j, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[i][j] == n) {
                return false;
            }
        }
        return true;
    }
    /**
     * Numeroiden piilotus
     */
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

    /**
     * Palauttaa halutun solun arvon
     * @param x     Rivi
     * @param y     Kolumni
     * @return 
     */
    public String returnCell(int x, int y) {
        if (board[x][y] == 0) {
            return "  ";
        }
        return String.valueOf(board[x][y]);
    }
    /**
     * Tallentaa uuden numeron vastaukseen
     * @param i     Rivi
     * @param j     Kolumni
     * @param z     Numero
     */
    public final void setNumber(int i, int j, int z) {
        board[i][j] = z;
    }
    /**
     * Tarkastaa onko sudoku oikein
     * @return      boolean
     */
    public boolean checkAnswer() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {   
                if (board[x][y] == answer[x][y])   {
              
                } else {
                    return false;  
                }     
            }
            
            
        }
        return true;
    }
    /**
     * Tyhjentää sudokuruudukon, jotta voidaan generoida uusi
     */
    public void resetGame() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = 0;
            }
        }        
    }
}

