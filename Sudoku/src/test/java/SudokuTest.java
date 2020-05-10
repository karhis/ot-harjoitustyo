/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import data.Stats;
import java.io.FileNotFoundException;
import java.io.IOException;
import sudoku.main.Sudoku;
/**
 *
 * @author Otso
 */
public class SudokuTest {
    Sudoku ruutu;
    Stats player;
         
    @Test
    public void addWinWorks() throws IOException {
        Stats player = new Stats();
        player.readWins();
        player.getStats();
        player.gameWon();
        assertEquals(1, player.wonStats());
    }
 
    @Test
    public void addGameWorks() throws IOException {
        Stats player = new Stats();
        player.readGames();
        player.getStats();
        player.gameLost();
        assertEquals(2, player.gameStats());      
    }    
    
    @Test
    public void numberSetAndGetWorks() {
        Sudoku sudoku = new Sudoku(0);
        sudoku.generate();
        sudoku.setNumber(0, 0, 5);
        assertEquals("5", sudoku.returnCell(0, 0));
    }
    
    @Test
    public void resetWorks() {
        Sudoku sudoku = new Sudoku(0);
        sudoku.generate();
        sudoku.resetGame();
        assertEquals("  ", sudoku.returnCell(0, 0));
    }
    
    
    
        
    
}
