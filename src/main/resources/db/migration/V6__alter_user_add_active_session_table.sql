ALTER TABLE users
    ADD COLUMN session_active int(10) DEFAULT 0 AFTER role;


