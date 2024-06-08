import React, { useState } from 'react';
import "./purchase.css";
import { useNavigate } from 'react-router-dom'


//Purchase page consisting of card number, cvv, expiry and submit button
export const Purchase = () => {
  const initialId = parseInt(localStorage.getItem('username'), 10); 
  const [cardDetails, setCardDetails] = useState({
    cardNumber: '',
    expiryDate: '',
    cvv: '',
    Id: isNaN(initialId) ? null : initialId 
  });

  const navigate = useNavigate();
  
  const [responseMessage, setResponseMessage] = useState('');


  const handleChange = (event) => {
    const { name, value } = event.target;
    setCardDetails(prevDetails => ({
      ...prevDetails,
      [name]: value
    }));
  };

  
  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log('Card Details:', cardDetails);
    
  
    try {
      const response = await fetch('http://localhost:8080/purchase/status', {
        method: 'POST',
        headers: {
          'Authorization': localStorage.getItem('token'),
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(cardDetails)
      });
  
     
      let data;
      const contentType = response.headers.get('content-type');
      if (contentType && contentType.indexOf('application/json') !== -1) {
        data = await response.json(); 
      } else {
        data = await response.text();
      }
  
      if (response.ok) {
        setResponseMessage('Transaction Successful!');
        navigate("/ThankYou")
      } else {
        setResponseMessage(data.message || data || 'Failed to process transaction');
      }
    } catch (error) {
      console.error('Error:', error);
      setResponseMessage('Error submitting transaction');
    }
  };

  return (
    <div className="purchase-container">
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="cardNumber">Card Number</label>
          <input
            type="text"
            id="cardNumber"
            name="cardNumber"
            value={cardDetails.cardNumber}
            onChange={handleChange}
            placeholder="Enter card number:4444444444444444"
          />
        </div>
        <div className="form-group">
          <label htmlFor="expiryDate">Expiry Date</label>
          <input
            type="text"
            id="expiryDate"
            name="expiryDate"
            value={cardDetails.expiryDate}
            onChange={handleChange}
            placeholder="MMYY"
          />
        </div>
        <div className="form-group">
          <label htmlFor="cvv">CVV</label>
          <input
            type="text"
            id="cvv"
            name="cvv"
            value={cardDetails.cvv}
            onChange={handleChange}
            placeholder="CVV"
          />
        </div>
        <button type="submit">Proceed</button>
        {responseMessage && <p>{responseMessage}</p>}
      </form>
    </div>
  );
};
