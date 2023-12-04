-- schéma SQL = DDL = create table etc
-- create_table.sql

CREATE DATABASE IF NOT EXISTS DB_Gestock;
USE DB_Gestock;

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(255),
    stock INT NOT NULL,
    photo VARCHAR(255)

);
-- Insérer le premier produit
INSERT INTO Product (id, name, price, category, stock, photo)
VALUES (1, 'Produit A', 29.99, 'Electronique', 50, 'produitA.jpg');

-- Insérer le deuxième produit
INSERT INTO Product (id, name, price, category, stock, photo)
VALUES (2, 'Produit B', 19.99, 'Mode', 30, 'produitB.jpg');

-- Insérer le troisième produit
INSERT INTO Product (id, name, price, category, stock, photo)
VALUES (3, 'Produit C', 39.99, 'Maison', 20, 'produitC.jpg');
