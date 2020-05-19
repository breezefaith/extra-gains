var createError = require('http-errors');
var express = require('express');
var { createProxyMiddleware } = require('http-proxy-middleware');
var AccessControl = require('express-ip-access-control');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var employeesRouter = require('./routes/employees');

var app = express();

var options = {
  mode: 'deny',
  denys: [
    //change it if nessesary
  ],
  allows: [],
  forceConnectionAddress: false,
  log: function (clientIp, access) {
    console.log(clientIp + (access ? ' accessed.' : ' denied.'));
  },

  statusCode: 401,
  message: 'Unauthorized'
};
app.use(AccessControl(options));

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use("/api/employees", employeesRouter);
app.use("/404", function (req, res, next) {
  next(createError(404));
});
app.use("/", createProxyMiddleware({ target: 'http://localhost:3001', changeOrigin: true }));

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
