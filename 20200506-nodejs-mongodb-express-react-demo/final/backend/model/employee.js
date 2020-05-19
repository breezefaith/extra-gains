var mongoose = require("mongoose");
var db = require("../db");

var EmployeeSchema = new mongoose.Schema({
    id: {
        type: Number,
        unique: true,
        index: true,
        required: true,
    },
    emp_name: {
        type: String
    },
    emp_age: {
        type: Number,
    },
    emp_salary: {
        type: Number,
    },
    join_date: {
        type: Date,
        default: Date.now
    },
    phone: {
        type: String,
    }
}, {
    collection: "employee"
});

var EmployeeModel = mongoose.model('employee', EmployeeSchema);

module.exports = EmployeeModel;