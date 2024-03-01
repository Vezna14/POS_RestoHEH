
import{useContext} from 'react'
import { purchaseContext } from "../context/Mycontext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
  import { faTimes } from '@fortawesome/free-solid-svg-icons'


function DeleteFromPurchase({ item }) {
    const { removeItem } = useContext(purchaseContext);
  
    const handleRemoveItem = () => {
      removeItem(item.id, item.prix, item.quantite);
    };
  
    return (
      <button className="cart-item-delete orderdisplayPrint" onClick={handleRemoveItem}>
         <FontAwesomeIcon icon={faTimes} />
      </button>
    );
  }
  
  export default DeleteFromPurchase;