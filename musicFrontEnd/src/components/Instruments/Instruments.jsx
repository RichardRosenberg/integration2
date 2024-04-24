import React from 'react'
import './Instruments.css'
import instrument_1 from '../../assets/instrument_1.jpg'
import instrument_2 from '../../assets/instrument_2.jpg'
import instrument_3 from '../../assets/instrument_3.jpg'
import guitarB from '../../assets/guitarB.png'
import drumB from '../../assets/drumB.png'
import pianoB from '../../assets/pianoB.png'

const Instruments = () => {
  return (
    <div className='instruments'>
        <div className='instrument'>
            <img src={instrument_1} alt="photo of guitar" />
            <div className="caption">
                <img src={guitarB} alt="image of guitar"/>
                <p>Guitar</p>
            </div>
        </div>
        <div className='instrument'>
            <img src={instrument_2} alt="photo of piano" />
            <div className="caption">
                <img src={pianoB} alt="image of piano"/>
                <p>Piano</p>
            </div>
        </div>
        <div className='instrument'>
            <img src={instrument_3} alt="photo of drum" />
            <div className="caption">
                <img src={drumB} alt="image of drum"/>
                <p>Drum</p>
            </div>
        </div>
    </div>
  )
}

export default Instruments
