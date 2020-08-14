/**
 * update user info
 */
var https = require('https');
var fs = require('fs');
var cheerio = require('cheerio');
var iconv = require('iconv-lite'); // 编码
var querystring = require('querystring');
var mysql = require('./mysql');
var date = require('./date');
var dir = __dirname;

var index = 0;
var errorIndex = 0;
try {
	errorIndex = fs.readFileSync(dir + '/userErrorIndex.txt', 'utf-8');
	index = errorIndex;
} catch (err) {
	//errorIndex = 0;
}
var userLen = [];
var url = 'https://bitcointalk.org/index.php?action=profile;u=';
var startTime = new Date().Format('yyyy-MM-dd hh:mm:ss');

function main() {
	if(userLen.length == 0) {
		mysql.getAllUserList(function(data) {
			for(var i = errorIndex; i < data.length; i++) {
				userLen[i] = data[i].user_id;
				if(i == data.length - 1) {
					fetch(userLen[index]);
				}
			}
		});
	} else {
		fetch(userLen[index]);
	}
}


function fetch(userId) {
	console.log('[' + date.getDate() + '] GET User Info ' + index + '/' + userLen.length + ' -> ' + url + userId);

	var req = https.get(url + userId, function(res) {
        //console.log(res);
        var html = [];
        res.on('data', function(chunk) {
            html.push(chunk);
        });

        res.on('end', function() {
            html = iconv.decode(Buffer.concat(html), 'utf-8');
            var $ = cheerio.load(html, {
                ignoreWhitespace: true,
                decodeEntities: false
            });
            getUserInfo($);

            index++;
            if(index < userLen.length) {
                setTimeout(function(){
            	main();
                }, 750);
            } else {
            	var endTime = new Date().Format('yyyy-MM-dd hh:mm:ss');
            	console.log('\nStartTime：' + startTime + ', EndTime' + endTime);
            	//process.exit(0);
            	console.log('\nFetching end, Please close terminal');
            	// delete file
            	try {
					fs.unlinkSync(dir + '/userErrorIndex.txt');
				} catch (err) {}
            }
        });
    });
    req.on('error',function(err){
        console.error('\n[' + date.getDate() + '] [ERROR] user.js https.get() ' + err);
        console.error('\nPlease check you network and enable it can work.');
        console.error('\nThen open cmd to run <node getUser.js> to continue fetch user infomation');
        fs.writeFileSync(dir + '/userErrorIndex.txt', index);
        process.exit(0);
    });
}

function getUserInfo($) {
	var _tr = $('#bodyarea .bordercolor>tbody').children('tr').eq(1);
	var _trs = _tr.find('tr');
	var _posts = '', _activity = '', _position = '', _date_reg = '', _bitcoin_add = '', _gender = '', _age = '', _location = '';
	_trs.each(function(i) {
		var _this = $(this);
		var _name = _this.find('td').eq(0).text();
		var _value = _this.find('td').eq(1).text();
		_value = handlerText(_value);
		if(_name.indexOf('Posts') != -1) _posts = _value;
		if(_name.indexOf('Activity') != -1) _activity = _value;
		if(_name.indexOf('Position') != -1) _position = _value;
		if(_name.indexOf('Date Registered') != -1) _date_reg = date.formatDate(_value);
		if(_name.indexOf('Bitcoin address') != -1) _bitcoin_add = _value;
		if(_name.indexOf('Gender') != -1) _gender = _value;
		if(_name.indexOf('Age') != -1) _age = _value;
		if(_name.indexOf('Location') != -1) {
			_location = _value;
			return false;
		}
	});
    mysql.updateUser(userLen[index], _posts, _activity, _position, _date_reg, _bitcoin_add, _gender, _location, _age);
}

function handlerText(txt) {
    var result = txt.replace(/'/g, '&apos;')
        .replace(/"/g, '&quot;')
        .replace(/‘/g, '&apos;')
        .replace(/’/g, '&apos;')
        .replace(/“/g, '&quot;')
        .replace(/”/g, '&quot;')
        .replace(/\\/g, '\\ ');
    return result;
}

exports.main = main;
//main();
