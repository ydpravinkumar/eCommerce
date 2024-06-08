import React, { useState, useEffect } from 'react';
import "./product.css";


//Shop page to display the products and cart adiition button to allow users to add products to cart
export const Shop = () => {
    const [products, setProducts] = useState([]);
    const [cartItems, setCartItems] = useState([]);


    useEffect(() => {
 
        fetch('http://localhost:8080/products/listall')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.log('Error fetching products:', error));
    }, []);
    const handleAddToCart = (product, quantity) => {
        fetch(`http://localhost:8080/cart/addtocart/${product.id}/${quantity}`, {
            method: 'POST',
            headers: {
                'Authorization':localStorage.getItem('token'),
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ productId: product.id, quantity })
        })
        .then(response => response.json())
        .then(() => {
           
            const newCartItem = {
                product,
                quantity: Number(quantity)
            };
            setCartItems([...cartItems, newCartItem]);
            alert('Product added to cart!');
        })
        .catch(error => {
            console.error('Error adding product to cart:', error);
            alert('Failed to add product to cart.');
        });
    };

    return (
        <div className="shop-container">
            <h1>Our Products</h1>
            <div className="products-grid">
                {products.map((product) => (
                    <div key={product.id} className="product-card">
                        <img src={product.imageUrl} alt={product.name} className="product-image" />
                        <h3>{product.name}</h3>
                        <p>${product.price}</p>
                        <input type="number" defaultValue={1} min={1} className="product-quantity" />
                        <button onClick={() => handleAddToCart(product, document.querySelector(`input`).value)}>Add to Cart</button>
                    </div>
                ))}
            </div>
        </div>
    );
}
