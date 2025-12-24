# Potilaskartta

## Yleiskuvaus

Potilaskartta on paikallisesti ajettava fullstack-sovellus, jonka tarkoituksena on mallintaa päivystyspoliklinikan potilasvirtaa ja potilashallintaa. Sovellus on toteutettu Spring Boot -taustapalvelulla ja React-pohjaisella käyttöliittymällä.

Projektin painopiste ei ole visuaalisessa viimeistelyssä, vaan teknisessä toteutuksessa ja selkeässä arkkitehtuurissa. Tavoitteena on demonstroida domain-mallinnusta, relaatiotietokannan käyttöä sekä frontend–backend-yhteistyötä.

Projekti on tehty henkilökohtaisena projektina trainee- ja junior-tason paikkoja varten.

---

## Keskeiset toiminnallisuudet

### Potilaiden lisääminen

Käyttäjä voi lisätä potilaan antamalla:
- etunimen
- sukunimen
- iän

Potilas lisätään joko:
- odotusaulaan
- ennakkoilmoitettuihin ambulanssipotilaisiin

Uusi potilas on aina tilassa **AKTIIVINEN**.

---

### Potilaiden sijoittelu ja siirtäminen

Potilaat jaetaan kolmeen pääryhmään:
- Odotusaula
- Ambulanssipotilaat
- Päivystyksen osastopaikat (1–9)

Käyttäjä voi siirtää potilaan odotusaulasta tai ambulanssista osastopaikalle. Siirron yhteydessä määritellään:
- kiireellisyysluokitus  
  - A = kiireellinen  
  - B = keskikiireellinen  
  - C = ei kiireellinen  
- osastopaikka

Osastopaikalla potilaasta näytetään:
- sukunimi
- kiireellisyysluokitus värikoodattuna

---

### Potilastietojen tarkastelu ja muokkaus

Osastopaikalla olevan potilaan tiedot voidaan avata erilliseen näkymään.

Näkymässä käyttäjä voi tarkastella ja hallita:
- potilaan perustietoja (ikä, kiireellisyysluokitus)
- diagnooseja
- lääkityksiä
- hoito-ohjeita
- jatkohoito-ohjeita

Tietomalli on pidetty tarkoituksella rajattuna ja yksinkertaisena.

---

### Kotiutus

Potilas voidaan kotiuttaa riippumatta siitä, missä hän sijaitsee:
- odotusaulassa
- ambulanssipotilaissa
- osastopaikalla

Kotiutuksen yhteydessä:
- käyttäjä kirjoittaa kotiutustiedon / yhteenvedon
- potilaan tila vaihtuu **AKTIIVINEN → KOTIUTETTU**
- potilas poistuu aktiivisista näkymistä

Potilasta ei poisteta tietokannasta.  
Kotiutus tallennetaan omana entiteettinään, ja potilaan ja kotiutustiedon välillä on one-to-one-suhde.

---

### Päivystyksen kuormituksen visualisointi

Käyttöliittymässä näytetään graafisesti:
- odotusaulan potilasmäärä (vihreä)
- ambulanssipotilaat (punainen)
- osastopotilaat (keltainen)

Visualisointi antaa nopean yleiskuvan päivystyksen kuormitustilanteesta.

---

## Tekninen toteutus

### Backend

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Transaktionhallinta (`@Transactional`)

Keskeiset entiteetit:
- Potilas
- Kotiutustieto
- Paikka
- Ambulanssi
- Odotusaula
- Diagnoosi
- Lääkelista
- Hoito-ohje
- Jatkohoito

Potilaan tila ja kiireellisyys on mallinnettu enum-arvoilla.

---

### Frontend

- React (Vite)
- Fetch API
- Komponenttipohjainen rakenne
- Tilanhallinta Reactin hookeilla
- Modal-pohjaiset toiminnot (lisäys, siirto, kotiutus)

---

## Arkkitehtuuriset periaatteet

- Potilasta ei poisteta kotiutuksen yhteydessä → tilaohjattu logiikka
- Relaatiot on mallinnettu eksplisiittisesti (`OneToOne`, `OneToMany`, `ManyToOne`)
- Backend vastaa liiketoimintalogiikasta ja tietokannan eheydestä
- Frontend vastaa käyttöliittymästä ja näkymien tilasta

---

## Kehitysympäristö

- Backend: http://localhost:8080
- Frontend: http://localhost:5173

Sovellus on tarkoitettu ajettavaksi paikallisesti kehitysympäristössä.

