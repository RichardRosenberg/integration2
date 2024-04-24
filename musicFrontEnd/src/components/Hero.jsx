import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Hero.css';


const Hero = () => {
  return (
    <div className='hero container'>
        <div className='hero-text'>
            <h1>Music makes your life more vibrant, and your journey more unforgettable.</h1>
            <p>At Music Academy, we help to transform lives with vibrancy, emotion, and lasting memories. Whether you're starting out or refining your skills, our academy fosters passion, connections, and personal growth. Join us to explore the endless possibilities music offers.</p>
            <Link to="/register"><button className='btn'>Register</button></Link>
        </div>
    </div>
  );
};

export default Hero;
