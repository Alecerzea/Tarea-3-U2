import React, { useState, useEffect } from 'react';
import api from '../api';

function Students() {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        api.get('/students')
            .then(response => setStudents(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h1>Estudiantes</h1>
            <ul>
                {students.map(student => (
                    <li key={student.id}>{student.user.username} - {student.academicHistory}</li>
                ))}
            </ul>
        </div>
    );
}

export default Students;
