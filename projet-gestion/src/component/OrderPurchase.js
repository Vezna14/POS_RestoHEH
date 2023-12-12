//import
import { useContext,useState,useEffect} from "react";
import DeleteFromPurchase from "./DeleteFromPurchase";
import { purchaseContext } from "../context/Mycontext";
import axios from "axios";

function OrderPurchase (props){
    // var total = products.reduce((amount, item) => (amount+parseInt(item.price)),0);
    const purchasectx = useContext(purchaseContext);
    const [tableContents, setTableContents] = useState(purchasectx.items);
    console.log(tableContents)
    
    const handlePrint = () => {
        props.markTableOccupied(props.selectedTable?.id);
        window.print();
    }
    const handleSave =async () => {
       

        const data = {
            idTable: props.selectedTable?.id,
            productList: tableContents,
            isPaid: false,
            totalPrice: purchasectx.totalPrice,
            date:new Date().toLocaleTimeString()
          };
          console.log('datas:',data)

          try {
            // Make a POST request to save the order
            await axios.post('http://localhost:8080/resto/orders', data);
            
            props.markTableOccupied(props.selectedTable?.id);
            console.log('datas:',data)
        }catch (error) {
            console.error("Error saving order:", error);
        }
        
    }
    useEffect(() => {
        // Update tableContents when purchasectx.items changes
        setTableContents(purchasectx.items);
    }, [purchasectx.items]);
  
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
                <button className="pay" onClick={() => handlePrint()}>pay</button>
                <button className="pay" onClick={() =>  handleSave()}>save </button>            
                <button className="avoid">avoid</button>
            </div>

        </div>
    )
}
export default OrderPurchase