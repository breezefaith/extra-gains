import React from 'react';
import "./EmployeeList.css";

export class EmployeeList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        }
    }

    render() {
        const trs = this.state.employees.map((employee) => (
            <tr>
                <td>{employee.id}</td>
                <td>{employee.emp_name}</td>
                <td>{employee.emp_age}</td>
                <td>{employee.emp_salary}</td>
                <td>{employee.join_date}</td>
                <td>{employee.phone}</td>
            </tr>
        ));

        return (
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Salary</th>
                            <th>Join Date</th>
                            <th>Phone</th>
                        </tr>
                    </thead>
                    <tbody>
                        {trs}
                    </tbody>
                </table>
            </div>
        );
    }

    componentWillMount() {
        fetch('http://localhost:3000/api/employees', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            mode: 'cors',
            cache: 'default'
        })
            .then(res => res.json())
            .then((employees) => {
                this.setState({
                    employees: employees
                });
            });
    }
}
