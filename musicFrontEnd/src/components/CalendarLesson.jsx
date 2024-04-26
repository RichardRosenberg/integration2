import React, {useState, useEffect} from 'react'
import { Calendar } from 'react-big-calendar';
import { dateFnsLocalizer } from 'react-big-calendar';
import "react-big-calendar/lib/css/react-big-calendar.css"
import "react-datepicker/dist/react-datepicker.css";
import { startOfWeek, getDay, parse, format  } from 'date-fns';
import DatePicker from 'react-datepicker';
import { jwtDecode } from "jwt-decode";


const locales = {
    "en-US": import("date-fns/locale/en-US").then((module) => module.default)

}

const localizer = dateFnsLocalizer({
    format,
    parse,
    startOfWeek,
    getDay,
    locales
})



const CalendarLesson = ({ token }) => {

    const [newEvent, setNewEvent] = useState({name:"", eStart: "", eEnd: ""})
    const [allEvents, setAllEvents] = useState([]);
    const [isTeacher, setIsTeacher] = useState(false);

    const fetchLessons = () => {
      fetch("http://localhost:8081/lesson/getAll")
        .then((res) => res.json())
        .then((result) => {
          setAllEvents(result);
        });
    };
    
    useEffect(() => {
      fetchLessons();
    }, []);


    const handleAddEvent = (e) => {
        e.preventDefault();

        const formatToUTC = (date) => date && new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes()));

        const startUTC = formatToUTC(newEvent.eStart);
        const endUTC = formatToUTC(newEvent.eEnd);
        
        const lesson = {
            title: newEvent.name,
            start: startUTC.toISOString(), 
            end: endUTC.toISOString()
        };
    

    
        fetch("http://localhost:8081/lesson/add", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(lesson),
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error("Network response was not ok");
            }
            return response.text();
          })
          .then((data) => {
            console.log("Lesson added:", data);
            fetchLessons();
          })
          .catch((error) => {
            console.error("Error adding lesson:", error);
          });
      };

      useEffect(() => {
        if (token) {
          try {
            const decodedToken = jwtDecode(token);
            const decodedRoles = decodedToken.roles || [];
            const isTeacher = decodedRoles.includes("ROLE_TEACHER");
            console.log("isTeacher:", isTeacher); // Console log to show the value of isTeacher
            setIsTeacher(isTeacher);
          } catch (error) {
            console.error("Error decoding token:", error);
          }
        }
      }, [token]);

  return (
    <div className='lesson-container'>
        <h1 className='h1-not-home'>Calendar</h1>
        {isTeacher && (        
        <div className='calendar'>
          <h2>Add New Lesson</h2>
            <input type='text' placeholder='Add Title' style={{width: "20%", marginRight: "10px"}}
            value={newEvent.name} onChange={(e) => setNewEvent({...newEvent, name: e.target.value})}
            />
            <DatePicker placeholderText='Start Date' style={{marginRight: "10px"}}
            selected={newEvent.eStart} onChange={(eStart) => setNewEvent({...newEvent, eStart})} 
            showTimeSelect
            timeFormat="HH:mm"
            timeIntervals={15}
            dateFormat='MMMM d, yyyy h:mm aa'

            />
                        <DatePicker placeholderText='End Date' style={{marginRight: "10px"}}
            selected={newEvent.eEnd} onChange={(eEnd) => setNewEvent({...newEvent, eEnd})} 
            showTimeSelect
            timeFormat="HH:mm"
            timeIntervals={15}
            dateFormat='MMMM d, yyyy h:mm aa'

            />
            <button style={{marginTop: "10px"}} onClick={handleAddEvent}>Add Lesson</button>
        </div>
        )}
     <Calendar localizer={localizer} events={allEvents} views={['month', 'day']}  startAccessor={(event) => new Date(event.start)}
  endAccessor={(event) => new Date(event.end)} style={{height:500, margin:"50px"}}/>

    </div>
  )
}

export default CalendarLesson