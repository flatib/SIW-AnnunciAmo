INSERT INTO categorie (nome, descrizione) 
VALUES ('Elettronica', 'Smartphone, PC, Tablet, TV, Console e Audio') 
ON CONFLICT (nome) DO NOTHING;

INSERT INTO categorie (nome, descrizione) 
VALUES ('Motori', 'Auto, Moto, Scooter e Veicoli commerciali') 
ON CONFLICT (nome) DO NOTHING;

INSERT INTO categorie (nome, descrizione) 
VALUES ('Arredamento', 'Mobili, Elettrodomestici, Fai-da-te e Giardino') 
ON CONFLICT (nome) DO NOTHING;

INSERT INTO categorie (nome, descrizione) 
VALUES ('Abbigliamento', 'Vestiti, Scarpe, Borse e Accessori') 
ON CONFLICT (nome) DO NOTHING;