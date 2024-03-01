
import trash from '../store/trash.svg'
import pencil from '../store/pencil.svg'
import '../style/Home.css';

function CartItem({ item }) {


  const handleRemoveItem = () => {
    
  };

  return (
    <div>
        <button className="cart-item-delete orderdisplayPrint">
            <img src={trash} alt="Delete Item" className="changeicone" />
        </button>
        <button className="cart-item-delete">
            <img src={pencil} alt="Delete Item" className="changeicone" />
        </button>

    </div>
  );
}

export default CartItem;