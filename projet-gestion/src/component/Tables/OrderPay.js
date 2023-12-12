//import

import axios from "axios";


function OrderPay (props){
    console.log(props.data)
    
    const avoid= () =>{
        props.avoid(false);
    }
    
    
        const handlePrint =(id) =>{
            try {
              axios.post(`http://localhost:8080/resto/orders/pay/${id}`)
              window.print();
              
        
            } catch (error) {
             
              console.error("Error sending request:", error);
            }
          };
        
       
    
   
  
    return(
        <div className="orderpurchase">
            <div className="purchase">
                <h2>bon de commande </h2>
                <h5> N°de table:{props.data.idTable} ..... à{props.data.date}</h5> 
                    <table>
                        <thead>
                            <tr>
                                <th>ref</th>
                                <th>Description</th>
                                <th>Qty</th>
                                <th>Price</th>
                              
                                
                            </tr>
                        </thead>   
                        <tbody>            
                        {props.data.productList.map((item) => (                            
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.name}</td>
                                <td>{item.quantity}</td>
                                <td>{item.price}</td>
                                
                            </tr>                            
                        ))}                                            
                        </tbody>                     
                    </table>
                </div>

                <div className="subtotal">
                    <hr/>   
                    <div className="subtotal__price">                                                      
                        Subtotal: {props.data.totalPrice}€
                    </div>                                     
                    <hr/>                    
                </div>

            
            <div className="actions">
                <button className="pay" onClick={() => handlePrint(props.data.id)}>pay</button>
                         
                <button className="avoid" onClick={() => avoid()}>avoid</button>
            </div>

        </div>
    )
}
export default OrderPay