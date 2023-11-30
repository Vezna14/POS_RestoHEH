import React, { useState } from "react";
import Modal from 'react-modal';


import jsonData from '../assert/datajson/platsmeteo.json';
import trash from '../assert/svg/trash.svg';
import pencil from '../assert/svg/pencil.svg';
import add from '../assert/svg/add.svg';


import '../style/ChangeCart.css'; // Assurez-vous d'ajuster le chemin selon votre structure de fichiers

const ChangeCart = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isDel, setIsDel] = useState(false);

  const recettesFiltrees = jsonData.filter((recette) => recette);

  const handleAdd = () => {
    setIsModalOpen(true);
    setIsEditModalOpen(false);
    setIsDel(false);
  };

  const handleEdit = () => {
    setIsModalOpen(false);
    setIsEditModalOpen(true);
    setIsDel(false);
  };

  return (
    <div className="change-cart-container">
      <div className="addbut" onClick={handleAdd}>
        <img src={add} alt="add Item" className="changeicone" />
        <span>Ajoutez un Plat</span>
      </div>

      <table className="responsive-table">
        <thead>
          <tr>
            <th>Reference</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {recettesFiltrees.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.price}</td>
              <td>5</td>
              <td>
                <div>
                  <img src={trash} alt="Delete Item" onClick={() => setIsDel(true)} className="changeicone" />
                  <img src={pencil} alt="Edit Item" onClick={handleEdit} className="changeicone" />
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
        <h2>Add Item Form</h2>
        <form>
          <label htmlFor="dishName">Dish Name:</label>
          <input type="text" id="dishName" name="dishName" />

          <label htmlFor="quantity">Quantity:</label>
          <input type="number" id="quantity" name="quantity" />

          {/* Add more form fields as needed */}

          <button type="submit">Submit</button>
        </form>
        <button onClick={() => setIsModalOpen(false)}>Close</button>
      </Modal>

      <Modal
        isOpen={isEditModalOpen}
        onRequestClose={() => setIsEditModalOpen(false)}
        contentLabel="Edit Item Modal"
        className="modal"
        overlayClassName="overlay"
      >
        <h2>Edit Item Form</h2>
        <form>
          <label htmlFor="selectedDish">Select Dish:</label>
          <select id="selectedDish" name="selectedDish">
            <option value="dish1">plat 1</option>
            <option value="dish2">plat 2</option>
            {/* Add more options as needed */}
          </select>

          <label htmlFor="newQuantity">New Quantity:</label>
          <input type="number" id="newQuantity" name="newQuantity" />

          {/* Add more form fields as needed */}

          <button type="submit">Submit</button>
        </form>
        <button onClick={() => setIsEditModalOpen(false)}>Close</button>
      </Modal>

    </div>
  );
}

export default ChangeCart;
