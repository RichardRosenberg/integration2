import React from 'react';
import Lesson from "./Lesson";
import CalendarLesson from './CalendarLesson';


function Home() {
  // Change the title on the internet tab
  document.title = "Lessons";

  return (
    <>

            <CalendarLesson />
            <Lesson />

    </>
  );
}

export default Home;