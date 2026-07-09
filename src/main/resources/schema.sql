CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(60) NOT NULL
);

INSERT INTO users (username, password) 
SELECT 'admin', 'admin123' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

INSERT INTO users (username, password) 
SELECT 'anton', 'passwort1' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'anton');