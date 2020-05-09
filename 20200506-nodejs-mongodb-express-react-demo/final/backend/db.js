var mongoose = require("mongoose");

const uri = "mongodb://127.0.0.1:27017/final";
var db = mongoose.connect(uri);

module.exports = db;