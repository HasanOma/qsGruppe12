# qsGruppe12
QS alternativ presentert av gruppe 12

QS er et program som enkelt lar studenter holde oversikt over øvingene sine, samt stå i godkjenningskø i sine respektive emner.  
Studentassistenter og undervisere/emneansvarlige kan enkelt god- og underkjenne øvinger.   
Undervisere/emneansvarlig har og tilgang til flere administrative verktøy  
  
Swagger dokumentasjon finnes på /swagger-ui/

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
  - Alt en _studentassisten_ kan
  - Legge til en eller flere studenter i databasesystemet
  - Arkivere emner
  - Opprette emner, med grupperingsregler
  - Slette og oppdatere emner (foreløpig kun implementert i backend)
  

## Databaseskjema
TODO legg inn bilder av databasen


## Teknologier 
- **Vuejs** - Javascript-rammeverk for å bygge brukergrensesnitt
- **Spring Boot** - Server-side rammeverk
- **JWT** - Autentisering for REST APIer
- **Docker** - Rammeverk for å utvikle, teste ut og kjøre applikasjoner i kontainere 
- **MySQL** - Database (DBMS)
- **H2 Database Engine** - In-memory database til testing
- **Swagger** - API dokumentasjon
- **Bootstrap / Bootstrap Studio** - UI design






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
TODO skriv om sikkerhet



## API-dokumentasjon
TODO skriv om dokumentasjonen og swagger

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
mvn spring-boot:run
```

### Frontend
```
git clone https://github.com/HasanOma/qsGruppe12.git
cd frontend
npm install
npm run serve
```




## CI/CD
### CI - Testing
Vi har hele tiden kjørt tester fortløpende med CI for både frontend og backend ved hjelp av GitHub Actions
Dette er noe vi kunne ha utnyttet oss bedre av, da vi iblant har pushet tester som feiler
### CD
Vi har ikke deployet QS noe sted, så dette punktet er ikke relevant



## Hjelp
Lurer du på noe? Har du spørsmål eller funnet en bug? Lag et issue eller send en av oss en mail på enten andetel@stud.ntnu.no, hasanro@stud.ntnu.no eller bragemi@stud.ntnu.no
