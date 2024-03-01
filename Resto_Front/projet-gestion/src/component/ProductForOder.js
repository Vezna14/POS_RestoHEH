//product for oder

import Card from 'react-bootstrap/Card';
import ProductItemForm from './ProductItemForm';

function ProductForOrder({data,cart}) { 
    
    return (
        <section className="card orderdisplayPrint">
           
            <Card.Img variant="top" className='centerimg' src={data.photo}/>
            
            <Card.Body>
              <Card.Title className='plat-name'>{data.name}</Card.Title>
              
              <Card.Text>{data.price}€<span></span></Card.Text>
              <ProductItemForm className="orderdisplayPrint" product={data} />
              <div className="d-flex flex-row my-3">
            <div className="text-warning mb-1 me-2">
            </div>
            </div>
            </Card.Body>
        </section>)
    
    
        
    
}


export default ProductForOrder