/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.Sudoku;
/**
 *
 * @author Otso
 */
public class SudokuTest {
    Sudoku ruutu;
    @Before
    public void setUp() {
        Sudoku ruutu = new Sudoku(0);
        ruutu.generate(); 
    }
    
@Test
public void sumTest() {
    assertEquals(405, ruutu.getSum());   
}
    
}
