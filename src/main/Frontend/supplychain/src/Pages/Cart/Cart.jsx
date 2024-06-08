import React, { useState, useEffect } from 'react';
import "./cart.css"; // Assumed CSS for styling
import { useNavigate } from 'react-router-dom';

//Cart Page to display all the products added to cart with a checkout button and the cart totoal

export const Cart = () => {
    const [cartItems, setCartItems] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        
        fetch('http://localhost:8080/cart/getcart', {
            method: 'GET',
            headers: {
                'Authorization': localStorage.getItem('token'),
                'Content-Type': 'application/json',
            }
        })
        .then(response => response.json())
        .then(data => setCartItems(data))
        .catch(error => console.log('Error fetching cart items:', error));
    }, []);

    

   
     const calculateTotal = () => {
        return cartItems.reduce((total, item) => total + item.product.price * item.quantity, 0);
    };

    const calculateTax = (total) => {
        return total * 0.18;  
    };

   
const total = calculateTotal();
const tax = calculateTax(total);
const finalTotal = total + tax;

    return (
        <div className="cart-container">
            <h1>Your Cart</h1>
            
            <div className="cart-items">
                {cartItems.length > 0 ? cartItems.map((item) => (
                    <div key={item.product.id} className="cart-item">
                        <img src={item.product.imageUrl} alt={item.product.name} className="cart-item-image" />
                        <div className="cart-item-details">
                            <h3>{item.product.name}</h3>
                            <p>${item.product.price}</p>
                            <p>Quantity: {item.quantity}</p>
                            
                        </div>
                        
                    </div>
                )) : <p>Your cart is empty.</p>}
            </div>
            <div className="cart-summary">
        <h2>Cart Total</h2>
        <p>Cart Total: ${total.toFixed(2)}</p>
        <p>Tax: ${tax.toFixed(2)}</p>
        <p>Total: ${finalTotal.toFixed(2)}</p>
        
    </div>
    <button onClick={() => navigate('/purchase')}>Checkout</button>
        </div>
    );
}
