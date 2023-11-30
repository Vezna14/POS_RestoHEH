
import './App.css';
import './style/Home.css';
import './style/Head.css';
import Header from './component/Header';
import Product from './component/Product';
import Weather from './component/weatherComponent/Weather';
import OrderPurchase from './component/OrderPurchase';
import MenuTestjson from './component/Testdatacomponent/MenuTestjson';
import { useState } from 'react';
import CartProvider from './context/Mycontext';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ChangeCart from './component/ChangeCart';
function App() {
  const [ticketItems, setTicketItems] = useState([]);

 
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
                  <MenuTestjson/>
                  <Product/>

                </CartProvider>
                
              </div>
            </>
          }/>
        <Route path='/UpdateStock' element={
           <>
           <Header/>
           <div id="newOrder">
             <CartProvider>
               <ChangeCart/>
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
