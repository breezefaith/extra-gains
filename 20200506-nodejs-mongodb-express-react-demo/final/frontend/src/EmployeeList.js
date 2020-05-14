import React from 'react';
import Operation from "./Operation";
import "./EmployeeList.css";

export class EmployeeList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        }
    }

    findAll() {
        fetch('http://localhost:3000/api/employees', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            mode: 'cors',
            cache: 'default'
        }).catch(error => {
            console.error(error);
        }).then(
            res => res.json()
        ).then((employees) => {
            this.setState({
                employees: employees
            });
        });
    }

    remove(id) {
        fetch("http://localhost:3000/api/employees/" + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            mode: 'cors',
            cache: 'default'
        }).catch(error => {
            console.error(error);
        }).then(
            () => {
                this.findAll();
            }
        )
    }

    edit(id) {
        this.props.history.push({
            pathname: "/employee/" + id,
            state: {
                operation: Operation.edit
            }
        });
    }

    create() {
        this.props.history.push({
            pathname: "/employee",
            state: {
                operation: Operation.create
            }
        });
    }

    render() {
        const trs = this.state.employees.map((employee, idx) => (
            <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.emp_name}</td>
                <td>{employee.emp_age}</td>
                <td>{employee.emp_salary}</td>
                <td>{employee.join_date}</td>
                <td>{employee.phone}</td>
                <td>
                    <button type="button" className="btn btn-sm btn-outline-info mr-2" onClick={this.edit.bind(this, employee.id)}>Edit</button>
                    <button type="button" className="btn btn-sm btn-outline-danger mr-2" onClick={this.remove.bind(this, employee.id)}>Delete</button>
                </td>
            </tr>
        ));

        return (
            <div className="container p-5 w-100">
                <div className="my-2">
                    <button type="button" className="btn btn-sm btn-primary mr-2" onClick={this.create.bind(this)} >Add</button>
                </div>
                <div className="my-2 table-responsive">
                    <table className="table table-striped table-condensed">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Age</th>
                                <th>Salary</th>
                                <th>Join Date</th>
                                <th>Phone</th>
                                <th>Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            {trs}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }

    componentDidMount() {
        this.findAll();
    }
}
