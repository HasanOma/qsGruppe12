# qsGruppe12
QS alternativ presentert av gruppe 12

QS er et program som enkelt lar studenter holde oversikt over øvingene sine, samt stå i godkjenningskø i sine respektive emner.  
Studentassistenter og undervisere/emneansvarlige kan enkelt god- og underkjenne øvinger.   
Undervisere/emneansvarlig har og tilgang til flere administrative verktøy  
Kildekoden kan finnes på https://github.com/HasanOma/qsGruppe12
  
Swagger dokumentasjon finnes på `/swagger-ui/`

## Innhold ##
1. [Medlemmer](#medlemmer)
2. [Applikasjonen](#applikasjonen)
3. [Databaseskjema](#databaseskjema)
4. [Teknologier](#teknologier)
5. [Applikasjonsstruktur](#applikasjonsstruktur)
6. [Sikkerhet](#sikkerhet)
7. [API-dokumentasjon](#api-dokumentasjon)
8. [Programvarekrav](#programvarekrav)
9. [Installasjon](#installasjon)
10. [CI/CD](#cicd)
11. [Hjelp](#hjelp)

## Medlemmer
Anders Tellefsen - andetel@stud.ntnu.no  
Hasan Rehman Omarzae - hasanro@stud.ntnu.no  
Brage Minge - bragemi@stud.ntnu.no  


## Applikasjonen 
Prosjektet består av både backend og frontend hvorav begge deler er utviklet med moderne og stabile rammeverk. 

På frontend bruker vi Vuejs som er up and coming og i vinden for tiden. Vi har skrevet i ren JavaScript da ingen av gruppemedlemmene har særlig erfaring med f.eks TypeScript, og vi ikke så på projektperioden som en tid for å lære seg så mye nytt.  
I backend har vi benyttet oss Spring Boot som rammeverk for å bygge REST-apiet vårt. Dette har vi brukt sammen med Java og Maven. Java er en tidløss klassiker av et backend språk, og det er god støtte for det på nett. Vi har brukt Maven som byggeverktøy fordi gruppen har god erfaring med det fra tidligere prosjekter, og fordi det støtter mye og er godt dokumentert..

Vi har benyttet oss av både CI og CD under prosjektet for å sørge for den beste kontinuerlige utviklingen. Dette er noe vi kunne vært bedre på å benytte oss av under f.eks testfasen da det noen ganger har blitt pushet tester som feiler. 

Applikasjonen har for øyeblikket tre typer brukere: 
  - Student
  - Læringsassistent
  - Administrator

En _student_ kan:
  - Se øvingene sine i forskjellge emner
  - Stille seg i kø, enten for hjelp eler for godkjenning
  - Endre passord
  - Legge til en alternativ epost

En _studentassistent_ kan: 
  - Aktivere/deaktivere kø
  - Godkjenne/underkjenne øvinger
  - Slette studenter i kø
  
En _administrator_ kan:
  - Alt en _studentassistent_ kan
  - Legge til en eller flere studenter i databasesystemet
  - Arkivere emner
  - Opprette emner, med grupperingsregler
  - Slette og oppdatere emner (foreløpig kun implementert i backend)
  

## Databaseskjema

![QSgruppe12](https://user-images.githubusercontent.com/56249709/161622542-4112a858-2a83-4e07-9dfa-7690d952d5f2.jpg)  
Dette er en enkel oversikt over hvordan vi har valgt å legge opp databasen vår


## Teknologier 
- **Vuejs** - Javascript-rammeverk for å bygge brukergrensesnitt
- **Spring Boot** - Server-side rammeverk
- **JWT** - Autentisering for REST APIer
- **Docker** - Rammeverk for å utvikle, teste ut og kjøre applikasjoner i kontainere 
- **MySQL** - Database (DBMS)
- **H2 Database Engine** - In-memory database til testing
- **Swagger** - API dokumentasjon
- **Bootstrap / Bootstrap Studio** - UI design
- **opencsv** - CSV fil behandler for java






## Applikasjonsstruktur
![image](https://user-images.githubusercontent.com/56249709/161533675-a3415eb8-2f97-4542-aabd-6390a5d09fad.png)
![image](https://user-images.githubusercontent.com/56249709/161533791-b568bb3e-55cb-414d-849a-658bfe9e70d5.png)

Vi har prøvd å strukturere prosjektet rundt de forskjellige modellene og laget pakker for hver av dem slik at man f.eks. finner alle service-klassene i service-pakken, og da igjen det som f.eks har med course å gjøre inn under service.course, slik at man hele tiden har en naturlig struktur og det ikke skal være nødvendig å lete noe særlig så lenge man vet hva man er på jakt etter.

Controller
Controller-klassene er view-laget av applikasjonen og tilbyr REST-endepunkter for klienter over HTTP.

Service
Service-klassene inneholder det meste av forretningslogikken slik at den operere uavhengig av laget over.

Repositories
Repositories er repository-klasser som er ansvarlig for å kommunisere med databasen.

Modeller & DTOer
Modellene våre ligger i model-pakken og deres respektive DTOer (Data Transfer Objects) ligger i dto-pakken. Dette er gjort for å decouple/dekople modell-laget fra view-laget slik at de kan utvikle seg så uavhenig av hverandre som nødvendig

## Sikkerhet
Sikkerhet er kanskje noe det viktigste i ett produkt. Det å bruke et program der man vet at brukerinformasjonen
er trygg gir en god følelse. Sikkerhet er derfor noe vi har tatt på fokusert mye på under prosjektet. På frontend bruker vi restiktiv routing og authentisering av rolle for å vise fram komponenter. Når frontend og backend kommuniserer bruker vi JWT token for å authentisere bruker rollen. Tokenet til brukeren fornyes hvert 2.time, da køen til godkjenning kan bli litt langvarig til tider. 
Vi holder fast på at sikkerhet er viktig og har authentikasjon og restriktiv tilgang på mange av endepunktene våre. Passordene i databasen blir kryptert og saltet med Bcrypt passwod encoder import som er en del av spring security. 


## API-dokumentasjon
Vi bruker swagger til api-dokumentasjon  
  
  
![image](https://user-images.githubusercontent.com/56249709/161589261-b853de00-1817-4153-9940-15195f978a4a.png)  

Her kan man se en oversikt over de forskjellige kontrollerne som inneholder endepunktene i applikasjonen  

![image](https://user-images.githubusercontent.com/56249709/161589574-3802a870-9cd6-4a0a-af4e-29e195433aa1.png)  

For å logge inn sender man enkelt og greit inn brukernavn og passord  

![image](https://user-images.githubusercontent.com/56249709/161589787-ddf1b80e-3e58-4fcf-9542-89464653d3b9.png)  

Da vil man få tilbake denne responsen. Herfra henter man ut jwtToken  

![image](https://user-images.githubusercontent.com/56249709/161589998-a0ed8732-39c3-43a2-8d00-737871b77282.png)  

Legger så inn tokenet sammen med 'Bearer' i authorization  

![image](https://user-images.githubusercontent.com/56249709/161590068-7e303884-2980-4f2f-b358-0e6e5b7b813e.png)  

Når man da trykker på enter-tasten kan man se at man er logget inn  
Er du interessert i å se mer på swagger så kan du kjøre applikasjonen og navigere deg til `/swagger-ui/`



## Programvarekrav
### Backend
- Java
- Maven
- Docker

### Frontend
- nodejs med npm

## Installasjon

### Backend
```
git clone https://github.com/HasanOma/qsGruppe12.git
docker compose up
mvn clean install spring:boot-run
```

Evt kan man bare trykke på 'play'-ikonet inne i `src/main/java/com/example/qsgruppe12/QsGruppe12Application.java` om du jobber i en IDE


### Frontend
```
git clone https://github.com/HasanOma/qsGruppe12.git
cd frontend
npm install
npm run serve
```
### Rekkefølge
Det er viktig for førstegangsinstallasjon at man kjører ```docker compose up``` før man starter backend. Etter dette er gjort kan man starte frontend

### Dummy data

Dummy-brukere som allerede er tilstede i databasen:

Admin: 
andetel@stud.ntnu.no

TA(Læringsassistent):
bragem@stud.ntnu.no

Student:
hasano@stud.ntnu.no

Alle har passord: passord123

## CI/CD
### CI - Testing
Vi har hele tiden kjørt tester fortløpende med CI for både frontend og backend ved hjelp av GitHub Actions
Dette er noe vi kunne ha utnyttet oss bedre av, da vi iblant har pushet tester som feiler.  
Generelt testing av backend har vi valgt å kommentere ut da det ikke har blitt undervist i og vi ikke har fått det til å fungere optimalt grunnet tidsbegrensningen på prosjektet.  
Frontendtestene får vi ikke til å fungere med GitHub actions, selv om testene kjører og bestås lokalt. Vi tror dette har med ES2015 kompatibilitet å gjøre, men fikk ikke til å fikse dette grunnet tidsbegrensingene som er satt på prosjektet. For mer info om dette kan man lese denne tråden: https://github.com/TypeStrong/ts-node/issues/922

### CD
Vi har ikke deployet QS noe sted, så dette punktet er ikke relevant

## Hjelp
Lurer du på noe? Har du spørsmål eller funnet en bug? Lag et issue eller send en av oss en mail på enten andetel@stud.ntnu.no, hasanro@stud.ntnu.no eller bragemi@stud.ntnu.no
