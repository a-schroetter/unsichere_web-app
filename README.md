# Unsafe web-app

This project was built with security issues to learn and understand the fundamentals of web security and fix them step by step.

## Build the web-app
- login with database access
- profiles with session management

## App Vulnerabilities
- SQL-Injection (String concatenation in queries)
- passwords stored in plaintext
- no session invalidation on logout
- IDOR on profile page
- No brute-force protection on login

### Fixing App Vulnerabilities
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
    
- [x] brute-force protection
    - before fix:   -> no limitations on how often you can try to login
    - after fix:    -> after 5 login attempts account gets blocked
    - how to fix:   -> add counter for failed login attempts and check before every login

## Server Vulnarabilities
- brute-force attack possible
- root-login possible
- no firewall
- app runs as root-user
- no https

### Fixing Server Vulnerabilities
- [x] brute-force protection for SSH-login
    - before fix:    -> no limitation on how often you can try to login
    - after fix:     -> after 5 login attempts your IP gets blocked for 10 minutes
    - how to fix:    -> install fail2ban, works and runs out of the box (maxretry=5, bantime=10m, findtime=10m)
    - proof:
    <img width="744" height="269" alt="image" src="https://github.com/user-attachments/assets/2395cbae-bec6-4a9d-80f9-77453274467a" />
          
- [x] disable root-login via SSH
    - before fix:    -> root-login via SSH possible
    - after fix:     -> root-login only possible on the server console
    - how to fix:    -> in /etc/ssh/sshd_config change `PermitRootLogin yes` to `PermitRootLogin no`
    - proof:
    <img width="388" height="55" alt="image" src="https://github.com/user-attachments/assets/fdec72dc-d0ed-4cbe-aafe-ae42c54ac353" />
    
- [ ] firewall
- [ ] seperate user for app
- [ ] configure https
