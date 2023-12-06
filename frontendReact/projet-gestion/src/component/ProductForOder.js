//product for oder

import Card from 'react-bootstrap/Card';
import ProductItemForm from './ProductItemForm';

function ProductForOrder({data,cart}) { 
    
    return (
        <section className='card'>
           
            <Card.Img variant="top" className='centerimg' src={data.photo}/>
            
            <Card.Body>
              <Card.Title>{data.name}</Card.Title>
              
              <Card.Text>{data.price}€<span></span></Card.Text>
              <ProductItemForm product={data} />
              <div className="d-flex flex-row my-3">
            <div className="text-warning mb-1 me-2">
            </div>
            </div>
            </Card.Body>
        </section>)
    
    
        
    
}


export default ProductForOrder