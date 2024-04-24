import React from 'react';
import './Hero.css';
import dark_arrow from '../../assets/dark-arrow.png';

const Hero = ({ toggleRegisterStudentForm, toggleRegisterTeacherForm, loggedIn, username, roles }) => {
  const defaultMessage = (
    <div>
      <h1>Music makes your life more vibrant, and your journey more unforgettable.</h1>
      <p>At Music Academy, we help to transform lives with vibrancy, emotion, and lasting memories. Whether you're starting out or refining your skills, our academy fosters passion, connections, and personal growth. Join us to explore the endless possibilities music offers.</p>
    </div>
  );

  const loggedInMessage = (
    <div>
      <h1>Welcome {username}!</h1>
      {roles && roles.length > 0 && (
        <p>Your role: {roles.map((role, index) => (
          <span key={index}>{role}</span>
        ))}</p>
      )}
      <p>Please check out lessons and your calendar.</p>
    </div>
  );

  return (
    <div className='hero container'>
      <div className='hero-text'>
        {loggedIn ? loggedInMessage : defaultMessage}
        {!loggedIn && (
          <div>
            <button className='btn' onClick={toggleRegisterStudentForm}>Register as Student</button>
            <button className='btn' onClick={toggleRegisterTeacherForm}>Register as Teacher</button>
          </div>
        )}
      </div>
    </div>
  );
};

export default Hero;
