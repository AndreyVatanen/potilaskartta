Yleiskuvaus

Potilaskartta on paikallisesti ajettava fullstack-sovellus, jonka tarkoituksena on mallintaa päivystyspoliklinikan potilasvirtaa ja potilashallintaa.
Sovellus on toteutettu Spring Boot -taustapalvelulla ja React-pohjaisella käyttöliittymällä.

Projektin painopiste ei ole visuaalisessa viimeistelyssä vaan:

selkeässä domain-mallinnuksessa

relaatiotietokannan oikeassa käytössä

frontend–backend-yhteistyössä

tilapohjaisessa potilaslogiikassa (aktiivinen ↔ kotiutettu)

Keskeiset toiminnallisuudet
Potilaiden lisääminen

Käyttäjä voi lisätä potilaan antamalla:

etunimen

sukunimen

iän

Potilas lisätään joko:

odotusaulaan

tai ennakkoilmoitettuihin ambulanssipotilaisiin

Potilaiden sijoittelu ja siirtäminen

Potilaat jaetaan kolmeen pääryhmään:

Odotusaula

Ambulanssipotilaat

Päivystyksen osastopaikat (1–9)

Käyttäjä voi:

siirtää potilaan odotusaulasta tai ambulanssista osastopaikalle

määrittää siirron yhteydessä:

kiireellisyysluokituksen

A = kiireellinen

B = keskikiireellinen

C = ei kiireellinen

osastopaikan

Osastopaikoilla potilaan:

sukunimi

kiireellisyysluokitus
näytetään värikoodattuna käyttöliittymässä.

Potilastietojen tarkastelu ja muokkaus

Osastopaikalla olevan potilaan tiedot voidaan avata erilliseen näkymään.

Näkymässä käyttäjä voi tarkastella ja hallita:

potilaan perustietoja (ikä, kiireellisyysluokitus)

diagnooseja

lääkityksiä

hoito-ohjeita

jatkohoito-ohjeita

Tietomalli on tarkoituksella pidetty rajattuna ja yksinkertaisena, koska projekti on henkilökohtainen harjoitus eikä oikea potilastietojärjestelmä.

Kotiutus

Potilas voidaan kotiuttaa riippumatta siitä, missä hän sijaitsee:

odotusaulassa

ambulanssipotilaissa

osastopaikalla

Kotiutuksen yhteydessä:

käyttäjä kirjoittaa kotiutustiedon / yhteenvedon

potilaan tila vaihtuu AKTIIVINEN → KOTIUTETTU

potilas poistuu aktiivisista näkymistä

Potilasta ei poisteta tietokannasta, vaan:

kotiutus tallennetaan omana entiteettinään

potilaan ja kotiutustiedon välillä on selkeä one-to-one-suhde

Päivystyksen kuormituksen visualisointi

Käyttöliittymässä näytetään graafisesti:

odotusaulan potilasmäärä (vihreä)

ambulanssipotilaat (punainen)

osastopotilaat (keltainen)

Visualisointi antaa nopean yleiskuvan päivystyksen kuormitustilanteesta.


---------------------------------------------------------------------------------------------------------------------------

Tekninen toteutus
Backend

Java + Spring Boot

Spring Data JPA (Hibernate)

PostgreSQL

Transaktionhallinta (@Transactional)

Selkeä domain-malli:

Potilas

Kotiutustieto

Paikka

Ambulanssi

Odotusaula

Diagnoosi, Lääkelista, Hoito-ohje, Jatkohoito

Enum-pohjaiset tilat ja luokitukset

Frontend

React (Vite)

Fetch API

Komponenttipohjainen rakenne

Tilanhallinta Reactin hookeilla

Modal-pohjaiset toiminnot (lisäys, siirto, kotiutus)

Arkkitehtuuriset periaatteet

Potilasta ei koskaan poisteta kotiutuksen yhteydessä → tilaohjattu logiikka

Relaatiot on mallinnettu eksplisiittisesti (OneToOne, OneToMany, ManyToOne)

Backend vastaa liiketoimintalogiikasta, frontend näkymästä ja tilapäivityksistä

Tietokannan eheys suojattu sovelluslogiikassa

Kehitysympäristö

Backend: http://localhost:8080

Frontend: http://localhost:5173

Sovellus on tarkoitettu ajettavaksi paikallisesti kehitysympäristössä
