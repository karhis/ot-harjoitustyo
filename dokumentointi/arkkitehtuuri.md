# Arkkitehtuuri
## Rakenne
Sovelluksen pakkausrakenne on kaksitasoinen.  
![Diagrammi](https://github.com/karhis/ot-harjoitustyo/blob/master/dokumentointi/package.PNG)  
sudoku.gui sisältää toteutetun käyttöliittymän, sudoku.data tallentaa pelattujen ja voittetujen pelien määrän ja sudoku.Sudoku on vastuussa sudokujen luomisesta.  

## Käyttöliittymä
Käyttöliittymässä on vain kolme erillistä näkymää. 
Ensimmäinen näkymä on yksinkertainen Start-nappula, jota klikkaamalla siirrytään itse sovelluksen päänäkymään.  
Sovelluksen päänäkymä on GridPane johon on sijoitettu 9x9 Buttonia, jotka toimivat kätevästi sudokun soluina ja samalla niitä kontrolloivina painikkeina.  
Kolmatta näkymää käytetään kun sudoku on ratkaistu valmiiksi. Tästä näkymästä on mahdollista palata takaisin ensimmäiseen Start näkymään.

Käyttöliittymä on eristetty muusta sovelluslogiikasta ja kutsuu muiden luokkien metodeja tarvittaessa.

## Tietojen tallentaminen  
Pakkauksen sudoku.data luokka Stats.java huolehtii tietojen tallentamisesta kahteen eri tiedostoon.  
Tiedostot ovat kaksi yksinkertaista .txt tiedostoa, "games" ja "wins".  
Tekstitiedostot eivät sisällä muutakuin yhden numeron, joka merkkaa pelattujen ja ratkaistujen sudokujen määrää. 
Stats.java lukee kyseiset tiedostot ja muuttaa ne Integereiksi jonka jälkeen niitä on helppo inkrementoida.


