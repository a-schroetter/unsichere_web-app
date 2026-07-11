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
    - before fix:   -> login possible without valid password
                    -> username `' OR '1'='1' --` logged you in with any password
    - after fix:    -> same input returns "Invalid username or password" error message
    - how to fix:   -> use prepared statements instead of String concatenation to treat input as text instead of SQL-statements
    
- [ ] password encryption
- [ ] invalidate sessions
- [ ] IDOR
- [ ] brute-force protection