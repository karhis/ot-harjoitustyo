# Testausdokumentti
Sovellukselle on rakennettu muutama yksinkertainen automatisoitu testi, testikattavuus on yli 80%  
![testit](https://github.com/karhis/ot-harjoitustyo/blob/master/dokumentointi/testit.PNG)  
Ohjelman rakenteen vuoksi automaattinen testaus on osoittautunut vaikeaksi. 
Sudokujen luomisesta vastuulla oleva Sudoku.sudoku-luokkaa on testattu manuaalisesti useasti, eikä osa sen metodeista toimisi jolleivat sudokut olisi kelpoja ja täten ratkaistavia.  

## Järjestelmätestaus

Sovellus on testattu manuaalisesti Windows- ja Linux-ympäristöissä eikä testauksessa ole huomattu virheitä. 

Asennukseen ei tarvita erillisiä toimia, sovellus toimii komentoriviltä suorittaessa. Sovellus myös luo tiedostot "games.txt" ja "wins.txt" automaattisesti.
