import React from "react";
import Operation from "./Operation";
import "./EmployeeDetail.css";

export class EmployeeDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employee: null
        };
    }

    handleChange(e, col) {
        var employee = this.state.employee;
        employee[col] = e.target.value;
        this.setState({
            employee: employee
        });
    }

    goBack() {
        this.props.history.push({
            pathname: "/employees",
        });
    }

    save() {
        var save$ = null;
        if (this.props.location.state.operation === Operation.create) {
            save$ = fetch('http://localhost:3000/api/employees', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(this.state.employee),
                mode: 'cors',
                cache: 'default'
            });
        } else if (this.props.location.state.operation === Operation.edit) {
            save$ = fetch('http://localhost:3000/api/employees/' + this.state.employee.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(this.state.employee),
                mode: 'cors',
                cache: 'default'
            });
        }

        save$.catch(
            (err) => {
                console.error(err);
                alert("failed to save");
            }
        ).then(
            () => {
                alert("saved successfully");
            }
        );
    }

    render() {
        return (
            <div className="container p-5 w-100">
                <div className={"my-2"}>
                    <button type="button" className="btn btn-sm btn-secondary mr-2" onClick={this.goBack.bind(this)}>Back</button>
                    <button type="button" className="btn btn-sm btn-primary mr-2" onClick={this.save.bind(this)}>Save</button>
                </div>
                <form className="">
                    <div className="form-group">
                        <label htmlFor="id">ID</label>
                        <input className="form-control" name="id" type="number" value={this.state.employee ? this.state.employee.id : -1} onChange={(e) => this.handleChange(e, "id")} readOnly={true} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="emp_name">Name</label>
                        <input className="form-control" name="emp_name" type="text" value={this.state.employee ? this.state.employee.emp_name : ""} onChange={(e) => this.handleChange(e, "emp_name")} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="emp_age">Age</label>
                        <input className="form-control" name="emp_age" type="number" value={this.state.employee ? this.state.employee.emp_age : -1} onChange={(e) => this.handleChange(e, "emp_age")} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="emp_salary">Salary</label>
                        <input className="form-control" name="emp_salary" type="number" value={this.state.employee ? this.state.employee.emp_salary : -1} onChange={(e) => this.handleChange(e, "emp_salary")} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="join_date">Join Date</label>
                        <input className="form-control" name="join_date" type="text" value={this.state.employee ? this.state.employee.join_date : ""} onChange={(e) => this.handleChange(e, "join_date")} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="emp_name">phone</label>
                        <input className="form-control" name="phone" type="text" value={this.state.employee ? this.state.employee.phone : ""} onChange={(e) => this.handleChange(e, "phone")} />
                    </div>
                </form>
            </div>
        );
    }

    componentDidMount() {
        if (this.props.location.state.operation === Operation.create) {
            fetch('http://localhost:3000/api/employees', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                mode: 'cors',
                cache: 'default'
            }).then(
                res => res.json()
            ).then((employees) => {
                var maxId = -1;
                employees.forEach(employee => {
                    if (maxId < employee.id) {
                        maxId = employee.id;
                    }
                });
                this.setState({
                    employee: {
                        id: maxId + 1,
                        emp_name: "",
                        emp_age: 0,
                        emp_salary: 0,
                        join_date: new Date().toISOString(),
                        phone: ""
                    }
                });
            });
        } else if (this.props.location.state.operation === Operation.edit) {
            fetch('http://localhost:3000/api/employees/' + this.props.match.params.id, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                mode: 'cors',
                cache: 'default'
            }).then(
                res => res.json()
            ).then((employee) => {
                this.setState({
                    employee: employee
                });
            });
        }
    }
}