var createError = require('http-errors');
var express = require('express');
var path = require('path');
var http = require('http');
var sql = require('mssql')

var dbConfig = {
    user: 'sa',
    password: 'zzc123456AA?',
    server: 'localhost',
    database: 'employee_db',
    port: 1433,
    options: {

    }
}

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post("/api/login", async function (req, res, next) {
    var username = req.body.username;
    var password = req.body.password;

    try {
        await sql.connect(dbConfig);
        const result = await sql.query`select * from [employees].[employee] where Login=${username} and Password=${password}`;
        if (result.recordset.length > 0) {
            var emp = result.recordset[0];
            res.json({
                id: emp.ID,
                login: emp.Login,
                password: emp.Password,
                firstName: emp.FirstName,
                lastName: emp.LastName,
            });
        } else {
            res.json(null);
        }
    } catch (err) {
        res.status(500).send('Internal Server Error');
        throw err;
    }
});

app.use(express.static(path.join(__dirname, 'client/dist/ng-front')));
app.get('*', (req, res) => {
    res.sendFile(path.join(__dirname + '/client/dist/ng-front/index.html'));
});

var port = process.env.PORT || '3000';
app.set('port', port);

var server = http.createServer(app);
server.listen(port);
