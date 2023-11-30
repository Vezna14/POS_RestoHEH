
import {Link} from 'react-router-dom';
import React, { useState } from 'react';
import menu from  '../assert/svg/menu.svg';
import '../style/Home.css';

function Header() {
    const [isBurgerOpen, setIsBurgerOpen] = useState(false);

    const handleBurgerClick = () => {
        setIsBurgerOpen(!isBurgerOpen);
    };


    return(
        <header>
            <nav id="idhead">
                <a href='#'><h1>LaBouffe</h1></a>
                <form>
                    <input type='search'></input>
                </form>
                <a><i className="fa fa-user"><span>username</span></i></a>
            
            </nav>
            <div className="burger-icon" onClick={handleBurgerClick} >
                   <img src={menu} alt='menu burger'/>
                </div>
            <nav id="idnavdiv" >
           
               
                <div  className={`burger-links ${isBurgerOpen ? 'open' : ''}`} >
                    <Link to={"/"} className='color1'>Home</Link>
                    <Link className='color1' to={"/MenuTestjson"}>Make New Order</Link>
                    <Link className='color1' to={'/UpdateStock'}>Manage Stock</Link>
                </div>
            </nav>


        </header>
    
  );
}

export default Header;