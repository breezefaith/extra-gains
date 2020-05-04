var Twitter = require('twitter');
const express = require('express');
const server = express();

var client = new Twitter({
    consumer_key: 'WC3HvtIQdLbE99rEFK1N0SCSG',
    consumer_secret: 'OhqIdzEmC80FuujUNaLNr48NkUF20xchSAfkSgycxPyhUlILW6',
    access_token_key: '1255635765842915330-qSH4qGwJNBQnd8d4ZluDDl2zkwVLbV',
    access_token_secret: 'xNr5QEFO7unfexUVHogWqO3sgOrreR7yOLkaN6DRP43m2'
});

server.get("/api/tweets/:param", function (req, res, next) {
    var param = req.params.param;
    console.log(param);
    client.get("search/tweets", { q: param, count: 50 }, function (error, tweets, response) {
        console.error(error);
        console.log(tweets);
        console.log(response);
        res.json(tweets).end();
    })
});

var host = server.listen(5001, function () {
    var addr = host.address().address;
    var port = host.address().port;

    console.log("serve on http://%s:%s", addr, port);
})