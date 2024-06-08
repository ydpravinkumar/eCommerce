
import './App.css';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Navbar } from './Components/Navbar';
import {Cart} from "./Pages/Cart/Cart"
import {Shop} from "./Pages/Shop/Shop"
import { AuthProvider } from './AuthContext';
import {Registration} from './Pages/Registration/Registration';
import { Purchase } from './Pages/Purchase/purchase';
import { Login } from './Pages/Login/LoginTemp';
import { ThankYou } from './Pages/ThankYou/ThankYou';

//Router to navigate between pages

function App() {
  return (
    <div className='App'>

    <Router>
      <Navbar></Navbar>
      <AuthProvider>
      <Routes>
        <Route path="/" element={<Shop/>}/>
        <Route path="/cart" element={<Cart/>}/>
        <Route path="/Login" element={<Login/>}/>
        <Route path='/Registration' element={<Registration/>}/>
        <Route path='/purchase' element={<Purchase/>}/>
        <Route path='/ThankYou' element={<ThankYou/>}/>

      </Routes>
      </AuthProvider>
    
    </Router>

    </div>
);
}
export default App;
