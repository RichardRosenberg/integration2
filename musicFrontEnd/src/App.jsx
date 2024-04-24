import React, { useState, useEffect } from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import Hero from './components/Hero/Hero';
import Instruments from './components/Instruments/Instruments';
import Title from './components/Title/Title';
import About from './components/About/About';
import Testimonials from './components/Testimonials/Testimonials';
import Contact from './components/Contact/Contact';
import Footer from './components/Footer/Footer';
import LoginForm from './components/LoginForm/LoginForm';
import RegisterStudentForm from './components/RegisterForm/RegisterStudentForm';
import RegisterTeacherForm from './components/RegisterForm/RegisterTeacherForm';
import { jwtDecode } from "jwt-decode";

import Lessons from './components/lessons';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  const [showLogin, setShowLogin] = useState(false);
  const [showRegisterStudent, setShowRegisterStudent] = useState(false);
  const [showRegisterTeacher, setShowRegisterTeacher] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const [username, setUsername] = useState("");
  const [roles, setRoles] = useState([]); // State for roles

  // Function to toggle login form visibility
  const toggleLoginForm = () => {
    setShowLogin(!showLogin);
    setShowRegisterStudent(false);
    setShowRegisterTeacher(false);
  };

  // Function to toggle registration form visibility for students
  const toggleRegisterStudentForm = () => {
    setShowRegisterStudent(!showRegisterStudent);
    setShowLogin(false);
    setShowRegisterTeacher(false);
  };

  // Function to toggle registration form visibility for teachers
  const toggleRegisterTeacherForm = () => {
    setShowRegisterTeacher(!showRegisterTeacher);
    setShowLogin(false);
    setShowRegisterStudent(false);
  };

  // Callback function to be called after successful login
  const handleLoginSuccess = (token) => {
    // Log the received token
    console.log("Received token:", token);
    
    try {
      // Decode the JWT token to extract the username and roles
      const decodedToken = jwtDecode(token);
      console.log("Decoded token:", decodedToken);
      // Extract the username and roles from the decoded token
      const decodedUsername = decodedToken.sub;
      const decodedRoles = decodedToken.roles || []; // Ensure roles is an array

      // Set the username and roles in the state
      setUsername(decodedUsername);
      setRoles(decodedRoles);
      setShowLogin(false);
      setLoggedIn(true);
    } catch (error) {
      console.error("Error decoding token:", error);
    }
  };


  // Callback function to be called after logout
  const handleLogout = () => {
    setUsername(""); 
    setLoggedIn(false); 
    setRoles([]); // Reset roles on logout
    localStorage.removeItem('token');
    window.location.reload();
  };

  // Log the username after it's updated
  useEffect(() => {
    console.log("username in useEffect: " + username);
  }, [username]);

  return (
    document.title="Music Academy",
    <div>
      <Navbar toggleLoginForm={toggleLoginForm} loggedIn={loggedIn} handleLogout={handleLogout} /> 
      <Hero toggleRegisterStudentForm={toggleRegisterStudentForm} toggleRegisterTeacherForm={toggleRegisterTeacherForm} loggedIn={loggedIn} username={username} roles={roles} /> {/* Pass roles as a prop */}
      <div className="container">
        <Title title="Instruments" />
        <Instruments />
        <Title title="About Music Academy" />
        <About />
        <Title title="Testimonials" />
        <Testimonials />
        <Title title="Contact us" />
        <Contact />
        <Footer />   
      </div>
      {showLogin && <LoginForm setLoggedIn={setLoggedIn} onLoginSuccess={handleLoginSuccess} />}
      {showRegisterStudent && <RegisterStudentForm />}
      {showRegisterTeacher && <RegisterTeacherForm />}
    </div>
  );
}

export default App;
