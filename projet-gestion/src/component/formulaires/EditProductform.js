import React, { useState } from 'react';
import '../../style/addProductform.css';

const EditProductform = (props) => {
  const { currentEdit, action, actionclose } = props;

  const [valuEdit, setValuEdit] = useState({
    id: currentEdit.id,
    name: currentEdit.name,
    price: currentEdit.price,
    category: currentEdit.category,
    stock: currentEdit.stock,
    photo: currentEdit.photo,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setValuEdit({ ...valuEdit, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    action(valuEdit); // Pass the updated state to the action function
  };

  return (
    <form className='addproductform editproduct' onSubmit={handleSubmit}>
      <label htmlFor="name">Nom:</label>
      <input type="text" id="name" name="name" value={valuEdit.name} onChange={(e) => handleChange(e)} required />

      <label htmlFor="price">Prix:</label>
      <input type="number" min={5} id="price" name="price" value={valuEdit.price} onChange={(e) => handleChange(e)} required />

      <label htmlFor="category">Catégorie:</label>
      <input type="text" id="category" name="category" value={valuEdit.category} onChange={(e) => handleChange(e)} required />

      <label htmlFor="stock">Stock:</label>
      <input type="text" id="stock" name="stock" value={valuEdit.stock} onChange={(e) => handleChange(e)} required />

      <label htmlFor="photo">Photo URL:</label>
      <input type="text" id="photo" name="photo" value={valuEdit.photo} onChange={(e) => handleChange(e)} required />

      <div className='memelignelabel'>
        <button className='close' onClick={() => actionclose(false)}>Close</button>
        <input type="submit" value="Update" />
      </div>
    </form>
  );
};

export default EditProductform;
