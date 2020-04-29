/**
 * Name: 
 */
const http = require("http");
const fs = require("fs")


const MINPORT = 5000;
const MAXPORT = 35000;
const REPART1 = /\/UNLINK\/.+/;  // You need to fill these in!
const REPART2 = /\/SIZE\/.+/;  // You need to fill these in!
const REPART3 = /\/FETCH\/.+/;  // You need to fill these in!
const WORKDIRECTORY = "WEBSERVER/";
const VALIDEXT = [
    ["txt", "text/plain"],
    ["html", "text/html"],
    ["mp3", "audio/mp3"],
    ["jpg", "image/jpeg"]
];

function randomPort(min, max) {
    return parseInt(Math.random() * (max - min + 1) + min, 10);
}

function doRemove(req, res) {
    var filepath = WORKDIRECTORY + req.url.substr(8);
    res.writeHead(200, { "Content-Type": "text/plain" });
    fs.unlink(filepath, function (err) {
        if (err != null) {
            res.write("ERROR: could not unlink file " + req.url);
        } else {
            res.write("UNLINK: the URL " + req.url + " was removed.");
        }
        res.end();
    });
}

function doSize(req, res) {
    var filepath = WORKDIRECTORY + req.url.substr(6);
    res.writeHead(200, { "Content-Type": "text/plain" });
    fs.stat(filepath, function (err, stats) {
        if (err != null) {
            res.write("ERROR: could not stat file " + req.url);
        } else {
            res.write("STAT: the URL " + req.url + " size is " + stats.size);
        }
        res.end();
    })
}

function doFetch(req, res) {
    var filepath = WORKDIRECTORY + req.url.substr(7);
    fs.readFile(filepath, function (err, data) {
        var postfix = filepath.substr(filepath.lastIndexOf(".") + 1);
        try {
            if (postfix == VALIDEXT[0][0]) {
                res.writeHead(200, { "Content-Type": VALIDEXT[0][1] });
            } else if (postfix == VALIDEXT[1][0]) {
                res.writeHead(200, { "Content-Type": VALIDEXT[1][1] });
            } else if (postfix == VALIDEXT[2][0]) {
                res.writeHead(200, { "Content-Type": VALIDEXT[2][1] });
            } else if (postfix == VALIDEXT[3][0]) {
                res.writeHead(200, { "Content-Type": VALIDEXT[3][1] });
            } else {
                throw "Unsupported file type";
            }
            res.write(data);
        } catch (error) {
            res.write("ERROR: unable to fetch URL " + req.url);
        }
        res.end();
    });
}

function incoming(req, res) {
    console.log("Incoming request with the URL: " + req.url);

    if (REPART1.test(req.url) === false && REPART2.test(req.url) === false && REPART3.test(req.url) === false) {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.write("INVALID URL: " + req.url);
        res.end();
        return;
    }

    if (REPART1.test(req.url) === true) {
        doRemove(req, res);
    } else if (REPART2.test(req.url) === true) {
        doSize(req, res);
    } else if (REPART3.test(req.url) === true) {
        doFetch(req, res);
    }
}

//start up
var port = randomPort(MINPORT, MAXPORT);
// var port = 1000;
http.createServer(incoming).listen(port);
console.log("Listening on http://localhost:" + port);
