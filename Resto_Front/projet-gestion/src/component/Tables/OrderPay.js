//import

import axios from "axios";
import '../../style/Home.css';

const apiUrl = process.env.REACT_APP_API_URL;


function OrderPay (props){
    console.log(props.data)
    
    const avoid= () =>{
        props.avoid(false);
    }

    const markTableStatus = (tableId) => {
        try {
            // Envoyer une requête PUT pour marquer la table comme occupée
             axios.put(`${apiUrl}/toggleStatus/${tableId}`)
            .then(response =>{
                console.log(response)

            });
          //  props.handleReleaseTable(tableId)
            
            console.log('Table marquée comme occupée avec succès!');
        } catch (error) {
            console.error('Erreur lors de la mise à jour du statut de la table:', error.message);
        }
    };
    

    const handlePrint = (id) => {
        try {
          axios.put(`${apiUrl}/resto/orders/pay/${id}`)
            .then(response => {
              if (response.status === 201) {
                console.log('Order payment successful. Marking table as occupied...');
               //markTableStatus(id);
                console.log('Table marked as occupied successfully!');
                props.setIsOccuped(false);
                avoid();
              }
              window.print();
            })
            .catch(error => {
              console.error("Error in request:", error);
            });
        } catch (error) {
          console.error("Unexpected error:", error);
        }
      };
    
        
       
    
   
  
    return(
        <div className="orderpurchase orderdisplayPrint " id="facture">
            <div className="purchase">
                <h2>Facture </h2>
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