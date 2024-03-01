//import
import { useContext,useState,useEffect} from "react";
import DeleteFromPurchase from "./DeleteFromPurchase";
import { purchaseContext } from "../context/Mycontext";
import axios from "axios";
const apiUrl = process.env.REACT_APP_API_URL;


function OrderPurchase (props){
    // var total = products.reduce((amount, item) => (amount+parseInt(item.price)),0);
    const purchasectx = useContext(purchaseContext);
    const [tableContents, setTableContents] = useState(purchasectx.items);
    console.log(tableContents)

    
  //pay orders  
    const handlePrint = async() => {
        
        const data = {
            idTable: props.selectedTable?.id,
            productList: tableContents,
            isPaid: false,
            totalPrice: purchasectx.totalPrice,
            date:new Date().toLocaleTimeString()
          };
        
        try {
            if(tableContents.length > 0){
                const response = await axios.post(`${apiUrl}/resto/orders/pay`,data);
                console.log(response.data);
            
                window.print();
                if (response.status === 200) {
                    // Succès
                    alert('Commande payée avec succès');
                  } else if (response.status === 400) {
                    // Stock insuffisant
                    alert('Erreur : ' + response.data);
                  } else {
                    // Gérez d'autres codes de statut au besoin
                  }

            }
            else{
                alert("veillez encoder une commande")
            }
           
        } catch (error) {
            
            console.error('Error:', error);
        }
        
        
    }

    
    //avoid order
    const handleAvoid =() =>{
        setTableContents(null)
        props.setSelectedTable(false)

    }
    //save Order
    const handleSave =async () => {
       console.log('taille order',tableContents.length)
        if(tableContents.length > 0){
            console.log('taille order z',tableContents.length)
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
                await axios.post(`${apiUrl}/resto/orders`, data);
                
                props.markTableOccupied(props.selectedTable?.id);
                console.log('datas:',data)
            }catch (error) {
                console.error("Error saving order:", error);
            }
        }
        else{
          alert("cette commande est vide !!!!!")

        }
            
       
        
        
    }
    useEffect(() => {
        // Update tableContents when purchasectx.items changes
        setTableContents(purchasectx.items);
    }, [purchasectx.items]);
  
    return(
        <div className="orderpurchase">
            <div className="purchase">
                <h2>Bon de commande </h2>
                <h5> N°de table: {props.selectedTable?.name} ..... à {new Date().toLocaleTimeString()}</h5> 
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
                        {tableContents.map((item) => (                            
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

            
            <div className="actions orderdisplayPrint">
                <button className="pay" onClick={() => handlePrint()}>pay</button>
                <button className="pay" onClick={() =>  handleSave()}>save </button>            
                <button className="avoid"onClick={() =>  handleAvoid()}>avoid</button>
            </div>

        </div>
    )
}
export default OrderPurchase