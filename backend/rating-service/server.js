const mysql = require('mysql');
const express = require('express');
const os = require('os');

var app = express();

 var mysqlConnection = mysql.createConnection(
     {
        user: 'root',
        password: 'root',
        database: 'rating_database',
        socketPath: '/cloudsql/xml-rating-service:us-central1:rating-database',
     }
 );

 mysqlConnection.connect(function (err)
 {
     if(err)
    {
        console.log("ERROR: " + JSON.stringify(err));
    }
    else
    {
        console.log("INFO: Successfully connected to cloud.");
        console.log("\n\n");
        console.log("INFO: CPUS: ");
        console.log(JSON.stringify(os.cpus()) + "\n\n");
        console.log("INFO: Home dir: " + os.homedir() + "\n\n");
        console.log("INFO: Running on: " + os.hostname());
    }
 });

 
app.listen(8080,() => console.log('Listening at 8080.'));
app.get('/', (req,res) => res.send('Hello world'));


