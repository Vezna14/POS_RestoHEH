import React from 'react'

import axios from "axios";
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

import { useState, useEffect } from "react";

 

function Product() {

    const [listeproducts, setProducts] = useState([]);

    const [isLoading, setIsLoading] = useState(true) //variable qui précise si ça charge ou pas

    const [isError, setIsError] = useState(false) // pour les erreurs

 

    useEffect(() => {
        
        try{
            axios.get('https://virtserver.swaggerhub.com/DJOUKUEDESMON_1/newgestock/1.0.0/product')

            .then(response => {

                setProducts(response.data);

                console.log(response.data)

                setIsLoading(false)

            })

            .catch(error => {

                console.log(error);

                setIsError(true)

                setIsLoading(false)

            });

        }catch(error){
              // Handle the error, e.g., display a user-friendly message
                 console.error("An error occurred swager:", error.message);
        }
        

    }, []);

    if (isLoading) {

        return (

            <div></div>

        )

    }

    if (isError) {

        return (

            <div></div>

        )

    }

 

    return (

        <section id="allproduct">
           {listeproducts.map((product) => (
            <article>
                <img src={product.photo}></img>
                <h3>{product.name}</h3>
                <li>price : {product.price}</li>
                <li>price : {product.category}</li>
                <li>stock: {product.stock}</li>
            </article>
           ))}

        </section>

    )

}

 

export default Product