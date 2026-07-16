# Unsafe web-app

This project was built with security issues to learn and understand the fundamentals of web security and fix them step by step.

## Build the web-app
- login with database access
- profiles with session management

## Vulnerabilities
- SQL-Injection (String concatenation in queries)
- passwords stored in plaintext
- no session invalidation on logout
- IDOR on profile page
- No brute-force protection on login

## Fixing Vulnerabilities (in progress)
- [x] SQL-Injection
    - before fix:   -> login possible without valid password - username `' OR '1'='1' --` logged you in with any password
    - after fix:    -> same input returns "Invalid username or password" error message
    - how to fix:   -> use prepared statements instead of String concatenation to treat input as text instead of SQL-statements
    
- [x] password encryption
    - before fix:   -> passwords stored as text - visible to anyone with DB access
    - after fix:    -> passwords stored as BCrypt hashes
    - how to fix:   -> store hash of the password in database and check with BCryptPasswordEncoder

- [x] invalidate sessions
    - before fix:   -> when logout was clicked the session remained active, user could access /profile after logout
    - after fix:    -> when clicked on logout, session is invalidated - user cant access /profile without entering password again
    - how to fix:   -> redirect user to /logout and invalidate session there

- [x] IDOR
    - before fix:   -> when logged in user could access any UserRepository via /profile?id=2
    - after fix:    -> user is getting redirectet to personal repository when they try to access unauthorized ID
    - how to fix:   -> compare username of current session to username of the accessed id on every GET-request of the repository
    
- [ ] brute-force protection