import ProductsData from '../../assert/datajson/productTest.json';
import ProductForOrder from '../ProductForOder';
import Col from 'react-bootstrap/Col';

function MenuTestjson({addItems}) {
 
    console.log(ProductsData);
    return (
        <div className="menu">
            
            {ProductsData.map((product, index) => {
                return(     
                    <Col className='cartproduct'  key={product.id}  /* onClick={() => clickHandler(product)} */>
                        <ProductForOrder data={product}/>   
                        {/* {console.log(product)}                      */}
                    </Col>                                   
                )
            })} 
            
                     
        </div>
    )
}

export default MenuTestjson