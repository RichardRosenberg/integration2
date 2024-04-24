import React, { useRef } from 'react'
import './Testimonials.css'
import next_icon from '../../assets/next-icon.png'
import back_icon from '../../assets/back-icon.png'
import user_1 from '../../assets/user-1.png'
import user_2 from '../../assets/user-2.png'
import user_3 from '../../assets/user-3.png'
import user_4 from '../../assets/user-4.png'

const Testimonials = () => {

    const slider = useRef();
    let tx = 0;

const slideForward = ()=>{
    if(tx > -50){
        tx -= 25;
    }
    slider.current.style.transform = `translateX(${tx}%)`;
}
const slideBackward = ()=>{
    if(tx < 0){
        tx += 25;
    }
    slider.current.style.transform = `translateX(${tx}%)`;
}

  return (
    <div className='testimonials'>
      <img src={next_icon} alt="" className='next-btn' onClick={slideForward}/>
      <img src={back_icon} alt="" className='back-btn' onClick={slideBackward}/>
      <div className="slider">
        <ul ref={slider}>
            <li>
                <div className="slide">
                    <div className="user-info">
                        <img src={user_1} alt="" />
                        <div>
                            <h3>Emily Williams</h3>
                            <span>Montreal, QC</span>
                        </div>
                    </div>
                    <p>Enrolling at Music Academy for my music education has been an incredibly rewarding choice. The nurturing musical community, top-notch facilities, and dedication to artistic growth have surpassed all my expectations. It's truly been an inspiring journey.</p>
                </div>
            </li>
            <li>
                <div className="slide">
                    <div className="user-info">
                        <img src={user_2} alt="" />
                        <div>
                            <h3>William Jackson</h3>
                            <span>Mont-Royal, QC</span>
                        </div>
                    </div>
                    <p>Opting to advance my musical education at Music Academy has proven to be an exceptional decision. The encouraging atmosphere, cutting-edge resources, and unwavering dedication to musical mastery have far surpassed what I could have imagined. It's been an enriching experience beyond compare.</p>
                </div>
            </li>
            <li>
                <div className="slide">
                    <div className="user-info">
                        <img src={user_3} alt="" />
                        <div>
                            <h3>Anna Smith</h3>
                            <span>Dorval, QC</span>
                        </div>
                    </div>
                    <p>Attending Music Academy has been transformative for my musical journey. The faculty's expertise, personalized guidance, and collaborative environment have elevated my skills to new heights. It's a place where passion meets proficiency, and I'm grateful for every moment spent honing my craft</p>
                </div>
            </li>
            <li>
                <div className="slide">
                    <div className="user-info">
                        <img src={user_4} alt="" />
                        <div>
                            <h3>Gaetan Dupont</h3>
                            <span>Pierrefonds, QC</span>
                        </div>
                    </div>
                    <p>Music Academy has been a beacon of inspiration and growth for me. From the moment I stepped through the doors, I felt a sense of belonging among fellow musicians and mentors who share my dedication to music. </p>
                </div>
            </li>
        </ul>
      </div>
    </div>
  )
}

export default Testimonials
