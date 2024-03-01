
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
import TableComponent from './component/Tables/TableComponent';
const apiUrl = process.env.REACT_APP_API_URL;

function App() {

  //table
  const [tableStatus, setTableStatus] = useState({});
  const [selectedTable, setSelectedTable] = useState(false);
  


const markTableOccupied = async (tableId) => {
    try {
        // Envoyer une requête PUT pour marquer la table comme occupée
        await axios.put(`${apiUrl}/toggleStatus/${tableId}`);
        
        // Mettre à jour l'état de la table pour marquer la table comme occupée
        setSelectedTable(false);
        console.log('Table marquée comme occupée avec succès!');
    } catch (error) {
        console.error('Erreur lors de la mise à jour du statut de la table:', error.message);
    }
};

  
  const releaseTable = () => {
    // Mettez à jour l'état de la table pour libérer la table
    
    setSelectedTable(false)
  };


  //****************** */
  const [ticketItems, setTicketItems] = useState([]);
  const [listeproducts, setProducts] = useState([]);

  const [isLoading, setIsLoading] = useState(true) //variable qui précise si ça charge ou pas

  const [isError, setIsError] = useState(false) // pour les erreurs



  useEffect(() => {
        
    try{
        axios.get(`${apiUrl}/products`)

        .then(response => {

            setProducts(response.data);


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
    

}, [listeproducts]);

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
              <TableComponent releaseTable={releaseTable} setSelectedTable={setSelectedTable} selectedTable={selectedTable} markTableOccupied={markTableOccupied}  />

              {selectedTable && (
              <div id="newOrderpro">
                <CartProvider>
               
                  
                  <OrderPurchase markTableOccupied={markTableOccupied} setSelectedTable={setSelectedTable} selectedTable={selectedTable} />
                  
                  <Product className="orderdisplayPrint" products={listeproducts}/>

               

                </CartProvider>
                
              </div>
                 )}
            </>
          }/>
        <Route path='/UpdateStock' element={
           <>
           <Header/>
           
          
            <div id="newOrder">
              <CartProvider>
                <ChangeCart setProducts={setProducts} products={listeproducts}/>
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
