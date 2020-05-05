var Twitter = require('twitter');
const express = require('express');
var Sentiment = require('sentiment');
var sentiment = new Sentiment();
const server = express();

var fs = require("fs");
var tweets = JSON.parse(fs.readFileSync('part1.txt').toString());

var client = new Twitter({
    consumer_key: 'WC3HvtIQdLbE99rEFK1N0SCSG',
    consumer_secret: 'OhqIdzEmC80FuujUNaLNr48NkUF20xchSAfkSgycxPyhUlILW6',
    access_token_key: '1255635765842915330-qSH4qGwJNBQnd8d4ZluDDl2zkwVLbV',
    access_token_secret: 'xNr5QEFO7unfexUVHogWqO3sgOrreR7yOLkaN6DRP43m2'
});

server.get("/api/tweets/:param", function (req, res, next) {
    // var param = req.params.param;
    // client.get("search/tweets", { q: param, count: 50 }, function (error, tweets, response) {
    //     if(!error){
    //         console.error(error);
    //         res.end();
    //     }else{
    //         tweets.statuses.forEach(element => {
    //             console.log(element.text);
    //         });
    //         res.json(tweets).end();
    //     }
    // })
    var texts = tweets.statuses.map(elt => elt.text);
    res.json(basic(req, res, texts)).end();
});

function basic(req, res, texts) {
    var result = {};
    texts.forEach(txt => {
        // console.log(txt);
        // txt.split("[\\p{Punct}\\s]+").forEach(word=>{
        // txt.replace("[\\p{Punct}\\s]+","#tweetbot#").split("#tweetbot#").forEach(word => {
            // console.log(word);
            // if (result[word] == null) {
            //     result[word] = 1;
            // } else {
            //     result[word]++;
            // }
        // });
        var resu= sentiment.analyze(txt);
        console.dir(resu);
    });
    return result;
    // res.end();
}

function intermediate(texts) {

}

function harder(texts) {

}

var host = server.listen(5001, function () {
    var addr = host.address().address;
    var port = host.address().port;

    console.log("serve on http://%s:%s", addr, port);
})