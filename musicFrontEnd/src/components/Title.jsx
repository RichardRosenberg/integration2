import React from 'react'
import '../styles/Title.css'

const Title = ({title}) => {
  return (
    <div className='title'>
        <h2>{title}</h2>
    </div>
  )
}

export default Title