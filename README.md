# Sudoku
**Itse sudokuluokan testaaminen on osoittautunut hankalaksi, joten toistaiseksi ohjelma sisältää vain yksinkertaisia tilastointiin liittyviä testejä.**  
Sudokulle rakennettu graafinen näkymä, toistaiseksi toiminnallisuus on rajattua.
**JAR-tiedosto toimii moitteettomasti Mintillä, laitoksen virtuaalikoneella ohjelma näyttää oudolta, en tiedä vielä miksi.**


## Dokumentaatio

[työaikakirjanpito](https://github.com/karhis/ot-harjoitustyo/blob/master/dokumentointi/ty%C3%B6aikakirjanpito.md)  
[vaatimusmäärittely](https://github.com/karhis/ot-harjoitustyo/blob/master/dokumentointi/vaatimusm%C3%A4%C3%A4rittely.md)  
[arkkitehtuuri](https://github.com/karhis/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)  

## Releaset
[Viikko 5](https://github.com/karhis/ot-harjoitustyo/releases/tag/viikko5)  

## Komentorivitoiminnot
### Testaus
Testit suoritetaan komennolla  
`mvn test` 
Testikattavuusraportti luodaan komennolla  
`mvn jacoco:report` 
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_  
### Suoritettavan jarin generointi  
Komento  
`mvn package` 
generoi hakemistoon target suoritettavan jar-tiedoston _Sudoku-1.0-SNAPSHOT.jar_  
### JavaDOc  
JavaDoc generoidaan komennolla  
`mvn javadoc:javadoc` 
JAvaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_  
### Checkstyle  
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla  
`mvn jxr:jxr checkstyle:checkstyle` 
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedsto _target/Site/checkstyle.html_





