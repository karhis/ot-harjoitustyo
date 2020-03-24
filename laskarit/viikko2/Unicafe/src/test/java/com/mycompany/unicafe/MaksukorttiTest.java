package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldonTarkistusAlussa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenToimii() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test 
    public void rahanOttaminenToimiiEka() {
        kortti.otaRahaa(200);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiToka() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void rahanOttaminentoimiiKolmas() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void rahanOttaminenToimiiNeljäs() {
        assertFalse(kortti.otaRahaa(200));
    }
    
    @Test
    public void saldoToimii() {
        assertEquals(10, kortti.saldo());
    }
        
}
