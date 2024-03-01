import axios from "axios";
import { useState } from "react";
import '../../style/EditProductform.css';

const apiUrl = process.env.REACT_APP_API_URL;


const AddTableForm = (props) => {
    const [newTable, setNewTable] = useState({
      id: 0,
      name: "",
      status:"Available",
      seats: 0,
      reserved: false,
    });
  
    const handleChange = (e) => {
      const { name, value } = e.target;
      setNewTable((prevTable) => ({
        ...prevTable,
        [name]: value,
      }));
    };
  
    const handleCreateTable = async (e)=> {
        e.preventDefault();
        try { 
            await axios.post(`${apiUrl}/tableRestos`, newTable);
            console.log('envoie ..... !');

             // Réinitialiser les champs du formulaire
          setNewTable({
            id: 0,
            name: "",
            status:"Available",
            seats: 0,
            reserved: false,
          });
          props.handleCloseModal();
          console.log('Données envoyées avec succès!');

        } catch (error) {
            console.error('Erreur lors de l\'envoi des données:', error.message);
          }
     
        
        const handleClose= () => {
            props.handleCloseModal();

        }
    };
  
    return (
      <div className="modal modalEdith">
        {/* Contenu du formulaire */}
        <label htmlFor="tableName">Nom de la table:</label>
        <input
          type="text"
          id="tableName"
          name="name"
          value={newTable.name}
          onChange={handleChange}
        />
  
        <label htmlFor="tableSeats">Nombre de places:</label>
        <input
          type="number"
          id="tableSeats"
          name="seats"
          value={newTable.seats}
          onChange={handleChange}
        />
        <div className="memeligne">
            <button onClick={handleCreateTable}>Créer la table</button>
            <button onClick={props.handleCloseModal}>Fermer</button>

        </div>
  
        
      </div>
    );
  }

  export default AddTableForm;