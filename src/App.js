import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Users from './components/Users';
import Students from './components/Students';
import Teachers from './components/Teachers';
import Courses from './components/Courses';

function App() {
    return (
        <Router>
            <div className="App">
                <Switch>
                    <Route path="/users" component={Users} />
                    <Route path="/students" component={Students} />
                    <Route path="/teachers" component={Teachers} />
                    <Route path="/courses" component={Courses} />
                </Switch>
            </div>
        </Router>
    );
}

export default App;
