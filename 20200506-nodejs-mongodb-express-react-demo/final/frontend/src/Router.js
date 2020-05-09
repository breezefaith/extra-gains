import React from 'react';
import { EmployeeList } from './EmployeeList';
import { EmployeeDetail } from './EmployeeDetail';
import { BrowserRouter as Router, Route, Redirect } from "react-router-dom";

export function router() {
    return (
        <Router>
            <Route exact path="/employees" component={EmployeeList} />
            <Route exact path="/employees/:id" component={EmployeeDetail} />
            <Redirect from="/" to="/employees" />
        </Router>
    );
}