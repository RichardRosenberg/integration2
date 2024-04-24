import React, { useEffect, useState } from 'react';
import './Hero.css';
import Lesson from '../Lesson'; 
import Calendar from '../CalendarLesson';
import { jwtDecode } from "jwt-decode";

const Hero = ({ 
  toggleRegisterStudentForm, 
  toggleRegisterTeacherForm, 
  loggedIn, 
  username, 
  roles, 
  userId, 
  token 
}) => {
  const [showLessons, setShowLessons] = useState(false);
  const [showCalendar, setShowCalendar] = useState(false);
  const [isTeacher, setIsTeacher] = useState(false);

  // Decoding the token and extracting user roles
  useEffect(() => {
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        const decodedRoles = decodedToken.roles || [];
        const isTeacher = decodedRoles.includes("ROLE_TEACHER");
        console.log("isTeacher:", isTeacher); 
        setIsTeacher(isTeacher);
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    }
  }, [token]);

  const toggleLessons = () => {
    setShowLessons(!showLessons);
    setShowCalendar(false);
  };

  const toggleCalendar = () => {
    setShowCalendar(!showCalendar); 
    setShowLessons(false); 
  };

  return (
    <div className='hero container'>
      <div className='hero-text'>
        {loggedIn ? (
          <div>
            <h1>Welcome {username}!</h1>
            <p>
              Please check out 
              <button className="link" onClick={toggleLessons}>Lessons</button> 
              and 
              <button className="link" onClick={toggleCalendar}>Calendar</button>.
            </p>
            {showLessons && (
              <Lesson 
                token={token} 
                roles={roles} 
                userId={userId} 
              />
            )}
            {showCalendar && <Calendar />}
          </div>
        ) : (
          <div>
            <h1>Music makes your life more vibrant, and your journey more unforgettable.</h1>
            <p>At Music Academy, we help to transform lives with vibrancy, emotion, and lasting memories. Whether you're starting out or refining your skills, our academy fosters passion, connections, and personal growth. Join us to explore the endless possibilities music offers.</p>
            <button className='btn' onClick={toggleRegisterStudentForm}>Register as Student</button>
            <button className='btn' onClick={toggleRegisterTeacherForm}>Register as Teacher</button>
          </div>
        )}
      </div>
    </div>
  );
};

export default Hero;
