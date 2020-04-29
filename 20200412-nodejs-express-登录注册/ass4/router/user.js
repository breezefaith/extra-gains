var express = require('express');
var path = require("path")
var uuid = require("uuid");
var config = require("../conf");

var router = express.Router();

router.get("/", function (req, res, next) {
    var user_id = req.cookies.user_id;
    if (user_id == null) {
        res.redirect("/user/login");
    } else {
        res.redirect("/user/" + user_id);
    }
});

router.get("/login", function (req, res, next) {
    res.render("login");
});

router.post("/login", function (req, res, next) {
    var username = req.body.username;
    var password = req.body.password;

    var user = config.get(username);
    if (user == null || user.password != password) {
        res.render("login", { message: "Invalid username or password", username: username });
    } else {
        res.cookie("user_id", user.user_id);
        res.redirect("/user/" + user.user_id);
    }
});

router.get("/new", function (req, res, next) {
    res.render("new");
})

router.post("/new", function (req, res, next) {
    var new_user = {
        user_id: null,
        username: req.body.username,
        email: req.body.email,
        password: req.body.password,
        verified_password: req.body.verified_password,
        phone: req.body.phone,
    };

    if (new_user.password != new_user.verified_password) {
        res.render("new", { message: "Verified password must be equals to password", user: new_user });
        return;
    }
    if (config.has(new_user.username) == true) {
        res.render("new", { message: "The username has been registered", user: new_user });
        return;
    }
    if (config.has(new_user.email) == true) {
        res.render("new", { message: "The email has been registered", user: new_user });
        return;
    }

    new_user.user_id = uuid.v1();

    config.set(new_user.user_id, new_user);
    config.set(new_user.username, new_user);
    config.set(new_user.email, new_user);

    res.cookie.user_id = null;
    res.render("login");
})

router.get("/:user_id", function (req, res, next) {
    var user_id = req.params.user_id;
    var user = config.get(user_id);
    if(user != null){
        res.render("user", { user: user });
    }else{
        res.status(404).send('Not found');
    }
})

router.post("/:user_id", function (req, res, next) {
    var new_info = {
        username: req.body.username,
        email: req.body.email,
        phone: req.body.phone,
    };

    var user = config.get(req.params.user_id);

    if(user == null){
        res.status(404).send('Not found');
        return;
    }
    if (new_info.username != user.username && config.has(new_info.username) == true) {
        res.render("user", { message: "The username has been registered", user: user });
        return;
    }
    if (new_info.email != user.email && config.has(new_info.email) == true) {
        res.render("user", { message: "The email has been registered", user: user });
        return;
    }

    config.delete(user.username);
    config.delete(user.email);

    user.username = new_info.username;
    user.email = new_info.email;
    user.phone = new_info.phone;

    config.set(user.user_id, user);
    config.set(user.username, user);
    config.set(user.email, user);
    res.render("user", { success: "Information modified successfully.", user: user });
})

module.exports = router;