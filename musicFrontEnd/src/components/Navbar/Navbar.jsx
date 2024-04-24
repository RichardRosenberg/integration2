import React, { useState, useEffect } from 'react';
import './Navbar.css';
import logo from '../../assets/logoW.png';
import menu_icon from '../../assets/menu-icon.png';
import { Link } from 'react-scroll';

const Navbar = ({ toggleLoginForm, loggedIn }) => {
  const [sticky, setSticky] = useState(false);
  const [mobileMenu, setMobileMenu] = useState(false);

  useEffect(() => {
    window.addEventListener('scroll', () => {
      window.scrollY > 50 ? setSticky(true) : setSticky(false);
    });
  }, []);

  useEffect(() => {
    console.log('loggedIn:', loggedIn);
  }, [loggedIn]);

  const toggleMenu = () => {
    setMobileMenu(!mobileMenu);
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    window.location.reload();
  };

  return (
    <nav className={`container ${sticky ? 'dark-nav' : ''}`}>
      <a href="/" className='logo-link'>
        <img src={logo} alt="Music Academy Logo" className='logo' />
      </a>
      <ul className={mobileMenu ? '' : 'hide-mobile-menu'}>
        <li><Link to='hero' smooth={true} offset={0} duration={500}>Home</Link></li>
        <li><Link to='instruments' smooth={true} offset={-300} duration={500}>Instruments</Link></li>
        <li><Link to='about' smooth={true} offset={-300} duration={500}>About us</Link></li>
        <li><Link to='testimonials' smooth={true} offset={-300} duration={500}>Testimonials</Link></li>
        <li><Link to='contact' smooth={true} offset={-300} duration={500}>Contact</Link></li>
        {loggedIn ? (
          <li><button className='btn' onClick={handleLogout}>Logout</button></li>
        ) : (
          <li><button className='btn' onClick={toggleLoginForm}>Login</button></li>
        )}
      </ul>
      <img src={menu_icon} alt="menu icon" className='menu-icon' onClick={toggleMenu} />
    </nav>
  );
};

export default Navbar;
