/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import data.Stats;
import sudoku.main.Sudoku;
/**
 *
 * @author Otso
 */
public class SudokuTest {
    Sudoku ruutu;
    Stats player;
     
    @Test
    public void percentageWorks() {
        Stats player = new Stats();  
        player.gameLost();
        assertEquals(0, player.ratio());
    }
    
    @Test
    public void percentageWorks2() {
     Stats player = new Stats();  
     player.gameWon();
     assertEquals(100, player.ratio());
    }
    
    @Test
    public void percentageWorks3() {
        Stats player = new Stats();  
        player.gameWon();
        player.gameLost();
        assertEquals(50, player.ratio());
    }
    
    @Test
    public void addWinWorks() {
        Stats player = new Stats();  
        player.gameWon();
        assertEquals(1, player.wonStats());
    }
    
    @Test
    public void addLossWorks() {
        Stats player = new Stats();  
        player.gameLost();
        assertEquals(1, player.gameStats());
        
    }
    
    
    
        
    
}
