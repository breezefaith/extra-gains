var Conf = require('conf');

var config = new Conf();

var admin = {
    user_id: '1',
    username: "admin",
    password: "aaaaaa",
    email: "admin@admin.com",
    phone: "12345678910"
};

config.set("admin", admin);
config.set("1", admin);
config.set("admin@admin.com", admin);

var user1 = {
    user_id: '2',
    username: "user1",
    password: "aaaaaa",
    email: "user1@admin.com",
    phone: "12345678910"
};
config.set("user1", user1);
config.set("2", user1);
config.set("user1@admin.com", user1);

module.exports = config;