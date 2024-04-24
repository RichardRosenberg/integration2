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

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  const [showLogin, setShowLogin] = useState(false);
  const [showRegisterStudent, setShowRegisterStudent] = useState(false);
  const [showRegisterTeacher, setShowRegisterTeacher] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const [username, setUsername] = useState("");
  const [roles, setRoles] = useState([]);
  const [userId, setUserId] = useState("");
  const [token, setToken] = useState("");

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
      // Decode the JWT token to extract the username, roles, and userId
      const decodedToken = jwtDecode(token);

      // Log the decoded token
      console.log("Decoded token:", decodedToken);

      // Extract the username, roles, and userId from the decoded token
      const decodedUsername = decodedToken.sub;
      const decodedRoles = decodedToken.roles || [];
      const decodedUserId = decodedToken.userId;

      // Set the username, roles, and userId in the state
      setUsername(decodedUsername);
      setRoles(decodedRoles);
      setUserId(decodedUserId);
      setToken(token);
      setLoggedIn(true);
      setShowLogin(false);
    } catch (error) {
      console.error("Error decoding token:", error);
    }
  };

  // Callback function to be called after logout
  const handleLogout = () => {
    setUsername(""); 
    setRoles([]); 
    setUserId(""); 
    setLoggedIn(false); 
    setToken(""); 
    localStorage.removeItem('token');
    window.location.reload();
  };

  // Log the username, roles, and userId after they're updated
  useEffect(() => {
    console.log("username in useEffect:", username);
    console.log("roles in useEffect:", roles);
    console.log("userId in useEffect:", userId);
    console.log("token in useEffect:", token);
  }, [username, roles, userId, token]);

  return (
    <div>
      <Navbar toggleLoginForm={toggleLoginForm} loggedIn={loggedIn} handleLogout={handleLogout} /> 
      <Hero 
        loggedIn={loggedIn} 
        username={username} 
        roles={roles} 
        userId={userId} 
        toggleRegisterStudentForm={toggleRegisterStudentForm} 
        toggleRegisterTeacherForm={toggleRegisterTeacherForm} 
        token={token} 
      />
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
