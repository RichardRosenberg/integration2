import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";

const Lesson = () => {
  const [title, setName] = useState("");
  const [date, setDate] = useState();
  const [lessons, setLessons] = useState([]);
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updatedLessonData, setUpdatedLessonData] = useState({
    id: 0,
    title: "",
    start: "",
  });

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleDateChange = (event) => {
    setDate(event.target.value);
  };

  // Function to populate modal with lesson data for update
  const handleUpdateClick = (lesson) => {
    setUpdatedLessonData({
      id: lesson.id,
      title: lesson.title,
      start: lesson.start,
    });
    toggleUpdateModal();
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const start = date + "T12:00:00";
    const lesson = { title, start };
    console.log(lesson);

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

  const handleUpdateLesson = () => {
    const { id, title, start } = updatedLessonData;
    const updatedStart = start + "T12:00:00"; // Append "T12:00:00" to the start date
    fetch(`http://localhost:8081/lesson/update/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title, start: updatedStart }), // Use updatedStart in the request body
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.text();
      })
      .then((data) => {
        console.log("Lesson updated:", data);
        fetchLessons(); // Fetch updated lessons
        toggleUpdateModal(); // Close the update modal
      })
      .catch((error) => {
        console.error("Error updating lesson:", error);
      });
  };

  const handleDelete = (id) => {
    fetch(`http://localhost:8081/lesson/delete/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        console.log("Lesson deleted");
        // Update the list of lessons after deleting a lesson
        fetchLessons();
      })
      .catch((error) => {
        console.error("Error deleting lesson:", error);
      });
  };

  const fetchLessons = () => {
    fetch("http://localhost:8081/lesson/getAll")
      .then((res) => res.json())
      .then((result) => {
        setLessons(result);
      });
  };

  useEffect(() => {
    fetchLessons();
  }, []);

  // Function to handle modal visibility
  const toggleUpdateModal = () => {
    setShowUpdateModal((prevState) => !prevState);
  };

  return (
    <div>
      <h1>Add Lesson</h1>
      <form>
        <input
          type="text"
          value={title}
          onChange={handleNameChange}
          placeholder="Lesson Name"
        />
        <br />
        <input type="date" value={date} onChange={handleDateChange} />
        <input type="submit" value="Submit" onClick={handleSubmit} />
      </form>
      <h1>Lessons</h1>
      <div>
        {lessons.map((lesson) => (
          <div key={lesson.id}>
            Id: {lesson.id} <br />
            Name: {lesson.title} <br />
            Date: {lesson.start} <br />
            <button onClick={() => handleDelete(lesson.id)}>Delete</button>
            <button onClick={() => handleUpdateClick(lesson)}>Update</button>
          </div>
        ))}
      </div>

      {/* Modal Popup for Update */}
      {showUpdateModal && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={toggleUpdateModal}>
              &times;
            </span>
            <h2>Update Lesson</h2>
            <form>
              <input
                type="text"
                value={updatedLessonData.title}
                onChange={(e) =>
                  setUpdatedLessonData({
                    ...updatedLessonData,
                    title: e.target.value,
                  })
                }
                placeholder="Lesson Name"
              />{" "}
              <br />
              <input
                type="date"
                value={updatedLessonData.start}
                onChange={(e) =>
                  setUpdatedLessonData({
                    ...updatedLessonData,
                    start: e.target.value,
                  })
                }
              />
              <input
                type="button"
                value="Update"
                onClick={handleUpdateLesson}
              />
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Lesson;