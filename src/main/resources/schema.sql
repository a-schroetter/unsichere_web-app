CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(60) NOT NULL
);

INSERT INTO users (username, password) 
SELECT 'admin', '$2a$10$NmDh4CDTo9wV7HWy6v3OBeruVlSYpb0M0RA4HdmtNyt.CIdMNFCWy' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

INSERT INTO users (username, password) 
SELECT 'anton', '$2a$10$7UVcNbtizm40gr/KIgOfgOpiI4tAJRKNQi0ElVQOJ8O.wVO/IMaVK' 
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'anton');