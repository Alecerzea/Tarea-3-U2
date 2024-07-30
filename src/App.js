import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Users from './components/Users';
import Students from './components/Students';
import Teachers from './components/Teachers';
import Courses from './components/Courses';

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/users" element={<Users />} />
                    <Route path="/students" element={<Students />} />
                    <Route path="/teachers" element={<Teachers />} />
                    <Route path="/courses" element={<Courses />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;

