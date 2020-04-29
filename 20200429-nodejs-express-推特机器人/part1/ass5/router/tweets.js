var express = require('express');
var path = require("path")

var router = express.Router();

router.get("/:param", function (req, res, next) {
    var param = req.params.param;
    
    res.json(param).end();
})

module.exports = router;