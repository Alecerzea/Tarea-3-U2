import React, { useState, useEffect } from 'react';
import api from '../api';

function Teachers() {
    const [teachers, setTeachers] = useState([]);

    useEffect(() => {
        api.get('/teachers')
            .then(response => setTeachers(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h1>Docentes</h1>
            <ul>
                {teachers.map(teacher => (
                    <li key={teacher.id}>{teacher.user.username} - {teacher.specialty}</li>
                ))}
            </ul>
        </div>
    );
}

export default Teachers;
