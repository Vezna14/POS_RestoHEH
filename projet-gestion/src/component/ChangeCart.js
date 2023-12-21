import React, { useState } from "react";
import Modal from 'react-modal';
import axios from "axios";

import jsonData from '../assert/datajson/platsmeteo.json';
import trash from '../assert/svg/trash.svg';
import pencil from '../assert/svg/pencil.svg';
import add from '../assert/svg/add.svg';
import '../style/ChangeCart.css'; // Assurez-vous d'ajuster le chemin selon votre structure de fichiers
import AddProductform from "./formulaires/AddProductform";
import EditProductform from "./formulaires/EditProductform";
const apiUrl = process.env.REACT_APP_API_URL;

const ChangeCart = (props) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isDel, setIsDel] = useState(false);
  const [currentEdit, setCurrentEdit] = useState();



  /* add form gestion */
  const [formData, setFormData] = useState({
    id: '',
    name: '',
    price: '',
    category: '',
    stock: '',
    photo: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Convertir la chaîne de catégories en tableau
      const categoryArray = formData.category.split(',');

      // Envoi des données à localhost:8080 (ajuste l'URL selon tes besoins)
      await axios.post(`${apiUrl}/product`, {
        name: formData.name,
        price: formData.price,
        category: categoryArray,
        stock: formData.stock,
        photo: formData.photo

      });
      

      // Réinitialiser le formulaire après l'envoi
      setFormData({
        name: '',
        price: '',
        category: '',
        stock: '',
        photo: '',
      });

      // Fermer le formulaire après l'envoi (ajuste en fonction de ta logique)
      setIsModalOpen(false);

      console.log('Données envoyées avec succès!');
    } catch (error) {
      console.error('Erreur lors de l\'envoi des données:', error.message);
    }
  };
  /***************************************** */

  const handleAdd = () => {
    setIsModalOpen(true);
    setIsEditModalOpen(false);
    setIsDel(false);
  };

  const handleEdit = (item) => {

    //requete axios

    setCurrentEdit(item);
    setIsModalOpen(false);
    setIsEditModalOpen(true);
    setIsDel(false);
  };

  return (
    <div className="change-cart-container orderdisplayPrint">
      <div className="addbut" onClick={handleAdd}>
        <img src={add} alt="add Item" className="changeicone" />
        <span>Ajoutez un Plat</span>
      </div>

      <table id="tabplat" className="responsive-table">
        <thead >
          <tr >
            <th>Reference</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {props.products.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.price}</td>
              <td>{item.stock}</td>
              <td>
                <div>
                  <img src={trash} alt="Delete Item" onClick={() => setIsDel(true)} className="changeicone" />
                  <img src={pencil} alt="Edit Item" onClick={() => handleEdit(item)} className="changeicone" />
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <Modal
        isOpen={isModalOpen}
        onRequestClose={() => setIsModalOpen(false)}
        contentLabel="Add Item Modal"
        className="modal"
        overlayClassName="overlay"
      >
        <AddProductform formData={formData} action={handleSubmit} actionclose={setIsEditModalOpen} action1={handleChange}/>
       
      </Modal>

      <Modal
        isOpen={isEditModalOpen}
        onRequestClose={() => setIsEditModalOpen(false)}
        contentLabel="Edit Item Modal"
        className="modal"
        overlayClassName="overlay"
      >
        <h3 className="edit">Mise à jour des aliments</h3>
        <EditProductform currentEdit={currentEdit} actionclose={setIsEditModalOpen} />
       
      </Modal>

    </div>
  );
}

export default ChangeCart;
