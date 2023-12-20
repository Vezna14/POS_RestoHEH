import { useContext,useState,useRef } from 'react';
import Button from 'react-bootstrap/Button';
import { purchaseContext } from '../context/Mycontext';


function ProductItemForm({product}) {
     // réference à l'input
    const inputRef = useRef(null);
    
    const [isInputEmpty, setIsInputEmpty] = useState(true);
    const [quantity, setQuantity] = useState(0);
    
  
    
    // accès au contexte CartContext
    const ctx = useContext(purchaseContext);

    const handleInputChange = () => {
      const inputValue = inputRef.current.value;
      setIsInputEmpty(inputValue === "");
       // méthode pour ajouter au panier
    //const handleAddToCart = () => {    // commenter pour securiser les champs
      setQuantity(parseInt(inputValue));
      if(parseInt(quantity)<=0 ){
        setQuantity(1);
      }
    };
    const handleAdd = () => {  
      const existingItem = ctx.items.find(item => item.id === product.id);
      
      if (existingItem) {
        // si l'article existe déjà, mettre à jour la quantité
        ctx.updateCart(product.id, product.name, product.price ,  parseInt(existingItem.quantity) + parseInt(quantity), product.images);
        
      } else {
        // sinon, ajouter le nouvel article au panier
        ctx.addItem(product.id, product.name, product.price,quantity, product.images);
      }
    };
   // }; 
   


  
   
  
    return (
      <form className='productquantityform orderdisplayPrint'>
        <input className="number"  type="number" min={1}  onChange={handleInputChange} ref={inputRef} ></input>
        <Button id='btnadd' variant="primary" disabled={isInputEmpty} onClick={handleAdd}>ADD</Button>
      </form>
    );
  }
  
  export default ProductItemForm;