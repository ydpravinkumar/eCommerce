import React from "react";
import {Link} from "react-router-dom"
import {ShoppingCart, UserCircle} from "phosphor-react";
import "./navbar.css"

//Navigation Menu Page to display and navigate through cart page, shop page, Login & Registration

export const Navbar = () => {
    return (
    <div className="navbar">
        <div className="links">
            <Link to="/login"><UserCircle size={50} /></Link>
            <Link to="/">Shop</Link>
            <Link to="/purchase"></Link>
            <Link to="/cart">
            <ShoppingCart size={32}>
            </ShoppingCart>
            </Link>

        </div>
    </div>
    );
};