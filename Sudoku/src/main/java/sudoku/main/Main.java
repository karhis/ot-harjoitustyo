/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.main;

import sudoku.Sudoku;
import sudoku.SudokuUi;
/**
 *
 * @author Otso
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sudoku sudoku = new Sudoku(38); 
        sudoku.generate();
        System.out.println(sudoku.getSum());
        sudoku.printAnswer();
        sudoku.print(); 
        SudokuUi.main(args);
    }
    
}
