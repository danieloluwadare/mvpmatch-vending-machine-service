CREATE TABLE users (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    username varchar(255) DEFAULT NULL,
    password varchar(100) DEFAULT NULL,
    deposit decimal(15,2) DEFAULT NULL,
    role varchar(100) DEFAULT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_email (username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- CREATE TABLE roles (
--     id bigint(20) NOT NULL AUTO_INCREMENT,
--     name varchar(60) NOT NULL,
--     PRIMARY KEY (id),
--     UNIQUE KEY uk_roles_name (name)
-- ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
--
--
-- CREATE TABLE user_roles (
--   user_id bigint(20) NOT NULL,
--   role_id bigint(20) NOT NULL,
--   PRIMARY KEY (user_id,role_id),
--   KEY fk_user_roles_role_id (role_id),
--   CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
--   CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- CREATE TABLE salary_history (
--     id bigint(20) NOT NULL AUTO_INCREMENT,
--     user_id bigint(20) NOT NULL,
--     salary decimal(15,2) DEFAULT NULL,
--     PRIMARY KEY (id),
--     CONSTRAINT fk_salary_history_employee_id FOREIGN KEY (user_id) REFERENCES users (id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- For a many to many relationship
-- CREATE TABLE manager_employee (
--     manager bigint(20) NOT NULL,
--     employee bigint(20) NOT NULL,
--     PRIMARY KEY (user_id,role_id),
--     KEY fk_user_roles_role_id (role_id),
--
--     CONSTRAINT fk_manager_employee_manger FOREIGN KEY (manager) REFERENCES users (id),
--     CONSTRAINT fk_manager_employee_employee FOREIGN KEY (employee) REFERENCES users (id),
--
--
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
