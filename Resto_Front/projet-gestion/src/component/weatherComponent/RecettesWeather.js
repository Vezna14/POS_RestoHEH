import React from 'react';
import jsonData from '../../assert/datajson/platsmeteo.json';



function RecetteWeather(props) {
    const temperature = props.temp;
    console.log(temperature);

  // Filtrer les recettes en fonction de la température
  const recettesFiltrees = jsonData.filter((recette) => {
    return temperature >= recette.minTemperature && temperature <= recette.maxTemperature;
    
  });
  console.log(recettesFiltrees);
  return (
    <section id='platsmeteo'>
      
        {recettesFiltrees.map((recette) => (
          <div key={recette.id} style={{ backgroundImage: `url(${recette.image})` }}>
            <h3>{recette.name}</h3>
            
          </div>
        ))}
      
    </section>
  );
}

export default RecetteWeather;