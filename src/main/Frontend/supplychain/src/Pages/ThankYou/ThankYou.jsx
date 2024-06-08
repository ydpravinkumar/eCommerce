import { useEffect, useState} from "react"
import React from 'react'

//Thank You button to display the Order ID of the purchase made
export const ThankYou = () => {

    const generateOrderId = () => {
        const length = 5;
        const characters = '0123456789';
        let result = '';
        for (let i = 0; i < length; i++) {
          result += characters.charAt(Math.floor(Math.random() * characters.length));
        }
        return result;
      };

      const [orderId, setOrderId] = useState('');


  useEffect(() => {
    const newOrderId = generateOrderId();
    setOrderId(newOrderId);
  }, []);

  return (
    <div>
<h1>Thank you for your purchase!</h1>
      <p>Your order ID is: {orderId}</p>

    </div>
  )
}
