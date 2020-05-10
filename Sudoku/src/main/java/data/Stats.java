/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Otso
 */
public class Stats {

    int wins;
    int played;
    int data;
    int games;

    public Stats() {

    }
    /**
     * Lukee tiedostosta voittojen määrän
     * @throws FileNotFoundException 
     */
    public void readWins() throws FileNotFoundException {
        try {
            File myObj = new File("wins.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                data = Integer.valueOf(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Lukee tiedostosta pelien määrän
     * @throws FileNotFoundException 
     */
    public void readGames() throws FileNotFoundException {
        try {
            File myObj = new File("games.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                games = Integer.valueOf(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Tallentaa tiedostoon voittojen ja pelien määrän
     * @throws IOException 
     */
    public void writeGames() throws IOException {
        PrintWriter prw = new PrintWriter("games.txt");
        prw.println(String.valueOf(played));
        prw.close();
        prw = new PrintWriter("wins.txt");
        prw.println(String.valueOf(wins));
        prw.close();
    }
    
    public void getStats() {
        wins = data;
        played = games;
    }
    /**
     * Tallentaa uuden voittojen määrän
     * @throws IOException 
     */
    public void gameWon() throws IOException {
        wins++;
        played++;
        writeGames();
    }
    /**
     * Tallentaa uuden pelien määrän
     * @throws IOException 
     */
    public void gameLost() throws IOException {
        played++;
        writeGames();
    }

    public int gameStats() {
        return played;
    }

    public int wonStats() {
        return wins;
    }

}
