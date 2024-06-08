import React, { useState } from 'react';
import "./register.css";
import { useNavigate } from 'react-router-dom'

//Registration page to send the request with the details using an API and allow users to register if information not correct throw an error
export const Registration = () =>  {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [address, setAddress] = useState('');
    const [registrationError, setRegistrationError] = useState('');
    const navigate = useNavigate();
  
    const handleRegistration = async (e) => {
      e.preventDefault();
  
      const userDetails = { username, address,password, email, firstname: firstName, lastname: lastName };
  
      try {
        const response = await fetch('http://localhost:8080/auth/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(userDetails),
        });
  
        if (!response.ok) {
          throw new Error('Registration failed');
        }
  
   
        console.log('Registration successful');
        navigate('/login'); 
       
      } catch (error) {
        console.error('Registration error:', error);
        setRegistrationError('Registration failed. Please try again.');
      }
    };
  
    return (
      <div className="register-container">
        <h2>Register</h2>
        {registrationError && <p className="error">{registrationError}</p>}
        <form onSubmit={handleRegistration}>
          <div className="input-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="firstName">First Name</label>
            <input
              type="text"
              id="firstName"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="lastName">Last Name</label>
            <input
              type="text"
              id="lastName"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="address">Address</label>
            <input
              type="text"
              id="address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
            />
          </div>
          <button type="submit">Register</button>
        </form>
      </div>
    );
  };
  
