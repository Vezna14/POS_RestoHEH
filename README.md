# Gestock_prj_G2
Projet Application Java Spring boot React pour le cours appl &amp; systèmes entreprise

# Présentation du projet
Nous allons réaliser une application qui permet de créer des commandes pour la vente de 
produit et gérer le stock de l’entreprise.
# Détails du projet

## API Reference



-----------------------------------------------------------------------------
Order Controller
-----------------------------------------------------------------------------

Create Order
POST /resto/orders

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `order`   | `object` | Order details              |

Pay Order
POST /resto/orders/pay

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `order`   | `object` | Order details              |

Pay Order by ID
PUT /resto/orders/pay/${id}

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id`      | `number` | ID of the order to pay      |

Show Order
GET /resto/orders/show/${idTable}

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `idTable` | `number` | ID of the table to show the order |

-----------------------------------------------------------------------------
Product Controller
-----------------------------------------------------------------------------

Get All Products
GET /products

Create Product
POST /product

| Parameter       | Type     | Description                |
| :-------------- | :------- | :------------------------- |
| `productToAdd`  | `object` | Product details           |

-----------------------------------------------------------------------------
Table Resto Controller
-----------------------------------------------------------------------------

Get All Tables
GET /tableRestos

Delete Table
DELETE /deleteTable/${id}

| Parameter | Type     | Description             |
| :-------- | :------- | :---------------------- |
| `id`      | `number` | ID of the table to delete|

Create Table
POST /tableRestos

| Parameter  | Type     | Description                |
| :--------- | :------- | :------------------------- |
| `newTable` | `object` | Table details              |

Toggle Status Table
PUT /toggleStatus/${id}

| Parameter | Type     | Description               |
| :-------- | :------- | :------------------------ |
| `id`      | `number` | ID of the table to toggle  |

