var http = require("http")

var handler = function (req, res) {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    res.write("<html><head></head><body>");
    res.write("<p>3. If you could make a sandwich with only 5 ingredients, what would you choose? Why?</p>");
    res.write("<p>I would like to choose whole wheat bread, lettuce, tomato, steak and thousand island sauce. The combination of lettuce, tomato and thousand island sauce tastes very good, and steak is one of my favorite foods.</p>");
    res.write("</body></html>");
    res.end();
};

var server = http.createServer(handler).listen(3500);