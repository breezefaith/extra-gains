var express = require('express');
var EmployeeModel = require("../model/employee");

var router = express.Router();

router.get('/', function (req, res, next) {
    var employee = {
        id: req.query.id && parseInt(req.query.id),
        emp_name: req.query.emp_name && req.query.emp_name,
        emp_age: req.query.emp_age && parseInt(req.query.emp_age),
        emp_salary: req.query.emp_salary && parseFloat(req.query.emp_salary),
        join_date: req.query.join_date && req.query.join_date,
        phone: req.query.phone && req.query.phone,
    };
    for (const key in employee) {
        if (employee.hasOwnProperty(key) && employee[key] === undefined) {
            delete employee[key];
        }
    }
    EmployeeModel.find(employee).exec(function (err, employees) {
        if (err) {
            res.status(500).json(err).end();
        }
        else {
            res.json(employees).end();
        }
    });
});

router.post('/', function (req, res, next) {
    var employee = {
        id: req.body.id && parseInt(req.body.id),
        emp_name: req.body.emp_name && req.body.emp_name,
        emp_age: req.body.emp_age && parseInt(req.body.emp_age),
        emp_salary: req.body.emp_salary && parseFloat(req.body.emp_salary),
        join_date: req.body.join_date && req.body.join_date,
        phone: req.body.phone && req.body.phone,
    };
    for (const key in employee) {
        if (employee.hasOwnProperty(key) && employee[key] === undefined) {
            delete employee[key];
        }
    }

    EmployeeModel.find({ id: employee.id }, function (err, employees) {
        EmployeeModel.create(employee, function (err) {
            if (err) {
                res.status(500).json(err).end();
            }
            else {
                res.status(200).end();
            }
        });
    });
});

router.put('/:id', function (req, res, next) {
    var id = req.params.id;
    var employee_update = {
        emp_name: req.body.emp_name && req.body.emp_name,
        emp_age: req.body.emp_age && parseInt(req.body.emp_age),
        emp_salary: req.body.emp_salary && parseFloat(req.body.emp_salary),
        join_date: req.body.join_date && req.body.join_date,
        phone: req.body.phone && req.body.phone,
    };
    for (const key in employee_update) {
        if (employee_update.hasOwnProperty(key) && employee_update[key] === undefined) {
            delete employee_update[key];
        }
    }
    if (id == null) {
        res.status(500).send("id mustn't be null").end();
        return;
    }
    EmployeeModel.update({ id: id }, employee_update, function (err) {
        if (err) {
            res.status(500).json(err).end();
        }
        else {
            res.status(200).end();
        }
    });
});

router.get('/:id', function (req, res, next) {
    var id = req.params.id;
    EmployeeModel.findOne({ id: id }).exec(function (err, employee) {
        if (err) {
            res.status(500).json(err).end();
        }
        else {
            res.json(employee).end();
        }
    });
});

router.delete('/:id', function (req, res, next) {
    var id = req.params.id;
    EmployeeModel.remove({ id: id }).exec(function (err) {
        if (err) {
            res.status(500).json(err).end();
        }
        else {
            res.status(200).end();
        }
    });
});

module.exports = router;
