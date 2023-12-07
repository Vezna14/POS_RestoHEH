//import
import { useContext } from "react";
import DeleteFromPurchase from "./DeleteFromPurchase";
import { purchaseContext } from "../context/Mycontext";

function OrderPurchase (props){
    // var total = products.reduce((amount, item) => (amount+parseInt(item.price)),0);
    const purchasectx = useContext(purchaseContext);


    const handlePrint = () => {
        props.markTableOccupied(props.selectedTable?.id);
        window.print();
    }
    const handleSave = ()=>{
        props.markTableOccupied(props.selectedTable?.id);
        console.log(props.selectedTable?.id)
    }
  
    return(
        <div className="orderpurchase">
            <div className="purchase">
                <h2>bon de commande </h2>
                <h5> N°de table:{props.selectedTable?.name} ..... à{new Date().toLocaleTimeString()}</h5> 
                    <table>
                        <thead>
                            <tr>
                                <th>ref</th>
                                <th>Description</th>
                                <th>Qty</th>
                                <th>Price</th>
                                <th>action</th>
                                
                            </tr>
                        </thead>   
                        <tbody>            
                        {purchasectx.items.map((item) => (                            
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.name}</td>
                                <td>{item.quantity}</td>
                                <td>{item.price}</td>
                                <td className="remove-icon"><DeleteFromPurchase item={item}/></td>
                            </tr>                            
                        ))}                                            
                        </tbody>                     
                    </table>
                </div>

                <div className="subtotal">
                    <hr/>   
                    <div className="subtotal__price">                                                      
                        Subtotal: {purchasectx.totalPrice}€
                    </div>                                     
                    <hr/>                    
                </div>

            
            <div className="actions">
                <button onClick={() => handlePrint()}>pay</button>
                <button onClick={() =>  handleSave()}>save </button>            
                <button >avoid</button>
            </div>

        </div>
    )
}
export default OrderPurchase