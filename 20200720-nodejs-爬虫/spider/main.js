/**
 * https://bitcointalk.org
 */
var httpProxy = require('http-proxy-middleware');
var https = require('https');
var fs = require('fs');
var cheerio = require('cheerio');
var iconv = require('iconv-lite'); // 编码
var querystring = require('querystring');
var mysql = require('./mysql');
var user = require('./user');
var date = require('./date');
var dir = __dirname;


// 获取命令行地址
var arguments = process.argv.splice(2);
if(arguments[0] === undefined) {
    console.log("\ncommand format error, format as：node main.js [board url]");
    process.exit(0);
}
var url = arguments[0];

//var url = 'https://bitcointalk.org/index.php?board=4.0';
var urls = [];
var index = 1;//1
var trIndex = 1;//2
var trLen = 0;
var $;
var userIds = [];
var board = '';
var postCnt = 0;
var userCnt = 0;
var replyCnt = 0;
var startTime = new Date().Format('yyyy-MM-dd hh:mm:ss');

function fetch() {
    startPageUrl(urls[index]);
}

function startPageUrl(url) {
    console.log('[' + date.getDate() + '] GET -> ' + url);
    var req = https.get(url, function(res) {
        //console.log(res);
        var html = [];
        res.on('data', function(chunk) {
            html.push(chunk);
        });

        res.on('end', function() {
            html = iconv.decode(Buffer.concat(html), 'utf-8');
            $ = cheerio.load(html, {
                ignoreWhitespace: true,
                decodeEntities: false
            });
            getPageTopic();
        });
    });
    req.on('error',function(err){
        console.error('startPageUrl error' + err);
    });
}

function getPageTopic() {
    var trs = $('.bordercolor').last().find('tbody').find('tr');
    trLen = trs.length;   
    fetchPageTopic(trs.eq(trIndex));
}

function fetchPageTopic(trs) {
    
    var _td = trs.find('td').eq(2);
    var _subject_topic = _td.find('a').text();
    


    var _subject_href = _td.find('a').attr('href');
    console.log('\n[' + date.getDate() + '] PostCnt：' + postCnt + ', ReplyCnt：' + replyCnt + ', UserCnt：' + userCnt);
    console.log('[TOPIC INFO]');
    console.log('        Page：' + index + ' / ' + (urls.length-1));
    console.log('       Topic：' + trIndex + ' / ' + (trLen-1));
    console.log('         Url：' + _subject_href + ';all');

    var req = https.get(_subject_href + ';all', function(res) {
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
            
            setTimeout(function(){
                getTopicDetail($);
                 }, 1000);
        });
    });
    req.on('error',function(err){
        console.error('\n[' + date.getDate() + '] [ERROR] main.js fetchPageTopic() https.get() ' + err);
        console.error('\nPlease check you network and enable it can work.');
        console.error('\nThen open cmd to run <node main.js [board url]> to continue fetch topic infomation');
        fs.writeFileSync(dir + '/pageErrorIndex.txt', index);
        process.exit(0);
    });
}

function getTopicDetail($) {
    //console.log($('.html'));
    
    
   // var _subject_topic = $('.subject>a').eq(0).text();
    //var _topic_id = $('.subject>a').eq(0).attr('href');
    //console.log('topic id' + _topic_id);
//    try{
    //_topic_id = _topic_id.split('topic=')[1].split('.')[0];
//    }
//    catch (err) {
//        console.log($);
//        }
               
    var _form = $('form');
    var _table = _form.find("table[class='bordercolor']");
    var _trs = _table.find('tr');
    var _author = _trs.eq(0);
    
    
    var _class = _author.attr('class');
    var _author_name ='';
    var _author_id = '';
    if(_author.find('.poster_info>b>a').text()) {
        _author_name = _author.find('.poster_info>b>a').text();
        _author_id = _author.find('.poster_info>b>a').attr('href');
        _author_id = _author_id.split('u=')[1];
    } else {
        _author_name = _author.find('.poster_info>b').text();
    }
    var _post_time = date.formatDate(_author.find('.td_headerandpost').find('.smalltext').text());
    var _content = _author.find('.td_headerandpost>.post').text();

    // reply info
    var _reply_trs = _table.find('.' + _class);
    _reply_trs.each(function(i) {
        if(i == 0) return true;
        var _this = $(this);
        var _reply_name = '';
        var _reply_user_id = '';
        if(_this.find('.poster_info>b>a').text()) {
            _reply_name = _this.find('.poster_info>b>a').text();
            _reply_user_id = _this.find('.poster_info>b>a').attr('href');
            _reply_user_id = _reply_user_id.split('u=')[1];
        } else {
            _reply_name = _this.find('.poster_info>b').text();
        }
        var _reply_time = date.formatDate(_this.find('.td_headerandpost').find('.smalltext').text());
        var _reply_order = _this.find('.td_headerandpost').find('.message_number').text();
        _reply_order = _reply_order.split('#')[1];

        // remove div
        _this.find('.td_headerandpost>.post').find('div').remove();
        var _reply_content = _this.find('.td_headerandpost>.post').text();
        _reply_content = handlerText(_reply_content);
        if(_reply_user_id) {
            mysql.insertReply(_reply_order, _reply_time, _reply_content, _reply_user_id);//yiyang: , _topic_id);
            replyCnt++;
            if(userIds.indexOf(_reply_user_id) == -1) {
                mysql.insertUserId(_reply_user_id);
                userIds.push(_reply_user_id);
                userCnt++;
            }
        }
    });
    _subject_topic = handlerText(_subject_topic);
    _content = handlerText(_content);
    if(_author_id) {
        //mysql.insertPost(board, _topic_id, _subject_topic, _content, _post_time, _author_id);
        mysql.insertPost(board, _subject_topic, _content, _post_time, _author_id);
        postCnt++;
        if(userIds.indexOf(_author_id) == -1) {
            mysql.insertUserId(_author_id);
            userIds.push(_author_id);
            userCnt++;
        }
    }
    console.log('     Subject：' + _subject_topic);
    console.log('      Author：' + _author_name );
    console.log('       Reply：' + (_reply_trs.length-1));
    console.log();
    trIndex ++;
    if(trIndex < trLen) {
        getPageTopic($);
    } else {
        index++;
        trIndex = 1;
        if (index < urls.length) {
            fetch();
        } else {
            var endTime = new Date().Format('yyyy-MM-dd hh:mm:ss');
            console.log('\nStartTime：' + startTime + ', EndTime' + endTime);
            console.log('PostCnt：' + postCnt + ', ReplyCnt：' + replyCnt + ', UserCnt：' + userCnt + '\n');
            // delete file
            try {
                fs.unlinkSync(dir + '/pageErrorIndex.txt');
            } catch (err) {}
            user.main();
        }
    }
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

function initPageUrl(pages) {
    console.log('');
    var pageErrorIndex = 1;
    try {
        pageErrorIndex = fs.readFileSync(dir + '/pageErrorIndex.txt', 'utf-8');
        index = pageErrorIndex;
    } catch (err) {
        //pageErrorIndex = 1;
    }
    for(var i = pageErrorIndex; i < pages * 1 + 1; i++) {
        console.log('[' + date.getDate() + '] Init Page ' + i + ' / ' + pages);
        urls[i] = url.replace('.0', '') + '.' + (i - 1) * 40;
    }
    console.log('');
    fetch();
}

function main() {
    console.log('\n[' + date.getDate() + '] Fetching Board：' + url);
    var req = https.get(url, function(res) {
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
            var pages = $('#toppages>a').last().text();
            board = $('#bodyarea .nav>b').last().text();
            initPageUrl(pages);
        });
    });
    req.on('error',function(err){
        console.error('\n[' + date.getDate() + '] [ERROR] main.js https.get() ' + err);
        console.error('\nPlease check you network and enable it can work.');
        console.error('\nThen open cmd to run <node main.js [board url]> to continue fetch topic infomation');
        process.exit(0);
    });
    req.end();
}

main();
