
import{useContext} from 'react'
import { purchaseContext } from "../context/Mycontext";


function DeleteFromPurchase({ item }) {
    const { removeItem } = useContext(purchaseContext);
  
    const handleRemoveItem = () => {
      removeItem(item.id, item.prix, item.quantite);
    };
  
    return (
      <button className="cart-item-delete orderdisplayPrint" onClick={handleRemoveItem}>
        <i class="fa fa-times" aria-hidden="true"></i>
      </button>
    );
  }
  
  export default DeleteFromPurchase;