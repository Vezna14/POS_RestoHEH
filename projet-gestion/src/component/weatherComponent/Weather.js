import React, { useState, useEffect } from "react";
import axios from "axios";
import RecetteWeather from "./RecettesWeather";
import "../../style/Home.css";
import Card from "react-bootstrap/Card";

function Weather() {
  const [temperature, setTemperature] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [city, setCity] = useState("mons"); // Ajout de l'état pour la ville
  const [recommandations, setRecommandations] = useState([]);

  const apiUrl = process.env.REACT_APP_API_URL;
  useEffect(() => {
    // Remplacez 'YOUR_API_KEY' par votre clé d'API OpenWeatherMap
    const apiKey = "0b46c6f7971264a4fbfd44c39d6477d1";
    const city = "mons"; // Remplacez par la ville de votre choix
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&unit=Metric&appid=${apiKey}`;
    //https://api.openweathermap.org/data/2.5/weather?q='mons'&appid='0b46c6f7971264a4fbfd44c39d6477d1'

    try {
      axios
        .get(apiUrl)
        .then((response) => {
          console.log(response.data);
          setTemperature(response.data.main.temp); //(response.data.main.temp)
          setLoading(false);
        })
        .catch((err) => {
          setError(err.message);
          setLoading(false);
        });
    } catch (error) {
      console.error("An error occurred:", error.message);
    }
  }, []);

  useEffect(() => {
    try {
      axios
        .get(`${apiUrl}/recommandations`)
        .then((response) => {
          setRecommandations(response.data);
          console.log(response.data);
          setLoading(false);
        })
        .catch((error) => {
          console.log(error);
          setError(true);
          setLoading(false);
        });
    } catch (error) {
      // Handle the error, e.g., display a user-friendly message
      console.error("An error occurred BACKEND:", error.message);
    }
  }, []);

  if (loading) {
    return <div>Chargement...</div>;
  }

  if (error) {
    return <div>Une erreur s'est produite : {error}</div>;
  }

  return (
    <div >
      <h2>Température actuelle à :</h2>
      <p>{(temperature - 273.15).toFixed(2)}°C</p>
      <section style={{ display:'flex', justifyContent: 'center', flexWrap: 'wrap', width:'100%' }}>
        {recommandations.map((product, index) => {
          if (product.stock > 0) {
            return (
              <div
                className="cartproduct"
                key={product.id} /* onClick={() => clickHandler(product)} */
              >
                <section className="card orderdisplayPrint">
                  <Card.Img
                    variant="top"
                    className="centerimg"
                    src={product.photo}
                  />

                  <Card.Body>
                    <Card.Title className="plat-name">
                      {product.name}
                    </Card.Title>

                    <Card.Text>
                      {product.price}€<span></span>
                    </Card.Text>


                  </Card.Body>
                </section>
              </div>
            );
          }
        })}
      </section>
    </div>
  );
}

export default Weather;
