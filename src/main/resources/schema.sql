-- Création de la base de données
CREATE DATABASE DB_Gestock;


CREATE DATABASE IF NOT EXIST;
USE DB_Gestock;

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(255),
    stock INT NOT NULL,
    photo VARCHAR(255)


-- Création de la table Product
CREATE TABLE Product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DOUBLE PRECISION NOT NULL,
                         category VARCHAR(255),
                         stock INT NOT NULL,
                         photo VARCHAR(255)
);

-- Ajout de données dans la table Product
INSERT INTO Product (name, price, category, stock, photo) VALUES
                                                              ('Produit1', 10.99, 'Alimentation', 50, 'photo1.jpg'),
                                                              ('Produit2', 20.99, 'Électronique', 30, 'photo2.jpg'),
                                                              ('Produit3', 15.49, 'Vêtements', 25, 'photo3.jpg');

-- Création de la table TableResto
CREATE TABLE TableResto (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            status VARCHAR(255) NOT NULL,
                            seats INT NOT NULL,
                            reserved BOOLEAN NOT NULL,
                            occupationTime VARCHAR(255),
                            currentOrder TEXT
);

-- Ajout de données dans la table TableResto
INSERT INTO TableResto (name, status, seats, reserved, occupationTime, currentOrder) VALUES
                                                                                         ('Table1', 'Libre', 4, false, NULL, NULL),
                                                                                         ('Table2', 'Occupée', 6, true, '2 heures', '{"item": "Pizza", "quantity": 2}'),
                                                                                         ('Table3', 'Libre', 2, false, NULL, NULL);

-- Création de la table Orders
CREATE TABLE Orders (
                        id SERIAL PRIMARY KEY,
                        idTable INT NOT NULL,
                        productList JSON NOT NULL,
                        isPaid BOOLEAN NOT NULL,
                        totalPrice DECIMAL(10, 2) NOT NULL,
                        date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Ajout de données dans la table Orders
INSERT INTO Orders (idTable, productList, isPaid, totalPrice) VALUES
                                                                  (1, '{"item": "Burger", "quantity": 1}', true, 15.99),
                                                                  (2, '{"item": "Pasta", "quantity": 2}', false, 30.49),
                                                                  (3, '{"item": "Salad", "quantity": 3}', true, 25.99);
