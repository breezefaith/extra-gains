var express = require('express');
var path = require("path")

var tweetsRouter = require("./router/tweets");

var app = express();

app.use('/api/tweets', tweetsRouter);

var server = app.listen(5000, function () {
    var host = server.address().address;
    var port = server.address().port;

    console.log("serve on http://%s:%s", host, port);
})