//product 

import React from 'react'


import Col from 'react-bootstrap/Col';

import ProductForOrder from './ProductForOder';


 

function Product(props) {

    console.log('halllooooo',props.products);


    return (
        
        <section id="allproduct">
                
                {props.products.map((product, index) => {
                    return(     
                        <Col className='cartproduct'  key={product.id}  /* onClick={() => clickHandler(product)} */>
                            <ProductForOrder data={product}/>   
                           
                        </Col>                                   
                    )
                })} 
                
                         
        </section>
        )
   

}

 

export default Product