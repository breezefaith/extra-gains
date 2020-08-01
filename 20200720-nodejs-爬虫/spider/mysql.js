var mysql = require('mysql');
var date = require('./date');

var db = {};
var pool = mysql.createPool({
    host: 'localhost',
    port: '3306',
    user: 'root',
    password: 'mysql_990524',
    database: 'bitforum',
    charset: 'utf8mb4_general_ci'
});

db.con = function(callback) {
    pool.getConnection(function (err, connection) {
        //console.log('\n[' + date.getDate() + '] Mysql Server Connect Success!')
        if (err) {      //对异常进行处理
            throw err;  //抛出异常
        } else {
            callback(connection);   //如果正常的话，执行回调函数（即请求）
        }
        connection.release();   //释放连接
        //console.log('\n[' + date.getDate() + '] Mysql Server Connect Release!')
    });
}

/*var connection = mysql.createConnection({
    host: 'localhost',
    port: '3306',
    user: 'root',
    password: 'root',
    database: 'bitcointalk',
    charset: 'utf8mb4_general_ci'
});
connection.connect(function(err) {
    if (err) {
        console.log('[CONNECT] - ', err.message);
        console.log('\n[EXIT] - ', ' Process exit');
        process.exit(0);
    }
    console.log('\n[' + date.getDate() + '] Mysql Server Connect Success!')
});*/

function insertPost(board, postId, topic, postContent, postTime, userId) {
    db.con(function(connect) {
        var sql = "SELECT * FROM post WHERE post_id = " + postId;
        connect.query(sql, function(err, result) {
            if (err) {
                console.log('[INSERT Post ERROR] - ', err.message);
                return;
            }
            if(result.length == 0) {
                sql = 'INSERT INTO post (board, post_id, topic, post_content, post_time, user_id) VALUES (';
                sql += '\'' + board + '\','
                sql +=  postId + ',';
                sql += '\'' + topic + '\',';
                sql += '\'' + postContent + '\',';
                sql += '\'' + postTime + '\',';
                sql += userId;
                sql += ')';
                connect.query(sql, function(err, result) {
                    if (err) {
                        console.log('[INSERT Post ERROR] - ', err.message);
                        return;
                    }
                });
            }
        });
    });
    /*var sql = "SELECT * FROM post WHERE post_id = " + postId;
    connection.query(sql, function(err, result) {
        if (err) {
            console.log('[INSERT Post ERROR] - ', err.message);
            return;
        }
        if(result.length == 0) {
            sql = 'INSERT INTO post (board, post_id, topic, post_content, post_time, user_id) VALUES (';
            sql += '\'' + board + '\','
            sql +=  postId + ',';
            sql += '\'' + topic + '\',';
            sql += '\'' + postContent + '\',';
            sql += '\'' + postTime + '\',';
            sql += userId;
            sql += ')';
            connection.query(sql, function(err, result) {
                if (err) {
                    console.log('[INSERT Post ERROR] - ', err.message);
                    return;
                }
            });
        }
    });*/
}

function insertReply(replyOrder, replyTime, replyContent, userId, postId) {
    db.con(function(connect) {
        var sql = 'SELECT * FROM user_reply_post WHERE post_id = ' + postId + ' and user_id = ' + userId + ' and reply_order = ' + replyOrder;
        connect.query(sql, function(err, result) {
            if (err) {
                console.log('[INSERT Reply ERROR] - ', err.message);
                return;
            }
            if(result.length == 0) {
                sql = 'INSERT INTO user_reply_post (reply_order, reply_time, reply_content, user_id, post_id) VALUES (';
                sql += replyOrder + ', ';
                sql += '\'' + replyTime + '\',';
                sql += '\'' + replyContent + '\',';
                sql += userId + ', ';
                sql += postId;
                sql += ')';
                connect.query(sql, function(err, result) {
                    if (err) {
                        console.log('[INSERT Reply ERROR] - ', err.message);
                        return;
                    }
                });
            }
        });
    });
    /*var sql = 'SELECT * FROM user_reply_post WHERE post_id = ' + postId + ' and user_id = ' + userId + ' and reply_order = ' + replyOrder;
    connection.query(sql, function(err, result) {
        if (err) {
            console.log('[INSERT Reply ERROR] - ', err.message);
            return;
        }
        if(result.length == 0) {
            sql = 'INSERT INTO user_reply_post (reply_order, reply_time, reply_content, user_id, post_id) VALUES (';
            sql += replyOrder + ', ';
            sql += '\'' + replyTime + '\',';
            sql += '\'' + replyContent + '\',';
            sql += userId + ', ';
            sql += postId;
            sql += ')';
            connection.query(sql, function(err, result) {
                if (err) {
                    console.log('[INSERT Reply ERROR] - ', err.message);
                    return;
                }
            });
        }
    });*/
}

function insertUserId(userId) {
    db.con(function(connect) {
        var sql = 'SELECT * FROM user WHERE user_id = ' + userId;
        connect.query(sql, function(err, result) {
            if (err) {
                console.log('[SELECT ERROR] - ', err.message);
                return;
            }
            if(result.length == 0) {
                sql = 'INSERT INTO user (user_id) VALUES (';
                sql += userId;
                sql += ')';
                connect.query(sql, function(err, result) {
                    if (err) {
                        console.log('[INSERT ERROR] - ', err.message);
                        return;
                    }
                });
            }
        });
    });
    /*var sql = 'SELECT * FROM user WHERE user_id = ' + userId;
    connection.query(sql, function(err, result) {
        if (err) {
            console.log('[SELECT ERROR] - ', err.message);
            return;
        }
        if(result.length == 0) {
            sql = 'INSERT INTO user (user_id) VALUES (';
            sql += userId;
            sql += ')';
            connection.query(sql, function(err, result) {
                if (err) {
                    console.log('[INSERT ERROR] - ', err.message);
                    return;
                }
            });
        }
    });*/
}

function findAllUser(callback) {
    db.con(function(connect) {
        var sql = 'SELECT DISTINCT user_id FROM user where date_registered is null';
        //var sql = 'SELECT DISTINCT user_id FROM user where id in(20409, 12566, 12567, 12568)';
        var list;
        connect.query(sql, function(err, result) {
            if (err) {
                console.log('[FIND ALL USER ERROR] - ', err.message);
                return;
            }
            callback(result);
        });
    });
    /*var sql = 'SELECT DISTINCT user_id FROM user where posts is null or posts = \'\'';
    var list;
    connection.query(sql, function(err, result) {
        if (err) {
            console.log('[FIND ALL USER ERROR] - ', err.message);
            return;
        }
        callback(result);
    });*/
}

function getAllUserList(callback) {
    return findAllUser(callback);
}

function updateUser(userId, posts, activity, position, dateRegistered, bitcoinAddress, gender, location, age) {
    db.con(function(connect) {
        var sql = 'UPDATE user set';
        sql += ' posts = \'' + posts + '\'';
        sql += ', activity = \'' + activity + '\'';
        sql += ', position = \'' + position + '\'';
        sql += ', date_registered = \'' + dateRegistered + '\'';
        sql += ', bitcoin_address = \'' + bitcoinAddress + '\'';
        sql += ', gender = \'' + gender + '\'';
        sql += ', location = \'' + location + '\'';
        sql += ', age = \'' + age + '\'';
        sql += ' where user_id = ' + userId;
        connect.query(sql, function(err, result) {
            if (err) {
                console.log('[UPDATE ERROR] - ', err.message);
                return;
            }
        });
    });
    /*var sql = 'UPDATE user set';
    sql += ' posts = \'' + posts + '\'';
    sql += ', activity = \'' + activity + '\'';
    sql += ', position = \'' + position + '\'';
    sql += ', date_registered = \'' + dateRegistered + '\'';
    sql += ', bitcoin_address = \'' + bitcoinAddress + '\'';
    sql += ', gender = \'' + gender + '\'';
    sql += ', location = \'' + location + '\'';
    sql += ', age = \'' + age + '\'';
    sql += ' where user_id = ' + userId;
    connection.query(sql, function(err, result) {
        if (err) {
            console.log('[UPDATE ERROR] - ', err.message);
            return;
        }
    });*/
}

function end() {
    connection.end();
}

exports.insertPost = insertPost;
exports.insertReply = insertReply;
exports.insertUserId = insertUserId;
exports.getAllUserList = getAllUserList;
exports.updateUser = updateUser;
exports.end = end;
