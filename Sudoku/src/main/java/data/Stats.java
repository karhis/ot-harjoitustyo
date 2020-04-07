/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Otso
 */
public class Stats {
    int wins;
    int played;

    public Stats() {
        wins = 0;
        played = 0;
    }
    
    public void gameWon() {
        wins++;
    }
    
    public void gameLost() {
        played++;
    }
    
    public int gameStats() {
        return played;
    }
    
    public int wonStats() {
        return wins;
    }
    
    public int ratio() {
        return (int) (wins * 1.0 / played * 100);
    }
}
