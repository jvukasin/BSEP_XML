const mysql = require('mysql');

let connection = mysql.createConnection({
    host: "35.238.27.216",
    user: "root",
    database: "ratings",
    password: "root"
});

connection.connect(function(err) {
    if (err) {
        console.error('Error connecting: ' + err.stack);
        return;
    }
    console.log('Connected as thread id: ' + connection.threadId);
});

module.exports = connection;