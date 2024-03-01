import { createContext, useReducer } from 'react';

// création du context
export const purchaseContext = createContext({
  items: [],
  totalPrice: 0,
  addItem: () => {},
  updateCart: () => {},
  removeItem: () => {}
});

// création du reducer
const cartReducer = (state, action) => {
  switch (action.type) {
    case 'ADD_TO_CART':
        console.log("addItemToCart")
      const existingItem = state.items.find(item => item.id === action.payload.id);
      
      if (existingItem) {
        return {
          ...state,
          items: state.items.map(item => {
            if (item.id === action.payload.id) {
              
              console.log("existe 1");
              return {
                
                ...item
                
              };
            } else {
              //console.log("existe 2")
              return item;
            }
          }),
          totalPrice: state.totalPrice + (action.payload.price * action.payload.quantity),
         
        };
      } else {
        //console.log("existe else");
        return {
          ...state,
          items: [...state.items, action.payload],
          totalPrice: state.totalPrice + (action.payload.price * action.payload.quantity)
        };
      }
    case 'UPDATE_CART':
        const itemToUpdateIndex = state.items.findIndex(item => item.id === action.payload.id);
        const updatedItems = [...state.items];
        const quantityDiff = parseInt(action.payload.quantity) - parseInt(updatedItems[itemToUpdateIndex].quantity);
        const totalPriceDiff = quantityDiff * action.payload.price;
        updatedItems[itemToUpdateIndex] = {
          ...updatedItems[itemToUpdateIndex],
          quantity: String(parseInt(updatedItems[itemToUpdateIndex].quantity) + quantityDiff)
        };
        
        return {
          ...state,
          items: updatedItems,
          totalPrice: state.totalPrice + totalPriceDiff
        };
    case 'REMOVE_FROM_CART':
      const itemToRemoveIndex = state.items.findIndex(item => item.id === action.payload.id);
      const itemToRemove = state.items[itemToRemoveIndex];
      const updatedTotalPrice = state.totalPrice - (itemToRemove.price * itemToRemove.quantity);
      
      return {
        ...state, 
        
        items: [
          ...state.items.slice(0, itemToRemoveIndex),
          ...state.items.slice(itemToRemoveIndex + 1)
        ],
        totalPrice: updatedTotalPrice
        
      };
    default:
      return state;
  }
};

// création du provider
export const CartProvider = ({ children }) => {
  const [cart, dispatch] = useReducer(cartReducer, {
    items: [], //le state de base
    totalPrice: 0
  });

  // fonction pour ajouter un item au panier
  const addItemToCart = (id, name, price, quantity,images) => {
    const newItem = { id, name, price, quantity,images };
    dispatch({ type: 'ADD_TO_CART', payload: newItem });
    console.log("addItemToCart")
  };

  // fonction pour mettre à jour la quantité d'un item dans le panier
  const updateItemInCart = (id, name, price, quantity,images) => {
    const updatedItem = { id, name, price, quantity,images };
    dispatch({ type: 'UPDATE_CART', payload: updatedItem });//envoyer une action au reducer 
    console.log('update')
  };

  // fonction pour supprimer un item du panier
  const removeItemFromCart = (id, name, price, quantity,images) => {
    const itemToRemove = { id, name, price, quantity,images };
    dispatch({ type: 'REMOVE_FROM_CART', payload: itemToRemove });
    
  };


  // création de l'objet de context
  const contextValue = {
    items: cart.items,
    totalPrice: cart.totalPrice,
    addItem: addItemToCart,
    updateCart: updateItemInCart,
    removeItem: removeItemFromCart
  };


  return(<purchaseContext.Provider value={contextValue}>{children}</purchaseContext.Provider>);
}
export default CartProvider;
