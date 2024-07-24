import React, { useState, useEffect } from 'react';
import api from '../api';

function Courses() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        api.get('/courses')
            .then(response => setCourses(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h1>Cursos</h1>
            <ul>
                {courses.map(course => (
                    <li key={course.id}>{course.name} - {course.level}</li>
                ))}
            </ul>
        </div>
    );
}

export default Courses;
