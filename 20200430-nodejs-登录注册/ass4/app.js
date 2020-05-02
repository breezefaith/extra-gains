var express = require('express');
var path = require("path")
var ejs = require('ejs');
var exphbs = require('express-handlebars');
var bodyParser= require('body-parser');
var cookieParser = require("cookie-parser");

var config = require("./conf");
var userRouter = require("./router/user");

var app = express();

app.set('views', path.join(__dirname, 'views'));
app.engine('html', exphbs({
    layoutsDir: 'views',
    defaultLayout: null,
    extname: '.html'
}));
app.set('view engine', 'html');

app.use(bodyParser.urlencoded({ extended: true })); 
app.use(cookieParser());
app.use(express.static("./public"));


app.use('/user', userRouter);
app.get('/', function (req, res) {
    res.redirect("/user");
})



var server = app.listen(8081, function () {

    var host = server.address().address;
    var port = server.address().port;

    console.log("serve on http://%s:%s", host, port);
})