import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './Table.css';
import tablesdata from './Table.json';
import { useNavigate } from 'react-router-dom';

const TableComponent = (props) => {
  const [tables, setTables] = useState([]);
  const [selectedTable, setSelectedTableLocal] = useState(null);
  const navigate = useNavigate();

  const handleTableClick = (table) => {
    setSelectedTableLocal(table);
    props.setSelectedTable(table); // Mettez à jour le state dans le composant parent
    navigate(`/MenuTestjson?table=${table.name}&time=${new Date().toLocaleTimeString()}`);
  };



    // Charger les données depuis le fichier backend table
   
    const handleReleaseTable = () => {
      // Ajoutez ici le code pour libérer la table (utilisez la fonction de libération de table, par exemple)
      props.releaseTable();
    };
    

  return (
    
    <div className="table-container">
      <div className="table-scroll">
        {tablesdata.map((table, index) => (
          <div>
          <div
            key={index}
            className={`table ${table.selected ? 'selected' : ''}`}
            onClick={() => handleTableClick(table)}
          >
            <p>Places: {table.name} </p>
            <p>places : {table.seats}</p>

          </div>
          <button onClick={handleReleaseTable}>Libérer la table</button>
          </div>
        ))}
        <div className="add-table">
          <p>+</p>
        </div>
      </div>
    </div>
  );
};

export default TableComponent;
