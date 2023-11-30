
import Card from 'react-bootstrap/Card';
import ProductItemForm from './ProductItemForm';

function ProductForOrder(props) {    
    return (
        <section className='card'>
           
            <Card.Img variant="top" className='centerimg' src={props.data.img}/>
            
            <Card.Body>
              <Card.Title>{props.data.name}</Card.Title>
              
              <Card.Text>{props.data.price}€<span></span></Card.Text>
              <ProductItemForm product={props.data} cart={props.cart}/>
              <div className="d-flex flex-row my-3">
            <div className="text-warning mb-1 me-2">
            </div>
            </div>
            </Card.Body>
        </section>)
    
    
        
    
}

export default ProductForOrder