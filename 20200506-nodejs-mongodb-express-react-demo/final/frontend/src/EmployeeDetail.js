import React from "react"
import "./EmployeeDetail.css"
import { Link } from 'react-router-dom';

export class EmployeeDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div>
                <div>
                    <Link to="/employees">Back</Link>
                </div>
                <form>
                    <div>
                        <label>ID</label>
                        <input type="number" value={this.state.employee ? this.state.employee.id : -1} readOnly={true} />
                    </div>
                    <div>
                        <label>Name</label>
                        <input type="text" value={this.state.employee ? this.state.employee.emp_name : ""} readOnly={true} />
                    </div>
                    <div>
                        <label>Age</label>
                        <input type="number" value={this.state.employee ? this.state.employee.emp_age : -1} readOnly={true} />
                    </div>
                    <div>
                        <label>Salary</label>
                        <input type="number" value={this.state.employee ? this.state.employee.emp_salary : -1} readOnly={true} />
                    </div>
                    <div>
                        <label>Join Date</label>
                        <input type="text" value={this.state.employee ? this.state.employee.join_date : ""} readOnly={true} />
                    </div>
                    <div>
                        <label>Phone</label>
                        <input type="text" value={this.state.employee ? this.state.employee.phone : ""} readOnly={true} />
                    </div>
                </form>
            </div>
        );
    }

    componentDidMount() {
        fetch('http://localhost:3000/api/employees/' + this.props.match.params.id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            mode: 'cors',
            cache: 'default'
        })
            .then(res => res.json())
            .then((employee) => {
                this.setState({
                    employee: employee
                });
            });
    }
}