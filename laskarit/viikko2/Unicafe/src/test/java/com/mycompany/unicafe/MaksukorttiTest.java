package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        kassa = new Kassapaate();
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
    
    @Test
    public void rahaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myydytMaukkaat() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void myydytEdullsiet() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahaKasvaaEdullinen() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanRahaKasvaaMaukas() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void käteisVaihtorahaEdullinen() {
        assertEquals(60, kassa.syoEdullisesti(300));
    }
    
    @Test 
    public void käteisVaihtorahaMaukas() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void myydytKasvaaEdullinen() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());   
    }
    
    @Test
    public void myydytKasvaaMaukas() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
     
    @Test
    public void kassanRahaEiKasvaEdullinen() {
        kassa.syoEdullisesti(20);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void kassanRahaEiKasvaMaukas() {
        kassa.syoMaukkaasti(20);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void pieniRahaVaihtorahaEdullinen() {
        assertEquals(20, kassa.syoEdullisesti(20));
    }
    @Test
    public void pieniRahaVaihtorahaMaukas() {
        assertEquals(20, kassa.syoMaukkaasti(20));
    }
    
    @Test
    public void myydytEiKasvaEdullinen() {
        kassa.syoEdullisesti(20);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydyEiKasvaMaukas() {
        kassa.syoMaukkaasti(20);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaOstoToimiiEdullinen() {
        kortti.lataaRahaa(240);
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    @Test
    public void kortillaOstoToimiiMaukas() {
        kortti.lataaRahaa(400);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    @Test
    public void korttilaOstoVeloittaaEdullinen() {
        kortti.lataaRahaa(240);
        kassa.syoEdullisesti(kortti);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void kortillaOstoVeloittaaMaukas() {
        kortti.lataaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void kortillaOstoNostaaMyytyjäEdullinen() {
        kortti.lataaRahaa(240);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortillaOstoNostaaMyytyjäMaukas() {
        kortti.lataaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaOstoEiToimiEdullinen() {
      assertFalse(kassa.syoEdullisesti(kortti));  
    }
    @Test
    public void kortillaOstoEiToimiMaukas() {
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    @Test
    public void kortillaOstoEiVeloitaEdullinen() {
     kassa.syoEdullisesti(kortti);
     assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void kortillaOstoEiVeloitaMaukas() {
     kassa.syoMaukkaasti(kortti);   
     assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test 
    public void korttilaOstoEiNostaMyytyjäEdullinen() {
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortillaOstoEiNostaMyytyjäMaukas() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaOstoEiVaikutaKassaanEdullinen() {
        kortti.lataaRahaa(240);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void kortillaOstoEiVaikutaKassaanMaukas() {
        kortti.lataaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void kortillaLatausMuuttaaSaldoa() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    @Test
    public void kortillaLatausMuuttaaKassaa() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
    }
    @Test
    public void kortillaNegatiivinenLatausEiMuuta() {
       kassa.lataaRahaaKortille(kortti, -10);
    }
    }
