
import './App.css';
import './style/Home.css';
import './style/Head.css';
import Header from './component/Header';
import Product from './component/Product';
import Weather from './component/weatherComponent/Weather';
import OrderPurchase from './component/OrderPurchase';
import MenuTestjson from './component/Testdatacomponent/MenuTestjson';
import axios from 'axios';
import { useState,useEffect } from 'react';
import CartProvider from './context/Mycontext';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ChangeCart from './component/ChangeCart';
function App() {
  const [ticketItems, setTicketItems] = useState([]);
  const [listeproducts, setProducts] = useState([]);

  const [isLoading, setIsLoading] = useState(true) //variable qui précise si ça charge ou pas

  const [isError, setIsError] = useState(false) // pour les erreurs



  useEffect(() => {
        
    try{
        axios.get('http://localhost:8080/products')

        .then(response => {

            setProducts(response.data);

            console.log(response.data)

            setIsLoading(false)

        })

        .catch(error => {

            console.log(error);

            setIsError(true)

            setIsLoading(false)

        });

    }catch(error){
          // Handle the error, e.g., display a user-friendly message
             console.error("An error occurred BACKEND:", error.message);
    }
    

}, []);

if (isLoading) {

    return (

        <div>loading....</div>

    )

}

if (isError) {

    return (

        <div> error fetching data</div>

    )

}


 
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={
            <>
              <Header/>
              <Weather/>
            </>
          }/>
          <Route path="/MenuTestjson" element={
            <>
              <Header/>
              <div id="newOrderpro">
                <CartProvider>
                  
                  <OrderPurchase />
                  
                  <Product products={listeproducts}/>

                </CartProvider>
                
              </div>
            </>
          }/>
        <Route path='/UpdateStock' element={
           <>
           <Header/>
           <div id="newOrder">
             <CartProvider>
               <ChangeCart products={listeproducts}/>
             </CartProvider>
             
           </div>
         </>

        } />  
        <Route path='*' element={<p>non non tu ne peut pas</p>} />
            
          
        </Routes>
      </BrowserRouter>
      
    </div>
  );
}

export default App;
