import React, { useState } from 'react'; 
import { useNavigate } from 'react-router-dom';
import "./login.css";
import { Link } from "react-router-dom";

//Login page to send the details of username and password using the API and process the jwt token in order to grant user the access to their account

export const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginError, setLoginError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    const credentials = { username, password };

    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }
    

      var data = await response.json();
      var arrData = data.jwt.split("IDIDIDIDIDID");
      localStorage.setItem('token', "Bearer " + arrData[0]);
      localStorage.setItem('username',arrData[1]);
      

     
      console.log('Login successful:', data.jwt);

      navigate('/');
      
    } catch (error) {
      console.error('Login error:', error);
      setLoginError('Login failed. Please try again.');
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      {loginError && <p className="error">{loginError}</p>}
      <form onSubmit={handleLogin}>
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
        <button type="submit">Login</button>
        <p>
          Not a user? <Link to="/registration">Register Now</Link>
        </p>
      </form>
    </div>
  );
};