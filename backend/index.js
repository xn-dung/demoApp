const express = require("express");
const mysql = require("mysql2");
const app = express();

app.use(express.json());


db.connect(err => {
    if (err) {
        console.error("MySQL connection error:", err);
    } else {
        console.log("MySQL connected!");
    }
});


app.listen(3000, () => {
    console.log("Server is running on port 3000");
});
