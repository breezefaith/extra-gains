const Twitter = require('twitter');
const express = require('express');
const server = express();

const natural = require('natural');
const tokenizer = new natural.WordTokenizer();
const language = "EN"
const defaultCategory = 'N';
const defaultCategoryCapitalized = 'NNP';
var lexicon = new natural.Lexicon(language, defaultCategory, defaultCategoryCapitalized);
var ruleSet = new natural.RuleSet('EN');
var tagger = new natural.BrillPOSTagger(lexicon, ruleSet);

var fs = require("fs");
var tweets = JSON.parse(fs.readFileSync('part1.txt').toString());

var client = new Twitter({
    consumer_key: 'WC3HvtIQdLbE99rEFK1N0SCSG',
    consumer_secret: 'OhqIdzEmC80FuujUNaLNr48NkUF20xchSAfkSgycxPyhUlILW6',
    access_token_key: '1255635765842915330-qSH4qGwJNBQnd8d4ZluDDl2zkwVLbV',
    access_token_secret: 'xNr5QEFO7unfexUVHogWqO3sgOrreR7yOLkaN6DRP43m2'
});

server.get("/api/tweets/:param", function (req, res, next) {
    var param = req.params.param;
    client.get("search/tweets", { q: param, count: 50 }, function (error, tweets, response) {
        if(!error){
            console.error(error);
            res.end();
        }else{
            var texts = tweets.statuses.map(elt => elt.text);
            res.write(basic(texts));
            // res.write("\n");
            // res.write(intermediate(texts));
            // res.write("\n");
            // res.write(harder(texts));
            res.end();
        }
    })
});

function basic(texts) {
    function getWordCount(words) {
        var obj = {};
        for (var i = 0, l = words.length; i < l; i++) {
            var item = words[i];
            obj[item] = (obj[item] + 1) || 1;
        }
        return obj;
    }

    function findWord(wordList, key) {
        for (const obj of wordList) {
            if (obj.key == key) {
                return obj;
            }
        }
        return null;
    }

    var table = [];
    texts.forEach(txt => {
        list = tokenizer.tokenize(txt);
        var tmp = getWordCount(list);
        for (const key in tmp) {
            var obj = findWord(table, key);
            if (obj == null) {
                table.push({ key: key, count: tmp[key] });
            } else {
                obj.count += tmp[key];
            }
        }
    });
    table.sort((a, b) => b.count - a.count);

    var res = "";
    for (const item of table) {
        res += item.key + " ";
        if (res.length >= 280) {
            res = res.substr(0, 279);
            break;
        }
    }
    res += ".";
    return res;
}

function intermediate(texts) {
    function getTaggedWordCount(taggedWords) {
        var obj = {};
        for (var i = 0, l = taggedWords.length; i < l; i++) {
            var item = taggedWords[i].token;
            obj[item] = obj[item] || {};
            obj[item].count = (obj[item].count + 1) || 1;
            obj[item].tag = taggedWords[i].tag;
        }
        return obj;
    }

    function findWord(wordList, key) {
        for (const obj of wordList) {
            if (obj.key == key) {
                return obj;
            }
        }
        return null;
    }

    var table = {
        article: [],
        adj: [],
        noun: [],
        verb: [],
        preposition: [],
        conjunction: [],
        adv: [],
        pronoun: []
    };
    texts.forEach(txt => {
        var taggedWords = tagger.tag(tokenizer.tokenize(txt)).taggedWords;
        var tmp = getTaggedWordCount(taggedWords);

        for (const key in tmp) {
            var obj;
            var tag;

            if (tmp[key].tag.startsWith("VB")) {
                obj = findWord(table.verb, key);
                tag = "verb";
            } else if (tmp[key].tag.startsWith("N")) {
                obj = findWord(table.noun, key);
                tag = "noun";
            } else if (tmp[key].tag.startsWith("DT")) {
                obj = findWord(table.article, key);
                tag = "article";
            } else if (tmp[key].tag.startsWith("CC")) {
                obj = findWord(table.conjunction, key);
                tag = "conjunction";
            } else if (tmp[key].tag.startsWith("JJ")) {
                obj = findWord(table.adj, key);
                tag = "adj";
            } else if (tmp[key].tag.startsWith("IN")) {
                obj = findWord(table.preposition, key);
                tag = "preposition";
            } else if (tmp[key].tag.startsWith("PRP")) {
                obj = findWord(table.pronoun, key);
                tag = "pronoun";
            } else if (tmp[key].tag.startsWith("RB")) {
                obj = findWord(table.adv, key);
                tag = "adv";
            } else {
                tag = null;
                obj = null;
            }
            if (tag != null) {
                if (obj == null) {
                    table[tag].push({ key: key, count: tmp[key].count });
                } else {
                    obj.count += tmp[key].count;
                }
            }
        }
    });

    for (const key in table) {
        if (table.hasOwnProperty(key)) {
            table[key].sort((a, b) => b.count - a.count);
        }
    }

    var res = "";
    res += `${table.article[0].key} ${table.adj[0].key}, ${table.adj[1].key} ${table.noun[0].key} ${table.verb[0].key} ${table.preposition[0].key} ${table.article[1].key} ${table.noun[1].key} `;
    res += `${table.conjunction[0].key} ${table.adv[0].key} ${table.verb[1].key} ${table.preposition[1].key} ${table.pronoun[0].key} ${table.noun[2].key}.`;

    return res;
}

function harder(texts) {
    return intermediate(texts);
}

var host = server.listen(5000, function () {
    var addr = host.address().address;
    var port = host.address().port;

    console.log("serve on http://%s:%s", addr, port);
})