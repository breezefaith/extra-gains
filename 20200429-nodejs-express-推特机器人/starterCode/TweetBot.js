var Twitter = require('twitter');
const express = require('express');
const server = express();

var client = new Twitter({
    consumer_key: '',
    consumer_secret: '',
    access_token_key: '',
    access_token_secret: ''
});
