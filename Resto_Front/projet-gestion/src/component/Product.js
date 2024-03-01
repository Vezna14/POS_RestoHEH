//product 

import React from 'react'
import ProductForOrder from './ProductForOder';


function Product(props) {


    return (
        
        <section id="allproduct" className="orderdisplayPrint" >
                
                {props.products.map((product, index) => {
                    if(product.stock>0){
                        return(     
                            <div className='cartproduct'  key={product.id}  /* onClick={() => clickHandler(product)} */>
                                <ProductForOrder className="orderdisplayPrint" data={product}/>   
                               
                            </div>                                   
                        )

                    }
                    
                })} 
                
                         
        </section>
        )
   

}

 

export default Product