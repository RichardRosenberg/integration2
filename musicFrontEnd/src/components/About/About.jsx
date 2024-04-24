import React from 'react'
import './About.css'
import live from '../../assets/live.jpg'


const About = () => {
  return (
    <div className='about'>
        <div className="about-left">
            <img src={live} alt="" className='live-img'/>
            
        </div>
        <div className="about-right">
            <p>Welcome to Music Academy, where passion meets expertise in the realm of  music education. Nestled in the heart of downtown, our school stands as a beacon for aspiring rock musicians of all ages and skill levels.</p>
            <p>Beyond technical proficiency, Music Academy fosters a sense of community among its students, encouraging collaboration and camaraderie. Through ensemble rehearsals, jam sessions, and live performances, aspiring rockers have the opportunity to connect with like-minded peers, forging friendships that extend far beyond the classroom walls.</p>
            <p> With a rich history of nurturing raw talent into polished performers, we pride ourselves on offering a dynamic learning environment where creativity thrives and musical boundaries are pushed.Join us today!</p> 
        </div>
    </div>
  )
}

export default About