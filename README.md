# AnnunciAmo

Sistema informativo su Web per la pubblicazione e ricerca di annunci di compravendita tra utenti, con sistema di messaggistica integrato tra acquirente e venditore.

Progetto personale per il corso di **Sistemi Informativi su Web**, a.a. 2025/2026, Università degli Studi Roma Tre.

**Autori:** [Flavio Tiberia, Mauro Pigna]

---

## Descrizione del progetto

AnnunciAmo è una piattaforma che permette agli utenti registrati di pubblicare, ricercare e gestire annunci di vendita, organizzati per categoria. Gli utenti interessati a un annuncio possono contattare il venditore tramite un sistema di chat integrato, associato a ciascun annuncio.

Il progetto segue un'architettura a livelli (Persistence, Service, Controller) come richiesto dalla consegna, con gestione delle transazioni tramite `@Transactional` e separazione netta delle responsabilità tra i layer.

## Tecnologie utilizzate

| Livello | Tecnologia |
|---|---|
| Backend | Spring Boot |
| Persistenza | JPA / Hibernate |
| Database | PostgreSQL |
| Frontend tradizionale | Thymeleaf |
| Frontend dinamico | React + TypeScript (Vite) |
| Autenticazione | Spring Security |
| HTTP client (React) | Axios |

## Modello di dominio

Entità principali del sistema:

- **User** – utente registrato (username, password, ruolo)
- **Annuncio** – annuncio di vendita (titolo, descrizione, prezzo, stato, data pubblicazione)
- **Categoria** – categoria merceologica dell'annuncio
- **Messaggio** – messaggio scambiato tra due utenti in relazione a un annuncio
- **Immagine** – immagine associata a un annuncio

Relazioni principali:

- Un `Annuncio` appartiene a un `User` (autore) e a una `Categoria`
- Un `Annuncio` può avere una `Immagine` e una lista di `Messaggio`
- Un `Messaggio` è associato a un `Annuncio`, un mittente e un destinatario (entrambi `User`)

## Casi d'uso implementati

Come richiesto dalla consegna, il sistema implementa almeno 6 casi d'uso comprendenti operazioni CRUD complete :

- **Creazione (Create):** pubblicazione di un nuovo annuncio da parte di un utente registrato
- **Aggiornamento (Update):** modifica dei dati di un proprio annuncio (titolo, descrizione, prezzo, stato)
- **Cancellazione (Delete):** rimozione di un proprio annuncio
- **Lettura (Read) 1:** ricerca e visualizzazione degli annunci in homepage, con filtri per nome, categoria e prezzo massimo
- **Lettura (Read) 2:** visualizzazione del dettaglio di un annuncio e della cronologia dei messaggi associati
- **Caso d'uso trasversale:** invio di un messaggio relativo a un annuncio, con aggiornamento della lista delle chat dell'utente (coinvolge `Annuncio`, `Messaggio` e `User`)

Autenticazione e registrazione utente sono presenti ma non contano come casi d'uso ai fini della valutazione, come specificato nella consegna .

## Autenticazione e autorizzazione

- Login tramite username e password (Spring Security)
- Ruoli: `USER` (pubblicazione annunci, messaggistica), eventuali funzionalità aggiuntive per ruoli con permessi elevati
- Le operazioni di modifica/cancellazione di un annuncio sono consentite solo all'autore dello stesso

## Struttura del progetto
annunciamo/
├── backend/ # Spring Boot
│ ├── src/main/java/it/uniroma3/siw/
│ │ ├── model/ # Entità JPA
│ │ ├── dto/ # DTO per la serializzazione REST
│ │ ├── repository/ # Repository JPA
│ │ ├── service/ # Logica di business, transazioni
│ │ └── controller/ # Controller MVC (Thymeleaf) e REST
│ └── src/main/resources/templates/ # Vista Thymeleaf
└── frontend/ # React + TypeScript
└── src/
├── components/ # SearchBar, AnnuncioCard, ecc.
├── pages/ # AnnunciPage e altre pagine
├── services/ # Chiamate API (Axios)
└── types/ # Definizioni TypeScript condivise (index.ts)

## Istruzioni per l'esecuzione

### Backend

```bash
mvn spring-boot:run
```

Il backend sarà disponibile su `http://localhost:8080`.

### Frontend React

```bash
npm install
npm run dev
```

Il frontend sarà disponibile su `http://localhost:5173`.

### Configurazione database

Configurare le credenziali PostgreSQL in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/annunciamo
spring.datasource.username=[username]
spring.datasource.password=[password]
```
