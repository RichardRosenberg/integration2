import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/Navbar.css';
import logo from '../assets/logoW.png';



const Navbar = () => {
  const [clickedLink, setClickedLink] = useState('Home');
  //logic to get Navbar dark-color sticky
  //const [sticky, setSticky] = useState(false);

  //ternaly operator to check scrollY value
  //ScrollY:https://developer.mozilla.org/en-US/docs/Web/API/Window/scrollY
  //useEffect(()=>{
    //window.addEventListener('scroll', ()=>{
        //window.scrollY > 50 ? setSticky(true) : setSticky(false);
    //})
//},[]);

  //const [mobileMenu, setMobileMenu] = useState(false);
  //const toggleMenu = ()=>{
  //mobileMenu ? setMobileMenu(false) : setMobileMenu(true);
  //};

  const handleLogin = () => {
    window.location.href = 'http://localhost:5173/login';
  };

  //If the sticky is true, use dark-nav, otherwise use default.
  return (
    <nav className="container">
      <Link to="/">
        <img src={logo} alt="logo" className='logo' />
      </Link>
      <ul>
        <div className='nav-links'>
          <li><Link to="/" onClick={() => setClickedLink('Home')}>Home</Link></li>
          <li><Link to="/instruments" onClick={() => setClickedLink('Instruments')}>Instruments</Link></li>
          <li><Link to="/lesson" onClick={() => setClickedLink('Lessons')}>Lessons</Link></li>
          <li><Link to="/about" onClick={() => setClickedLink('About')}>About us</Link></li>
          <li><Link to="/testimonials" onClick={() => setClickedLink('Testimonials')}>Testimonials</Link></li>
          <li><Link to="/contact" onClick={() => setClickedLink('Contact')}>Contact us</Link></li>
        </div>
        <li><button className={`btn`} onClick={handleLogin}>Login</button></li>
      </ul>
      
    </nav>
  );
};

export default Navbar;
