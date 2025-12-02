Tämä projekti on kehitteillä oleva fullstack-sovellus, jonka tarkoituksena on mallintaa päivystyspoliklinikan potilashallintajärjestelmää.

Sovellus sisältää Spring Boot -taustapalvelun sekä React-pohjaisen käyttöliittymän, joiden avulla voidaan:

hallita potilaita ja henkilökuntaa

sijoittaa potilaita päivystyksen eri paikkoihin

tarkastella päivystyksen kuormitusta datan avulla

visualisoida tilannetta yksinkertaisten UI-komponenttien kautta

Projekti on vielä kehitysvaiheessa, ja toiminnallisuuksia laajennetaan sekä backendin että frontendin puolella.

Projektin tavoite

Tavoitteena on luoda yksinkertainen potilaskartta, joka tarjoaa:

Potilasrekisterin

potilaan luonti

potilaan poistaminen

potilaslistojen tarkastelu

Henkilökunnan hallinta

uuden työntekijän lisääminen

henkilökunnan määrä

työntekijän poistaminen

Päivystyksen paikat

potilaan sijoittaminen hoitopaikalle

hoitopaikkojen hallinta
Päivystyksen kuormituksen seuranta

Backend laskee päivystyksen ruuhkatilannetta potilaiden kiireellisyysluokituksen perusteella.
Frontend näyttää kuormituksen havainnollistettuna (esim. kaaviot).

Visuaalinen potilaskartta

Käyttöliittymässä esitetään:

odotusaulan potilaat

ambulanssipotilaat

hoitopaikat

hallintapaneeli toiminnoille

Frontend käyttää Material UI -komponentteja (DataGrid, PieChart) datan esittämiseen.

Käytetyt teknologiat
Backend

Java / Spring Boot

Spring Data JPA

H2 / PostgreSQL (riippuen jatkokehityksestä)

REST API

Lombok

Frontend

React

Material UI (MUI DataGrid, Charts)

JavaScript / JSX

CSS

Kehitysvaihe:

Projekti on aktiivisesti kehitteillä.
Seuraavat osa-alueet ovat vielä työn alla:

frontendin ja backendin integrointi (API-kutsut)

käyttöliittymän laajentaminen

parempi validointi ja virheenkäsittely

paikkakohtainen potilashallinta

päivystyskohtaiset tilastot

lisätoimenpiteiden kirjaaminen

autentikointi (mahdollisesti myöhemmin)

Tarkoitus ja käyttökohde

Projektin ensisijainen tarkoitus on:

demonstroida fullstack-osaamista (Java + React)

mallintaa päivystyksen potilasvirtaa yksinkertaisessa muodossa

tarjota perusrakenne laajemmalle terveydenhuollon hallintasovellukselle

toimia oppimisprojektina ja portfolionäytteenä trainee- ja juniortason IT-rooleihin
