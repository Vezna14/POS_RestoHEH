import React from 'react';
import '../../style/addProductform.css';

const AddProductform = (props) => {
  const { formData, action, action1, actionclose } = props;

  const handleSubmit = (e) => {
    e.preventDefault();
    action(e); // Appel de la fonction handleSubmit définie dans ChangeCart
  };

  return (
    <form className='addproductform' onSubmit={handleSubmit}>
      <h3>Niam niam New Dish</h3>

      
        <label htmlFor="name">Nom:</label>
        <input type="text" id="name" name="name" value={formData.name} onChange={action1} required />
  

    
        <label htmlFor="price">Prix:</label>
        <input type="number" min={5} id="price" name="price" value={formData.price} onChange={action1} required />


      
        <label htmlFor="category">Catégorie:</label>
        <input type="text" id="category" name="category" value={formData.category} onChange={action1} required />


    
        <label htmlFor="stock">Stock:</label>
        <input type="text" id="stock" name="stock" value={formData.stock} onChange={action1} required />
  

   
        <label htmlFor="photo">Photo URL:</label>
        <input type="text" id="photo" name="photo" value={formData.photo} onChange={action1} required />
 

      <div className='memelignelabel'>
        <button className='close' onClick={() => actionclose(false)}>Close</button>
        <input type="submit" value="Créer" />
      </div>
    </form>
  );
};

export default AddProductform;
