import React, { useState, useEffect } from 'react';
import api from '../api';

function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        api.get('/users')
            .then(response => setUsers(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h1>Usuarios</h1>
            <ul>
                {users.map(user => (
                    <li key={user.id}>{user.username} - {user.email}</li>
                ))}
            </ul>
        </div>
    );
}

export default Users;
