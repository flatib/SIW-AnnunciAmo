-- Inserimento di 100 Annunci
INSERT INTO users (id, name, surname, email) VALUES (1, 'Mario', 'Rossi', 'mario.rossi@example.com');
INSERT INTO users (id, name, surname, email) VALUES (2, 'Luigi', 'Verdi', 'luigi.verdi@example.com');

INSERT INTO credentials (id, username, password, role, user_id) VALUES (1, 'mario', '$2a$10$0epXg50TMeIVHE0Vx9IgE.KoxGEZsUQIBtp012S0dDQc2KqBswzda', 'ROLE_USER', 1);
INSERT INTO credentials (id, username, password, role, user_id) VALUES (2, 'luigi', '$2a$10$17bHqligEcJrWNnWzf65G.uRU6.HNEzU/RdNIBRA/ZxVSCxcs06fK', 'ROLE_USER', 2);

INSERT INTO categorie (id, nome, descrizione) VALUES (1, 'Elettronica', 'Smartphone, PC, Tablet, TV, Console e Audio');
INSERT INTO categorie (id, nome, descrizione) VALUES (2, 'Motori', 'Auto, Moto, Scooter e Veicoli commerciali');
INSERT INTO categorie (id, nome, descrizione) VALUES (3, 'Arredamento', 'Mobili, Elettrodomestici, Fai-da-te e Giardino');
INSERT INTO categorie (id, nome, descrizione) VALUES (4, 'Abbigliamento', 'Vestiti, Scarpe, Borse e Accessori');
INSERT INTO categorie (id, nome, descrizione) VALUES (5, 'Sport e Hobby', 'Attrezzature sportive, Biciclette, Strumenti musicali');
INSERT INTO categorie (id, nome, descrizione) VALUES (6, 'Libri e Film', 'Testi scolastici, Romanzi, Fumetti, Vinili e Videogiochi');

-- Categoria 1: Elettronica
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (1, 'iPhone 13 Pro', 'Tenuto perfettamente, batteria 90%', 650.0, '2026-07-10T10:00:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (2, 'MacBook Air M1', 'Vendo per inutilizzo, scatola inclusa', 750.0, '2026-07-10T10:05:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (3, 'Samsung Galaxy S22', 'Colore nero, 128GB', 450.0, '2026-07-10T10:10:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (4, 'iPad Pro 11', 'Solo wifi, con Apple Pencil', 800.0, '2026-07-10T10:15:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (5, 'AirPods Pro 2', 'Nuove mai aperte, sigillate', 200.0, '2026-07-10T10:20:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (6, 'PlayStation 5', 'Con due controller e 3 giochi', 400.0, '2026-07-10T10:25:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (7, 'Xbox Series X', 'Perfetta, usata pochissimo', 380.0, '2026-07-10T10:30:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (8, 'Nintendo Switch OLED', 'Pellicola applicata dal primo giorno', 280.0, '2026-07-10T10:35:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (9, 'Smart TV LG OLED 55', 'Ritiro a mano', 850.0, '2026-07-10T10:40:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (10, 'Monitor Dell 27 4K', 'Ideale per grafica e programmazione', 300.0, '2026-07-10T10:45:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (11, 'Mouse Logitech MX Master 3', 'Ergonomico, ottime condizioni', 60.0, '2026-07-10T10:50:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (12, 'Tastiera Meccanica Keychron', 'Switch rossi, layout ANSI', 85.0, '2026-07-10T10:55:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (13, 'Cuffie Sony WH-1000XM4', 'Cancellazione del rumore top', 180.0, '2026-07-10T11:00:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (14, 'Stampante Laser Brother', 'Toner appena cambiato', 90.0, '2026-07-10T11:05:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (15, 'Scheda Video RTX 3070', 'Mai usata per mining', 350.0, '2026-07-10T11:10:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (16, 'Processore AMD Ryzen 5', 'Con dissipatore stock', 120.0, '2026-07-10T11:15:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (17, 'RAM 16GB DDR4', 'Due banchi da 8GB', 40.0, '2026-07-10T11:20:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (18, 'SSD 1TB Samsung', 'Velocissimo, ancora in garanzia', 70.0, '2026-07-10T11:25:00', 'ATTIVO', 2, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (19, 'Kindle Paperwhite', 'Senza pubblicita, cover inclusa', 90.0, '2026-07-10T11:30:00', 'ATTIVO', 1, 1);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (20, 'Apple Watch Series 7', 'Cassa 45mm, cinturino nero', 250.0, '2026-07-10T11:35:00', 'ATTIVO', 2, 1);

-- Categoria 2: Motori
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (21, 'Fiat Panda 2018', '70.000 km, tagliandata', 7500.0, '2026-07-10T12:00:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (22, 'Vespa 125 Primavera', 'Come nuova, pochissimi chilometri', 3200.0, '2026-07-10T12:05:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (23, 'Casco Integrale AGV', 'Taglia M, usato una volta', 150.0, '2026-07-10T12:10:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (24, 'Cerchi in lega 17', 'Set di 4 cerchi originali Volkswagen', 300.0, '2026-07-10T12:15:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (25, 'Gomme termiche Michelin', 'Usate una sola stagione invernale', 200.0, '2026-07-10T12:20:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (26, 'Ford Fiesta 1.4 TDCi', 'Ottima per neopatentati', 3500.0, '2026-07-10T12:25:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (27, 'Yamaha MT-07', 'Scarico Akrapovic, cupolino sportivo', 5500.0, '2026-07-10T12:30:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (28, 'Bauletto Givi 47 litri', 'Con piastra universale e doppie chiavi', 120.0, '2026-07-10T12:35:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (29, 'Tappetini auto universali', 'Nuovi, in gomma antiscivolo', 25.0, '2026-07-10T12:40:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (30, 'Portabici da tetto Thule', 'Compatibile con tutte le barre', 80.0, '2026-07-10T12:45:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (31, 'Autoradio Pioneer Bluetooth', 'Microfono esterno incluso', 90.0, '2026-07-10T12:50:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (32, 'Specchietto destro Golf 7', 'Ricambio originale, colore bianco', 60.0, '2026-07-10T12:55:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (33, 'Fari LED H7', 'Kit completo, luce bianchissima', 35.0, '2026-07-10T13:00:00', 'ATTIVO', 1, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (34, 'Cavalletto alzamoto', 'Posteriore, con forchette', 40.0, '2026-07-10T13:05:00', 'ATTIVO', 2, 2);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (35, 'Telo copriauto impermeabile', 'Adatto per utilitarie e berline compatte', 30.0, '2026-07-10T13:10:00', 'ATTIVO', 1, 2);

-- Categoria 3: Arredamento
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (36, 'Divano 3 posti Ikea', 'Sfoderabile, grigio chiaro', 150.0, '2026-07-10T13:30:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (37, 'Tavolo in legno massello', 'Allungabile, 6 posti', 250.0, '2026-07-10T13:35:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (38, 'Sedie moderne in ecopelle', 'Set da 4, gambe in metallo', 100.0, '2026-07-10T13:40:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (39, 'Letto matrimoniale con contenitore', 'Testiera imbottita, no materasso', 300.0, '2026-07-10T13:45:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (40, 'Lampada da terra', 'Stile industriale, metallo nero', 45.0, '2026-07-10T13:50:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (41, 'Frigorifero Samsung', 'No Frost, classe A++', 350.0, '2026-07-10T13:55:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (42, 'Lavatrice Bosch 8kg', 'Motore inverter silenzioso', 280.0, '2026-07-10T14:00:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (43, 'Microonde LG', 'Varie funzioni, piatto grande', 70.0, '2026-07-10T14:05:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (44, 'Tagliaerba elettrico', 'Ideale per piccoli giardini', 50.0, '2026-07-10T14:10:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (45, 'Trapano avvitatore Makita', 'Con valigetta e due batterie', 120.0, '2026-07-10T14:15:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (46, 'Cassettiera 6 cassetti', 'Bianca, guide scorrevoli in metallo', 60.0, '2026-07-10T14:20:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (47, 'Specchio da parete grande', 'Cornice in legno scuro, 120x80cm', 40.0, '2026-07-10T14:25:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (48, 'Scarpiera a 3 ribalte', 'Salvaspazio, ottima per ingresso', 35.0, '2026-07-10T14:30:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (49, 'Armadio 4 ante', 'Con specchi centrali, molto capiente', 250.0, '2026-07-10T14:35:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (50, 'Macchina del caffe', 'Usa capsule compatibili, perfetta', 50.0, '2026-07-10T14:40:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (51, 'Tenda da sole da balcone', 'A caduta, con braccetti laterali', 80.0, '2026-07-10T14:45:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (52, 'Set mobili da giardino', 'Tavolino e due poltroncine in rattan', 150.0, '2026-07-10T14:50:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (53, 'Idropulitrice Karcher', 'Perfetta per esterni e auto', 95.0, '2026-07-10T14:55:00', 'ATTIVO', 1, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (54, 'Set utensili manuali', 'Chiavi, cacciaviti, pinze', 40.0, '2026-07-10T15:00:00', 'ATTIVO', 2, 3);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (55, 'Libreria in metallo e legno', 'Stile vintage, 5 ripiani robusti', 110.0, '2026-07-10T15:05:00', 'ATTIVO', 1, 3);

-- Categoria 4: Abbigliamento
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (56, 'Giacca di pelle vintage', 'Vera pelle, taglia L', 120.0, '2026-07-10T15:10:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (57, 'Sneakers Nike Air Max 43', 'Indossate due volte, scatola originale', 85.0, '2026-07-10T15:15:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (58, 'Borsa a tracolla cuoio', 'Artigianale, interno capiente', 60.0, '2026-07-10T15:20:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (59, 'Pantaloni eleganti neri', 'Taglia 44, taglio dritto', 30.0, '2026-07-10T15:25:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (60, 'Occhiali da sole Ray-Ban', 'Modello Aviator classico', 75.0, '2026-07-10T15:30:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (61, 'Cappotto invernale di lana', 'Lungo, taglia M, molto caldo', 95.0, '2026-07-10T15:35:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (62, 'Scarponcini Timberland 42', 'Classico scarponcino giallo, usati poco', 80.0, '2026-07-10T15:40:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (63, 'Zaino da trekking 50L', 'Impermeabile, con supporto schiena', 45.0, '2026-07-10T15:45:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (64, 'Camicia di lino bianca', 'Taglia L, fresca per l''estate', 25.0, '2026-07-10T15:50:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (65, 'Orologio Casio vintage', 'Cinturino in acciaio, funzionante', 35.0, '2026-07-10T15:55:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (66, 'T-shirt band rock anni 80', 'Stampe originali, varie taglie M', 15.0, '2026-07-10T16:00:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (67, 'Sciarpa in cachemire', 'Morbida e senza difetti', 40.0, '2026-07-10T16:05:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (68, 'Stivali alti neri tg 38', 'In vera pelle con cerniera', 55.0, '2026-07-10T16:10:00', 'ATTIVO', 2, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (69, 'Cintura in cuoio marrone', 'Fibbia in ottone, 100cm', 20.0, '2026-07-10T16:15:00', 'ATTIVO', 1, 4);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (70, 'Vestito da sera rosso', 'Elegante, usato per un solo evento', 65.0, '2026-07-10T16:20:00', 'ATTIVO', 2, 4);

-- Categoria 5: Sport e Hobby
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (71, 'Bicicletta MTB 29', 'Freni a disco, ammortizzata', 220.0, '2026-07-10T16:30:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (72, 'Tapis Roulant pieghevole', 'Perfetto per allenamento in casa', 180.0, '2026-07-10T16:35:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (73, 'Chitarra acustica Fender', 'Corde nuove, borsa imbottita inclusa', 130.0, '2026-07-10T16:40:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (74, 'Set manubri regolabili', 'Da 2kg a 24kg ciascuno', 150.0, '2026-07-10T16:45:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (75, 'Racchetta da tennis Babolat', 'Usata un mese, grip sostituito', 80.0, '2026-07-10T16:50:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (76, 'Pallone da basket Molten', 'Misura 7, ottime condizioni', 25.0, '2026-07-10T16:55:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (77, 'Tavola da Surf 7.0', 'In schiuma, perfetta per principianti', 120.0, '2026-07-10T17:00:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (78, 'Panca piana per palestra', 'Inclinabile e robusta', 60.0, '2026-07-10T17:05:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (79, 'Monopattino elettrico Xiaomi', 'Autonomia 30km, gomme nuove', 200.0, '2026-07-10T17:10:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (80, 'Pattini in linea tg 40', 'Cuscinetti ABEC 7, ruote in gomma', 45.0, '2026-07-10T17:15:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (81, 'Tastiera MIDI Akai 25 tasti', 'Pad retroilluminati, cavo USB', 70.0, '2026-07-10T17:20:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (82, 'Sacco da Boxe 30kg', 'Incluso di catene e moschettone', 55.0, '2026-07-10T17:25:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (83, 'Racchette Padel Kuikma', 'In carbonio, senza graffi', 90.0, '2026-07-10T17:30:00', 'ATTIVO', 1, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (84, 'Tenda da campeggio 4 posti', 'Montaggio rapido, due camere', 85.0, '2026-07-10T17:35:00', 'ATTIVO', 2, 5);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (85, 'Sacco a pelo invernale', 'Tenuta fino a -5 gradi', 40.0, '2026-07-10T17:40:00', 'ATTIVO', 1, 5);

-- Categoria 6: Libri e Film
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (86, 'Il Signore degli Anelli', 'Edizione illustrata, copertina rigida', 40.0, '2026-07-10T17:50:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (87, 'Collana Harry Potter', 'Tutti i 7 libri, edizione Salani', 60.0, '2026-07-10T17:55:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (88, 'Vinile The Dark Side of the Moon', 'Stampa originale 1973', 85.0, '2026-07-10T18:00:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (89, 'Fumetti Dylan Dog dal n.1', 'Collezione dei primi 50 numeri', 120.0, '2026-07-10T18:05:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (90, 'Videogioco Elden Ring PS5', 'Custodia intatta, gioco finito', 45.0, '2026-07-10T18:10:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (91, 'Manuale di D&D 5E', 'Manuale del Giocatore, in italiano', 35.0, '2026-07-10T18:15:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (92, 'Cofanetto DVD Star Wars', 'Saga completa Episodi I - VI', 30.0, '2026-07-10T18:20:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (93, 'Libro di programmazione Java', 'Ultima edizione, mai usato', 25.0, '2026-07-10T18:25:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (94, 'Manga One Piece lotti 1-20', 'Letti una volta, tenuti benissimo', 70.0, '2026-07-10T18:30:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (95, 'Vinile dei Queen A Night at the Opera', 'Senza graffi', 45.0, '2026-07-10T18:35:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (96, 'Gioco da tavolo Catan', 'Completo di tutti i pezzi', 35.0, '2026-07-10T18:40:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (97, 'Trilogia Hunger Games libri', 'Copertina flessibile', 20.0, '2026-07-10T18:45:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (98, 'Videogioco Mario Kart 8 Switch', 'Perfetto, gioco iconico', 40.0, '2026-07-10T18:50:00', 'ATTIVO', 2, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (99, 'Blu-ray Ritorno al Futuro', 'Edizione 30esimo anniversario', 25.0, '2026-07-10T18:55:00', 'ATTIVO', 1, 6);
INSERT INTO annunci (id, titolo, descrizione, prezzo, data_pubblicazione, stato, autore_id, categoria_id) VALUES (100, 'Enciclopedia della Scienza', '10 volumi, illustrata', 50.0, '2026-07-10T19:00:00', 'ATTIVO', 2, 6);

ALTER SEQUENCE annunci_seq RESTART WITH 101;
ALTER SEQUENCE users_seq RESTART WITH 100;
ALTER SEQUENCE categorie_seq RESTART WITH 100;
ALTER SEQUENCE credentials_seq RESTART WITH 100;
SELECT setval(pg_get_serial_sequence('annunci', 'id'), (SELECT MAX(id) FROM annunci));
SELECT setval(pg_get_serial_sequence('users', 'id'), (SELECT MAX(id) FROM users));
SELECT setval(pg_get_serial_sequence('categorie', 'id'), (SELECT MAX(id) FROM categorie));