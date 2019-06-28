const mysql = require('mysql');

let connection = mysql.createConnection({
    host: "35.205.142.152",
    user: "root",
    database: "rate-comment",
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