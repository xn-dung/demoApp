const express = require("express");
const mysql = require("mysql2");
const app = express();

app.use(express.json());

const db = mysql.createConnection({
    host : "localhost",
    user : "ledung",
    password : "06122004",
    database : "appProject"
});

db.connect(err => {
    if (err) {
        console.error("MySQL connection error:", err);
    } else {
        console.log("MySQL connected!");
    }
});

app.post("/login", (req, res) => {
    const { username, password } = req.body;

    if (!username || !password) {
        return res.status(400).json({ error: "Username and password required" });
    }

    const sql = "SELECT * FROM tblUser WHERE username LIKE ? AND password LIKE ?";
    db.query(sql, [`%${username}%`, `%${password}%`], (err, result) => {
        if (err) {
            console.log(err);
            return res.status(500).json({ error : "Data query failed" });
        }
        if (result.length > 0) {
            res.status(200).json({ success: true, message: "Login success", user: result[0] });
        } else {
            res.status(401).json({ success: false, message: "Invalid username or password" });
        }
    });
});

app.listen(3000, () => {
    console.log("Server is running on port 3000");
});
