import React from 'react';
import './Hero.css';
import dark_arrow from '../../assets/dark-arrow.png';

const Hero = ({ toggleRegisterStudentForm, toggleRegisterTeacherForm, loggedIn, username, role }) => {
  // Message for logged-in student
  const studentMessage = (
    <div>
      <h1>Welcome {username}!</h1>
      <p>Please check out lessons and your calendar.</p>
    </div>
  );

  // Message for logged-in teacher
  const teacherMessage = (
    <div>
      <h1>Welcome {username}!</h1>
      <p>Please add some classes to our list, or view your current classes on your calendar.</p>
    </div>
  );

  // Default message for not logged in
  const defaultMessage = (
    <div>
      <h1>Music makes your life more vibrant, and your journey more unforgettable.</h1>
      <p>At Music Academy, we help to transform lives with vibrancy, emotion, and lasting memories. Whether you're starting out or refining your skills, our academy fosters passion, connections, and personal growth. Join us to explore the endless possibilities music offers.</p>
    </div>
  );

  // Determine message based on login status and role
  const getMessage = () => {
    if (loggedIn) {
      if (role === 'ROLE_STUDENT') {
        return studentMessage;
      } else if (role === 'ROLE_TEACHER') {
        return teacherMessage;
      }
    }
    return defaultMessage;
  };

  return (
    <div className='hero container'>
      <div className='hero-text'>
        {getMessage()}
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
