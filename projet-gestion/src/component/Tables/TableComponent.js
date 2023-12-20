import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Table.css';
import { useNavigate } from 'react-router-dom';
import AddTableForm from '../formulaires/AddTableForm';
import trash from '../../assert/svg/trash.svg';
import pencil from '../../assert/svg/pencil.svg';
import OrderPay from './OrderPay';


const TableComponent = (props) => {

  const [tables, setTables] = useState([]);
  const [selectedTable, setSelectedTableLocal] = useState(null);
  const [isLoading, setIsLoading] = useState(true) //variable qui précise si ça charge ou pas
  const [showModal, setShowModal] = useState(false);
  const [isError, setIsError] = useState(false); // pour les erreurs
  const [isOccuped, setIsOccuped] = useState(false) ;
  const [showPay ,setShowPay] = useState(false);
  const [dataOrder ,setDataOrder] = useState(null);

  const navigate = useNavigate();


  const handlePrint =(id) =>{
    try {
      axios.get(`http://localhost:8080/resto/orders/show/${id}`)
        .then(response => {
          setShowPay(true)
          setDataOrder(response.data);
         
          console.log(response.data);

        
  
        })
        .catch(error => {
        
          console.error("Error fetching order details:", error);
        });
    } catch (error) {
     
      console.error("Error sending request:", error);
    }
  };


  const handleTableClick = (table) => {
   
    setSelectedTableLocal(table);
    props.setSelectedTable(table); // Mettez à jour le state dans le composant parent
    navigate(`/MenuTestjson?table=${table.name}&time=${new Date().toLocaleTimeString()}`);
  };
  const handleAddTable = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  //delete table
  const handleDeleteTable = (id) => {
    if(props.selectedTable?.status =="available"){
      axios.delete(`http://localhost:8080/deleteTable/${id}`)
      .then(response => {
          // Mettez à jour l'état des tables après la suppression réussie
          setTables(tables.filter(table => table.id !== id));
          console.log(response.data);
      })
      .catch(error => {
          console.error(error);
      });

    }else{
      alert("cette table n'as pas encore payée")
    }
   
};
const chkTableOccupped=()=>{
  if(props.selectedTable?.status =="occupied" ||props.selectedTable?.status =="Occupied" ){
    console.log("statussssssss",props.selectedTable?.status )
    setIsOccuped(true);
  } else{
    console.log("statusss else ssss",props.selectedTable?.status )
    setIsOccuped(false);}
}

    // Charger les données depuis le fichier backend table
    useEffect(() => {
        
      try{
          axios.get('http://localhost:8080/tableRestos')
  
          .then(response => {
  
            setTables(response.data);
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
      chkTableOccupped();
    }, [props.selectedTable]);
   
    const handleReleaseTable = (tableId) => {
     
      props.markTableOccupied(tableId);
      chkTableOccupped();
    };
    if (isLoading) {return (<div>loading....</div>)}
  
  if (isError) {return ( <div> error fetching data</div> )}
 
    

  return (
    <div>
    <div className="table-container orderdisplayPrint">
      <div className="table-scroll">
        {tables.map((table, index) => (
          <div key={index}>
            <div
              className={`table ${table.selected ? 'selected' : ''}` }
              onClick={() => {if (!(table.status === "occupied" || table.status === "Occupied")) {
                handleTableClick(table);
              }
            }}
          >
            
              <p className='fontblack'>{table.name} </p>
              <p className={table.status}>{table.status}</p>
            </div>
            <div>
                <img src={trash} alt="Delete Item" onClick={() => handleDeleteTable(table.id)}   className="changeicone" />
                <button className={table.status} disabled={!(table.status =="occupied" ||table.status =="Occupied")} onClick={() =>handleReleaseTable(table.id)}>Libérer</button>
                {table.status =='occupied' &&<button className="pay" onClick={() => handlePrint(table.id)}>pay</button>}

                

            </div>
            
          </div>
        ))}
        <div className="add-table" onClick={handleAddTable}>
          <p >+</p>
        </div>
      </div>
      {showModal && <AddTableForm handleCloseModal={handleCloseModal} showModal={showModal} setShowModal={setShowModal}/>}
      
    </div>
    {showPay && <OrderPay data={dataOrder} avoid={setShowPay} setIsOccuped={setIsOccuped} />}

    </div>
    
  );
};

export default TableComponent;
